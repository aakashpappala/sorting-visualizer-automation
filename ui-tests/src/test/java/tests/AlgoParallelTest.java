package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.SortingPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

                List<Integer> before = page.getValues();

                page.clickSort();

                page.waitForSortingComplete();

                List<Integer> after = page.getValues();

                List<Integer> expected = new ArrayList<>(before);
                Collections.sort(expected);

                if (!after.equals(expected)) {
                    throw new RuntimeException("❌ FAIL Algo:" + algo);
                }

                System.out.println("✅ PASS Algo:" + algo +
                        " Size:" + size +
                        " Speed:" + speed);
            }
        }
    }
}