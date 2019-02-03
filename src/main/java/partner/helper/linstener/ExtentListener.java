package partner.helper.linstener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import partner.testBase.TestBase;

import partner.helper.logger.LoggerHelper;

/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import partner.helper.utils.ExtentManager;
import partner.testBase.TestBase; */

public class ExtentListener implements ITestListener {
	
//	ExtentReports extent;
//	ExtentTest test;
	Logger log = LoggerHelper.getLogger(ExtentListener.class);

	public void onTestStart(ITestResult result) {
	//	test.log(Status.INFO, result.getName() + " started.");
		Reporter.log(result.getMethod().getMethodName() + " test started.");
		log.info(result.getMethod().getMethodName() + " test started.");

	}

	public void onTestSuccess(ITestResult result) {
	//	test.log(Status.PASS, result.getName() + " passed.");
		Reporter.log(result.getMethod().getMethodName() + " test passed.");
		addScreenCaptureInReporter(result.getMethod().getMethodName());
		log.info(result.getMethod().getMethodName() + " test passed.");

	}

	public void onTestFailure(ITestResult result) {
	//	test.log(Status.FAIL, result.getThrowable() + " failed.");
		Reporter.log(result.getMethod().getMethodName() + " test failed. " + result.getThrowable());
		addScreenCaptureInReporter(result.getMethod().getMethodName());
		log.error(result.getMethod().getMethodName() + " test failed. " + result.getThrowable());

	}

	public void onTestSkipped(ITestResult result) {
	//	test.log(Status.SKIP, result.getThrowable() + " skipped.");
		Reporter.log(result.getMethod().getMethodName() + " test skipped. " + result.getThrowable());
		addScreenCaptureInReporter(result.getMethod().getMethodName());
		log.warn(result.getMethod().getMethodName() + " test skipped. " + result.getThrowable());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
	//	extent = ExtentManager.getInstance();
	//	test = extent.createTest(context.getName());
		Reporter.log(context.getName() + " test started.");
		log.info(context.getName() + " test started.");
		

	}

	public void onFinish(ITestContext context) {
	//	extent.flush();
		log.info(context.getName() + " test Finished.");
	}
	
	public void addScreenCaptureInReporter(String methodName)
	{
		(new TestBase()).captureScreen(methodName, TestBase.driver);
	}

}
