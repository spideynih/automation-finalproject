package ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import config.EnvConfig;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent = ExtentReportManager.getInstance();
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        driver = BasePage.initDriver(browser);
        driver.get(EnvConfig.BASE_URL + "/login");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String path = ScreenshotUtils.takeScreenshot(driver, result.getName());
                extentTest.fail(result.getThrowable())
                        .addScreenCaptureFromPath(path, "Screenshot on Failure");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                String path = ScreenshotUtils.takeScreenshot(driver, result.getName());
                extentTest.pass("Test passed")
                        .addScreenCaptureFromPath(path, "Screenshot on Pass");
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip("Test skipped: " + result.getThrowable());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}