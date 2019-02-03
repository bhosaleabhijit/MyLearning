package partner.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import partner.helper.logger.LoggerHelper;

public class AlertHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("Alert Helper intialized");
	}

	public Alert getAlert() {
		log.info("Alert text: " + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("Alert accepted");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("Alert dismissed");
	}

	public String getAlertText() {
		log.info("Alert Text: " + getAlert().getText());
		return getAlert().getText();
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("Alert is present");
			return true;
		} catch (Exception e) {
			log.info("Alert is not present: " + e.getMessage());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
			log.info("Alert is accepted");
		} else {
			log.info("Alert is not present: ");
		}
	}

	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
			log.info("Alert is dismissed");
		} else {
			log.info("Alert is not present: ");
		}
	}
	
	public void acceptPrompt(String msg)
	{
		if(isAlertPresent())
		{
			Alert alert = getAlert();
			alert.sendKeys(msg);
			alert.accept();
			log.info("Alert accepted : " + msg);
		}
		else {
			log.info("Alert is not present: ");
		}
	}
}
