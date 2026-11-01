package pagepkg;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addcart1;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement addcart2;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement addcart3;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    WebElement addcart4;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    WebElement addcart5;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    WebElement addcart6;

    @FindBy(className = "shopping_cart_link")
    WebElement cart;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    public CartPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void AddtoCart() {

        wait.until(ExpectedConditions.elementToBeClickable(addcart1)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addcart2)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addcart3)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addcart4)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addcart5)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addcart6)).click();

        wait.until(ExpectedConditions.visibilityOf(cartBadge));

        System.out.println("Cart count: " + cartBadge.getText());

        wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
    }

    public String getCartCount() {

        return wait.until(
            ExpectedConditions.visibilityOf(cartBadge)
        ).getText();
    }
}
