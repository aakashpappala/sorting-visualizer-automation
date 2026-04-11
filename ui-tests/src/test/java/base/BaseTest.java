package base;

import core.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

// 🔥 ALLURE IMPORT
import io.qameta.allure.Attachment;

public class BaseTest {

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");

        WebDriver driver = new ChromeDriver(options);

        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();
        driver.get("file:///C:/Users/DELL/sorting-visualizer/index.html");
    }

    @AfterMethod
    public void tearDown() {

        try {
            attachScreenshot();
        } catch (Exception e) {
            System.out.println("Screenshot failed");
        }

        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    // 🔥 ALLURE SCREENSHOT ATTACHMENT
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}