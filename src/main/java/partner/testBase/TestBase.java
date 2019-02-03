package partner.testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import partner.helper.browserConfig.BrowserType;
import partner.helper.browserConfig.ChromeBrowser;
import partner.helper.browserConfig.FirefoxBrowser;
import partner.helper.browserConfig.config.ObjectReader;
import partner.helper.browserConfig.config.PropertyReader;
import partner.helper.utils.ExtentManager;
import partner.helper.wait.WaitHelper;
import partner.helper.logger.LoggerHelper;
import partner.helper.resource.ResourceHelper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

public class TestBase {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	private File reportDirectory = new File(ResourceHelper.getResourcePath("/src/main/resources/screenshots"));;

	@BeforeSuite
	public void beforeSuit() {
		extent = ExtentManager.getInstance();
	}

	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getName());
		test.log(Status.INFO, "test extent test created");
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		log.info("In the before test");
		reportDirectory = new File(ResourceHelper.getResourcePath("/src/main/resources/screenshots"));
		setUpDriver(ObjectReader.reader.getBrowserType());
	}

	@AfterClass
	public void afterClass() throws Exception {
		log.info("In the After class");
		if (driver != null)
		{
			driver.quit();
			driver = null;
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + " test started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		log.info("In the After Method");
		Reporter.log("In the After method: " + result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(), driver);
			if (imagePath != null)
				test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is passed");
			String imagePath = captureScreen(result.getName(), driver);
			if (imagePath != null) {
				test.addScreenCaptureFromPath(imagePath);
				Reporter.log("<a href='" + imagePath + "'><img src='" + imagePath + "' height='100' width='100'/></a>");
			}
		}

		extent.flush();
	}

	public WebDriver getBrowserObject(BrowserType bType) throws Exception {
		try {
			switch (bType) {
			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);
			default:
				throw new Exception("Driver not found: " + bType.name());
			}

		} catch (Exception e) {
			System.out.println("Exception in getBrowserObject method");
			e.printStackTrace();
			log.info("Browser type not available: " + bType.name());
			throw e;
		}

	}

	public void setUpDriver(BrowserType bType) throws Exception {
		if (driver == null) {
			driver = getBrowserObject(bType);
			log.info("Initialize web driver: " + driver.hashCode());
			WaitHelper wait = new WaitHelper(driver);
			wait.setImplicitWait(ObjectReader.reader.getImplicityWait(), TimeUnit.SECONDS);
			wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else {
			log.info("driver present" + driver.toString());
			driver=driver;
			
		}
	}

	public String captureScreen(String fileName, WebDriver driver) {
		log.info("In the capture screen");
		System.out.println("File name is: " + fileName);
		if (driver == null) {
			log.info("driver is null");
			Reporter.log("Driver is null");
			return null;
		}
		if (fileName == "") {
			fileName = "Blank";
		}

		File destFile = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectory + "/" + fileName + "_"
					+ formatter.format(date).toString().replace(':', '_') + ".png");
			Files.copy(srcFile.toPath(), destFile.toPath());

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/></a>");
		} catch (Exception e) {
			System.out.println("Exception while getting screenshot: ");
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}

}
