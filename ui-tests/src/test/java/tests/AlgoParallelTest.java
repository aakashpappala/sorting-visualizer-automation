package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.SortingPage;

@Listeners(listeners.TestListener.class)
public class AlgoParallelTest extends BaseTest {

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    @Parameters("algo")
    public void runAlgo(String algo) {

        SortingPage page = new SortingPage(getDriver());

        String[] sizes = {"5", "10", "20"};
        String[] speeds = {"1", "2", "3"};

        for (String size : sizes) {
            for (String speed : speeds) {

                page.waitForPageToLoad();

                page.selectAlgorithm(algo);
                page.selectSize(size);
                page.selectSpeed(speed);

                page.clickGenerate();

                sleep(500);

                int before = page.getBarsCount();

                page.clickSort();

                sleep(1000);

                page.waitForSortingComplete();

                int after = page.getSortedBarsCount();

                if (before != after) {
                    throw new RuntimeException("❌ FAIL Algo:" + algo);
                }

                System.out.println("✅ PASS Algo:" + algo +
                        " Size:" + size +
                        " Speed:" + speed);
            }
        }
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}