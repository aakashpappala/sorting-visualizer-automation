package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.SortingPage;

public class SortingSteps extends BaseTest {

    SortingPage page;

    @Given("user opens sorting page")
    public void openPage() {
        setup();
        page = new SortingPage(getDriver());
        page.waitForPageToLoad();
    }

    // 🔥 NEW METHOD (IMPORTANT)
    @When("user runs all combinations for algorithm {string}")
    public void runAll(String algo) {

        String[] sizes = {"5", "10", "15"};
        String[] speeds = {"1", "2", "3"};

        for (String size : sizes) {
            for (String speed : speeds) {

                System.out.println("Running → Algo: " + algo +
                        " Size: " + size +
                        " Speed: " + speed);

                page.selectAlgorithm(algo);
                page.selectSize(size);
                page.selectSpeed(speed);

                page.clickGenerate();
                page.clickSort();

                page.waitForSortingComplete();
            }
        }
    }

    @Then("sorting should complete")
    public void verifySorting() {
        tearDown();
    }
}