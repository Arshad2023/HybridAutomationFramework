package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.Base.BasePage;
public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "(//button[text()='Add to cart'])[1]")
    WebElement firstAddToCartButton;

    @FindBy(xpath = "(//button[text()='Add to cart'])[2]")
    WebElement secondAddToCartButton;

    @FindBy(xpath = "(//button[text()='Add to cart'])[3]")
    WebElement thirdAddToCartButton;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement cartBadge;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement cartIcon;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button")
    WebElement backpackAddButton;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/ancestor::div[@class='inventory_item']//button")
    WebElement bikeLightAddButton;

    public void addThreeProductsToCart() {
        action.clickElement(firstAddToCartButton, "Add to cart button for the first product");
        action.clickElement(secondAddToCartButton, "Add to cart button for the second product");
        action.clickElement(thirdAddToCartButton, "Add to cart button for the second product");
        action.captureScreenshot("AddProduct", "Screenshot captured after adding 3 products");
    }
    public String getCartBadgeCount() {
        return action.getText(cartBadge, "Cart Badge");
    }
    public void openCartPage() {
        action.clickElement(cartIcon, "Cart Icon");
    }
    public void addBackpackAndBikeLightToCart() {
        action.clickElement(backpackAddButton, "Add to cart button for the product Back Pack");
        action.clickElement(bikeLightAddButton, "Add to cart button for the product Bike Light");
    }

}
