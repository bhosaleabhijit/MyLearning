package testScripts;


import org.testng.annotations.Test;

import partner.testBase.TestBase;

public class TestBaseTester extends TestBase {
	
	@Test
	public void testScreenShot() 
	{
		driver.get("https://www.google.com");
	//	captureScreen("firstScreenShot", driver);
	}
	

}
