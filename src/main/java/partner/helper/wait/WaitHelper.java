package partner.helper.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import partner.helper.logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver = null;

	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit wait set to: " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int PollingEveryMilliSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(PollingEveryMilliSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		return wait;
	}

	public void waitForElementVisibleWithPollingFrequency(WebElement element, int timeOutInSeconds,
			int pollingEveryInMilliseconds) {
		log.info("Waiting for element to visible with polling ferquency: " + element);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMilliseconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Waiting for element to visible with polling ferquency completed: " + element);
	}

	public void waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for element to be clickable: " + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Wait for element completed: " + element.toString());
	}

	public boolean waitForElementToDisappear(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for element to disappear: " + element);
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Wait for element to disappearcompleted: " + element.toString());
		return status;
	}
	
	private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryMilliSeconds)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.withTimeout(Duration.ofSeconds(timeOutInSeconds))
		.pollingEvery(Duration.ofMillis(pollingEveryMilliSeconds))
		.ignoring(NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class);
		return wait;
	}
	
	public WebElement waitForElementFluently(WebElement element, int timeOutInSeconds, int pollingEveryMilliSeconds)
	{
		log.info("Waiting for element fluently: " + element.toString());
		Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryMilliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Fluent wait completed: " + element.toString());
		return element;
	}
	
	public void pageLoadTime(int timeOut, TimeUnit unit)
	{
		log.info("Waiting for page to load: " + timeOut + " " + unit.toString());
		driver.manage().timeouts().pageLoadTimeout(timeOut, unit);
	}

	public void waitForElement(WebElement element, int timeoutInSeconds) {
		log.info("waiting for element to display: " + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Wait for element completed: " + element.toString());
	}
}