package partner.helper.linstener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import partner.helper.logger.LoggerHelper;;

public class Retry implements IRetryAnalyzer 
{
	private int retryCount = 0;
	private int maxRetryCount = 3;
	Logger log = LoggerHelper.getLogger(Retry.class);
	
	public boolean retry(ITestResult arg0) 
	{
		if(retryCount < maxRetryCount)
		{
			log.info("Retrying Test " + arg0.getName() + "with status: " + getResultStatusName(arg0.getStatus()) + "for " + (++retryCount) + "times");
			return true;
		}
		
		return false;
	}
	
	public String getResultStatusName(int statusId)
	{
		String statusName = "";
		switch(statusId)
		{
		case 1: statusName= "SUCCESS";
				break;
		case 2: statusName= "FAILURE";
				break;
		case 3: statusName= "SKIPPED";
				break;
		default: statusName = "NO STATUS FOUND";
		}
		
		return statusName;
	}
}
