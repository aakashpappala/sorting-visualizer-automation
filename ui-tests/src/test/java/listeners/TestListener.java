package listeners;

import com.aventstack.extentreports.*;
import core.DriverFactory;
import core.ScreenshotUtils;
import org.testng.*;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(),
                result.getMethod().getMethodName()
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}