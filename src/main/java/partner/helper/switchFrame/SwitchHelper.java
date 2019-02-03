package partner.helper.switchFrame;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import partner.helper.logger.LoggerHelper;

public class SwitchHelper {
	
	private WebDriver driver;
	
	Logger log = LoggerHelper.getLogger(SwitchHelper.class);
	
	public SwitchHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void switchToFrame(int index)
	{
		log.info("Switching to frame index: " + index);
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(String frameName)
	{
		driver.switchTo().frame(frameName);
	}
	
	public void switchToFrame(WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToDefaultWindow()
	{
		driver.switchTo().defaultContent();
	}
	
	public void switchToWindow(int index)
	{
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		
		for(String window : windows)
		{
			if(i == index)
			{
				driver.switchTo().window(window);
			}
			else
			{
				i++;
			}
		}
	}
	
	
	public void closeAllTabsAndSwitchToMainWindow()
	{
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
		for(String window : windows)
		{
			if(!window.equalsIgnoreCase(mainWindow))
			{
				driver.close();
			}			
		}
		
		driver.switchTo().window(mainWindow);
	}
	
	public void navigateBack()
	{
		driver.navigate().back();
	}
	
	public void navigateForward()
	{
		driver.navigate().forward();
	}
	
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
	

}
