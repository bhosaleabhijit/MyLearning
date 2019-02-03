package partner.helper.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		System.out.println("################################ In the extent manager #######################");
		if(extent==null)
		{
			return createInstance("test-output/extent.html");
		}
		return extent;
	}

	private static ExtentReports createInstance(String reportFileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFileName);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setReportName("Partner Site Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Partner site automation");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

}
