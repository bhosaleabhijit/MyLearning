package partner.helper.browserConfig.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import partner.helper.browserConfig.BrowserType;
import partner.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	private static FileInputStream input;
	private static Properties prop;

	public PropertyReader() {
		try {
			input = new FileInputStream(
					new File(ResourceHelper.getResourcePath("/src/main/resources/config/config.properties")));
			prop = new Properties();
			prop.load(input);
		} catch (FileNotFoundException e) {
			System.out.println("Config Properties file cannot be found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception while reading config properties file");
			e.printStackTrace();
		}
	}

	public int getImplicityWait() {
		return Integer.parseInt(prop.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(prop.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		if (prop.getProperty("browserType").equalsIgnoreCase("Chrome"))
			return BrowserType.Chrome;
		if (prop.getProperty("browserType").equalsIgnoreCase("Firefox"))
			return BrowserType.Firefox;
		return null;

	}

	public String getBaseURL() {
		return prop.getProperty("baseURL");
	}

}
