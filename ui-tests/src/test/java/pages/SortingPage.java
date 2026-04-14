package pages;

import core.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Collectors;

public class SortingPage {

    WebDriver driver;
    WaitUtils waitUtils;

    public SortingPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    By algo = By.cssSelector(".algo-menu");
    By size = By.cssSelector(".size-menu");
    By speed = By.cssSelector(".speed-menu");
    By generate = By.id("random");
    By sort = By.cssSelector(".start");

    By bars = By.cssSelector(".cell");

    public void waitForPageToLoad() {
        waitUtils.waitForElementVisible(algo);
    }

    public void selectAlgorithm(String value) {
        new Select(driver.findElement(algo)).selectByValue(value);
    }

    public void selectSize(String value) {
        new Select(driver.findElement(size)).selectByValue(value);
    }

    public void selectSpeed(String value) {
        new Select(driver.findElement(speed)).selectByValue(value);
    }

    public void clickGenerate() {
        WebElement btn = waitUtils.waitForElementClickable(generate);
        btn.click();
        waitUtils.waitForElementsMoreThanZero(bars);
    }

    public void clickSort() {
        WebElement sortBtn = waitUtils.waitForElementClickable(sort);
        sortBtn.click();
    }

    public List<Integer> getValues() {
        return driver.findElements(bars)
                .stream()
                .map(e -> Integer.parseInt(e.getAttribute("value")))
                .collect(Collectors.toList());
    }

    // 🔥 FINAL PERFECT WAIT (GREEN BASED)
    public void waitForSortingComplete() {

        while (true) {

            List<WebElement> elements = driver.findElements(bars);

            boolean allDone = true;

            for (WebElement e : elements) {

                String classes = e.getAttribute("class");

                // 🔥 FINAL CORRECT CHECK
                if (!classes.contains("done")) {
                    allDone = false;
                    break;
                }
            }

            if (allDone && elements.size() > 0) {
                return; // 🔥 IMMEDIATE EXIT
            }

            try {
                Thread.sleep(10); // ⚡ ultra fast
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public boolean isSortedCompletely() {

        List<WebElement> elements = driver.findElements(bars);

        for (WebElement e : elements) {

            String color = e.getCssValue("background-color");

            if (!(color.contains("0, 128, 0") || color.contains("0, 255, 0"))) {
                return false;
            }
        }

        return true;
    }
}