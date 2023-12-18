package automationFramework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Test results");
        reporter.config().setDocumentTitle("Test Results");

        System.out.println("hello");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Kaustubh Thakare");
//        extent.createTest(path);
        return extent;
    }

}
