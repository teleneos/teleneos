package org.teleneos.radius.bandwidth;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.teleneos.broker.Main;
import org.teleneos.radius.accounting.Radacct;
import org.teleneos.radius.accounting.RadacctService;
import org.teleneos.radius.history.ConnectionHistory;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class BandwidthServiceImpl implements BandwidthService {
	
	@Inject
	private Configuration configuration;
	
	@Value("${radius.client.remote_intet_address}")
	private String remoteInetAddress;

	@Value("${radius.client.remote_socket_port}")
	private int remoteSocketPort;
	
	@Inject
	private RadacctService radacctService;
	
	@Value("${htb.config}")
	private String qosConfigLocation;
	@Override
	public void reloadConfiguration() {
		EntityListWrapper<Object[]> radaccts = radacctService.groupByPackage();
		Map<String, Object> input = new HashMap<String, Object>();
		HashMap<String, List<UserInformation>> map = populateTemplateModelData(radaccts);
		input.put("map", map);
		Writer file = null;
		configuration.setClassForTemplateLoading(this.getClass(), "/");
		try {
			Template template = configuration.getTemplate("view/cfg/eth1-qos.ftl");
			file = new FileWriter(new File(qosConfigLocation));
			template.process(input, file);
			file.flush();
			Thread.sleep(500);
			reloadHTBService();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private HashMap<String, List<UserInformation>> populateTemplateModelData(
			EntityListWrapper<Object[]> radaccts) {
		HashMap<String, List<UserInformation>> map = new HashMap<String, List<UserInformation>>();
		for (Object[] obsj : radaccts.getEntityList()) {
			Radacct radacct = (Radacct) obsj[0];
			ConnectionHistory connectionHistory = (ConnectionHistory) obsj[1];
			String key = connectionHistory.getUserPackage().getInternetPackage().getCode();
			List<UserInformation> list = map.get(key);
			if(!map.containsKey(key)){
				list = new ArrayList<UserInformation>();
				list.add(new UserInformation(radacct, connectionHistory));
				map.put(key, list);
			}else{
				list.add(new UserInformation(radacct, connectionHistory));
				map.put(key, list);
			}
		}
		return map;
	}

	@Override
	public void reloadHTBService() throws UnknownHostException, IOException {
		Socket clientSocket = new Socket(remoteInetAddress, remoteSocketPort);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		outToServer.writeBytes(Main.RELOAD_HTB + '\n');
		clientSocket.close();
	}
}
