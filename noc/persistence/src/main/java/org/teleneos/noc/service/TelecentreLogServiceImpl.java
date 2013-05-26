package org.teleneos.noc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.teleneos.noc.TelecentreLog;

@Service
public class TelecentreLogServiceImpl implements TelecentreLogService {

	public static final Logger LOG = Logger.getLogger(TelecentreLogServiceImpl.class);
	
	@Override
	public EntityListWrapper<TelecentreLog> all(int limit, int page) {
		EntityListWrapper<TelecentreLog> teLogs = new EntityListWrapper<TelecentreLog>();
		List<TelecentreLog> logs = new ArrayList<TelecentreLog>();
		try {
			Configuration conf = new Configuration(true);
			conf.set("fs.default.name", "hdfs://192.168.2.15:54310");
			
			FileSystem fs = FileSystem.get(conf);
			FileStatus[] status = fs.listStatus(new Path("/itu/")); 
			for (int i = 0; i < status.length; i++) {
				if (status[i].isDir()) {
					LOG.info(status[i].getPath() + " is directory");
				}	
				else 
					logs.add(new TelecentreLog(status[i].getPath().toString().replace("hdfs://192.168.2.15:54310/", ""), new Date(), "zabbix"));
					
//				if(status[i].isDir()){
//				}else{
//					BufferedReader br = new BufferedReader(new InputStreamReader(
//							fs.open(status[i].getPath())));
//					String line;
//					line = br.readLine();
//					while (line != null) {
//						line = br.readLine();
//					}
//				}
			}
		} catch (Exception e) {
			// System.out.println("File not found");
			e.printStackTrace();
		}
		teLogs.setEntityList(logs);
		return teLogs;
	}
	
	@Override
	public EntityListWrapper<TelecentreLog> getTelecentreLogByDate(Date date, int limit, int page) {
		EntityListWrapper<TelecentreLog> teLogs = new EntityListWrapper<TelecentreLog>();
		try {
			Configuration conf = new Configuration(true);
			conf.set("fs.default.name", "hdfs://192.168.2.15:54310");
			
			FileSystem fs = FileSystem.get(conf);
			FileStatus[] status = fs.listStatus(new Path("/itu/noc")); 
			for (int i = 0; i < status.length; i++) {
				if(status[i].isDir()){
					System.out.println("dir : " + status[i].getPath());
				}else{
					System.out.println("file: " + status[i].getPath());
					BufferedReader br = new BufferedReader(new InputStreamReader(
							fs.open(status[i].getPath())));
					String line;
					line = br.readLine();
					while (line != null) {
	//					System.out.println(line);
						line = br.readLine();
					}
				}
			}
		} catch (Exception e) {
			// System.out.println("File not found");
			e.printStackTrace();
		}
		return teLogs;
	}
	
	@Override
	public EntityListWrapper<TelecentreLog> getTelecentreLogByAppName(String appName,int limit, int page) {
		EntityListWrapper<TelecentreLog> teLogs = new EntityListWrapper<TelecentreLog>();
		try {
			Configuration conf = new Configuration(true);
			conf.set("fs.default.name", "hdfs://192.168.2.15:54310");
			
			FileSystem fs = FileSystem.get(conf);
			FileStatus[] status = fs.listStatus(new Path("/itu/noc")); 
			for (int i = 0; i < status.length; i++) {
				if(status[i].isDir()){
					System.out.println("dir : " + status[i].getPath());
				}else{
					System.out.println("file: " + status[i].getPath());
					BufferedReader br = new BufferedReader(new InputStreamReader(
							fs.open(status[i].getPath())));
					String line;
					line = br.readLine();
					while (line != null) {
	//					System.out.println(line);
						line = br.readLine();
					}
				}
			}
		} catch (Exception e) {
			// System.out.println("File not found");
			e.printStackTrace();
		}
		return teLogs;
	}
	
}
