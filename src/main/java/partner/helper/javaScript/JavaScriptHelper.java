package partner.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import partner.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	
	private WebDriver driver;
	
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public Object executeJavaScript(String script)
	{
		log.info("Executing javaascript: " + script);
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	public Object executeJavaScript(String script, Object...args)
	{
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}
	
	public void scrollToElement(WebElement element)
	{
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.scrollTo(arguments[0], arguments[1])", element.getLocation().x, element.getLocation().y);
	}
	
	public void scrollToElementAndClick(WebElement element)
	{
		scrollToElement(element);
		element.click();
	}
	
	public void scrollIntoView(WebElement element)
	{
		executeJavaScript("arguments[0].scrollIntoView()", element);
	}
	
	public void scrollIntoViewAndClick(WebElement element)
	{
		scrollIntoView(element);
		element.click();
	}
	
	public void scrollDownFull()
	{
		executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollUpFull()
	{
		executeJavaScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	
	public void cilckElement(WebElement element)
	{
		executeJavaScript("argument[0].ckick();", element);
	}
}
