package partner.helper.logger;

import org.apache.log4j.*;
import org.apache.log4j.PropertyConfigurator;

import partner.helper.resource.ResourceHelper;

public class LoggerHelper {
	
	static boolean isLoggerInitiated = false;
	
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class cls)
	{
		if(isLoggerInitiated)
		{
			return Logger.getLogger(cls);
		}
		
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("/src/main/resources/config/log4j.properties"));
		isLoggerInitiated = true;
		
		return Logger.getLogger(cls);
	}
	
	public static void main(String args[])
	{
		Logger log = getLogger(LoggerHelper.class);
		log.info("Test logger");
	}

}
