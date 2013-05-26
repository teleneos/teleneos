package org.teleneos.noc.service;

import java.util.Date;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.noc.TelecentreLog;

public interface TelecentreLogService {
	
	EntityListWrapper<TelecentreLog> all(int limit, int page);
	EntityListWrapper<TelecentreLog> getTelecentreLogByDate(Date date,int limit, int page);
	EntityListWrapper<TelecentreLog> getTelecentreLogByAppName(String appName,int limit, int page);
}
