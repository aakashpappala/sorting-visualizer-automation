package base;

import core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.manage().window().maximize();
        driver.get("http://sorting-visualizer-aakash.s3-website.ap-south-1.amazonaws.com");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}