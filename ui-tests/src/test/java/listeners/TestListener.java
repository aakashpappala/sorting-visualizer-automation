package listeners;

import com.aventstack.extentreports.*;
import org.testng.*;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getName()));
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}