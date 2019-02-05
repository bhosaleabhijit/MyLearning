package partner.helper.browserConfig.config;

import partner.helper.browserConfig.BrowserType;

public interface ConfigReader {
	public int getImplicityWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getBaseURL();

}
