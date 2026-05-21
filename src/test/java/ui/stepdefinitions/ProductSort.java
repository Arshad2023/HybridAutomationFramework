package ui.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.Base.BaseTest;
import ui.hooks.Hooks;
import ui.utils.ActionMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSort extends BaseTest {

    @When("User selects product sort option as {string}")
    public void user_selects_product_sort_option_as(String sortOption) {
        ui.pages.ProductSort productSort = new ui.pages.ProductSort(BaseTest.getDriver());
        productSort.selectSortOption(sortOption);
        Hooks.getTest().pass("Selected sorting option: " + sortOption);
    }
    @Then("Products should be sorted from low to high price")
    public void products_should_be_sorted_from_low_to_high_price() {
        ui.pages.ProductSort productSort = new ui.pages.ProductSort(BaseTest.getDriver());
        List<Double> actualPrices = productSort.getDisplayedProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices);
        Hooks.getTest().pass("Products are sorted correctly: " + actualPrices);
        ActionMethod.captureScreenshot("Products_Sorted_Low_To_High", "Products sorted low to high validated");
    }
}
