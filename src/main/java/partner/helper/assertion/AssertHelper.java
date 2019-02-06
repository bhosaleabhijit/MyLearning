package partner.helper.assertion;
import org.apache.log4j.Logger;
import org.testng.Assert;

import partner.helper.logger.LoggerHelper;

public class AssertHelper 
{
	private static Logger log = LoggerHelper.getLogger(AssertHelper.class);
	
	public static void verifyText(String expectedMsg, String actualMsg)
	{
		log.info("Checking if two string are equal: " + expectedMsg + " AND " + actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	public static void markTrue()
	{
		log.info("Marking test as passed");
		Assert.assertTrue(true);
	}
	
	public static void markTrue(String msg)
	{
		log.info("Marking test as Passed: " + msg);
		Assert.assertTrue(true, msg);
	}
	
	public static void markFalse()
	{
		log.info("Marking test as Failed: ");
		Assert.assertTrue(false);
	}
	
	public static void markFalse(String msg)
	{
		log.info("Marking test as Failed: " + msg);
		Assert.assertTrue(false, msg);
	}
	
	public static void verifyTrue(Boolean status)
	{
		log.info("Verifying condition is true: " + status);
		Assert.assertTrue(status);
	}
	
	public static void verifyFalse(Boolean status)
	{
		log.info("Verifying condition is false: " + status);
		Assert.assertFalse(status);
	}
	
}
