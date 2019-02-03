package partner.helper.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import partner.helper.resource.ResourceHelper;

public class FirefoxBrowser {

	public FirefoxOptions getFirefoxOptions() {
		DesiredCapabilities firefox = DesiredCapabilities.firefox();

		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);

		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionatte", true);

		FirefoxOptions options = new FirefoxOptions(firefox);

		if (System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,769", "--no-sandbox");
		}

		return options;
	}

	public WebDriver getFirefoxDriver(FirefoxOptions cap) {
		if (System.getProperty("os.name").contains("Windows")) {
			String firefoxDriverPath = "/src/main/resources/drivers/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath(firefoxDriverPath));

			return new FirefoxDriver(cap);
		}

		return null;
	}
	
	public static void main(String args[]) {
		FirefoxBrowser br = new FirefoxBrowser();
		FirefoxOptions options = br.getFirefoxOptions();
		WebDriver driver = br.getFirefoxDriver(options);
		driver.get("https://google.com");

	}

}
