package pages;

import core.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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
    By sortedBars = By.cssSelector(".cell.done");

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
        driver.findElement(generate).click();
        waitUtils.waitForElementsMoreThanZero(bars);
    }

    public void clickSort() {
        WebElement sortBtn = waitUtils.waitForElementClickable(sort);
        sortBtn.click();
    }

    public int getBarsCount() {
        return driver.findElements(bars).size();
    }

    public int getSortedBarsCount() {
        return driver.findElements(sortedBars).size();
    }

    // 🔥 STREAM BASED VALUES FETCH
    public List<Integer> getValues() {
        return driver.findElements(bars)
                .stream()
                .map(e -> Integer.parseInt(e.getAttribute("value")))
                .collect(Collectors.toList());
    }

    // 🔥 WAIT FOR SORT COMPLETE
    public void waitForSortingComplete() {

        long start = System.currentTimeMillis();

        while (true) {

            int total = driver.findElements(bars).size();
            int done = driver.findElements(sortedBars).size();

            if (total > 0 && total == done) {
                return;
            }

            if (System.currentTimeMillis() - start > 120000) {
                throw new RuntimeException("❌ Sorting timeout");
            }

            try {
                Thread.sleep(300);
            } catch (Exception ignored) {}
        }
    }

    public boolean isSortedCompletely() {
        int total = driver.findElements(bars).size();
        int done = driver.findElements(sortedBars).size();
        return total > 0 && total == done;
    }
}