package base;

import core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");

        WebDriver driver = new ChromeDriver(options);

        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();
        driver.get("///C:/Users/DELL/sorting-visualizer/index.html"); // change if needed
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}