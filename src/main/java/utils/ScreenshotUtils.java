package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = "build/reports/extent/screenshots/";
        String fileName = testName + "_" + timestamp + ".png";
        String fullPath = folderPath + fileName;

        try {
            new File(folderPath).mkdirs();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return relative path dari lokasi HTML report
        return "screenshots/" + fileName;
    }
}