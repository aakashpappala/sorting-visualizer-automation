package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SortingPage;

public class SortingTest extends BaseTest {

    @DataProvider(name = "sortingData")
    public Object[][] getData() {

        String[] algorithms = {"1", "2", "3", "4", "5"};
        String[] sizes = {"5", "10", "20"};
        String[] speeds = {"1", "2", "3"};

        Object[][] data = new Object[algorithms.length * sizes.length * speeds.length][3];

        int index = 0;

        for (String algo : algorithms) {
            for (String size : sizes) {
                for (String speed : speeds) {
                    data[index++] = new Object[]{algo, size, speed};
                }
            }
        }

        return data;
    }

    @Test(dataProvider = "sortingData")
    public void testSorting(String algo, String size, String speed) {

        SortingPage page = new SortingPage(getDriver());

        page.waitForPageToLoad();

        page.selectAlgorithm(algo);
        page.selectSize(size);
        page.selectSpeed(speed);

        page.clickGenerate();

        int before = page.getBarsCount();

        page.clickSort();

        page.waitForSortingComplete();

        int after = page.getSortedBarsCount();

        Assert.assertEquals(after, before);

        System.out.println("PASS → Algo:" + algo + " Size:" + size + " Speed:" + speed);
    }
}