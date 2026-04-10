package listeners;

import core.DriverFactory;
import core.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverFactory.getDriver();
        String testName = result.getName();

        // Save file
        ScreenshotUtils.captureScreenshot(driver, testName);

        // Attach to Allure
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(testName, new ByteArrayInputStream(bytes));
    }
}