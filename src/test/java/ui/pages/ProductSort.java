package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.Base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ProductSort extends BasePage {
    public ProductSort(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//select[@data-test='product-sort-container']")
    WebElement sortDropdown;

    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    List<WebElement> productPrices;

    public void selectSortOption(String visibleText) {
        action.selectByVisibleText(sortDropdown, visibleText, "Sort Dropdown");
    }
    public List<Double> getDisplayedProductPrices() {
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement price : productPrices) {
            String priceText = action.getText(price, "Product Price").replace("$", "");
            actualPrices.add(Double.parseDouble(priceText));
        }
        return actualPrices;
    }
}
