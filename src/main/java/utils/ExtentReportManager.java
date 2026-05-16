package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("build/reports/extent/ExtentReport.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Final Project QA - Automation Report");
            spark.config().setReportName("LMS B2B Dibimbing - Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Tester", "Adinda Nimas");
        }
        return extent;
    }
}