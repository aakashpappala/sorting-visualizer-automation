package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SortingPage;

import java.util.*;

public class SortingTest extends BaseTest {

    @Test(enabled = false)
    public void testSorting() {

        SortingPage page = new SortingPage(getDriver());

        page.waitForPageToLoad();
        page.selectAlgorithm("1");
        page.selectSize("10");
        page.selectSpeed("2");

        page.clickGenerate();

        List<Integer> before = page.getValues();

        page.clickSort();
        page.waitForSortingComplete();

        List<Integer> after = page.getValues();

        List<Integer> expected = new ArrayList<>(before);
        Collections.sort(expected);

        Assert.assertEquals(after, expected);
    }
}