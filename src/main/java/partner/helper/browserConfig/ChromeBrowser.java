package partner.helper.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import partner.helper.resource.ResourceHelper;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");

		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);

		options.setCapability(ChromeOptions.CAPABILITY, chrome);

		if (System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,769", "--no-sandbox");
		}

		return options;

	}

	public WebDriver getChromeDriver(ChromeOptions cap) {
		if (System.getProperty("os.name").contains("Windows")) {
			String chromeDriverPath = "/src/main/resources/drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath(chromeDriverPath));

			return new ChromeDriver(cap);
		}

		return null;
	}

	public static void main(String args[]) {
		ChromeBrowser br = new ChromeBrowser();
		ChromeOptions options = br.getChromeOptions();
		WebDriver driver = br.getChromeDriver(options);
		driver.get("https://google.com");

	}

}
