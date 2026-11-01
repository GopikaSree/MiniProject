
package testpkg;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.base_class;

import pagepkg.login_page;
import pagepkg.product_page;
import pagepkg.CartPage;
import pagepkg.chkout_page;

public class CartTest extends base_class {

    // Reusable login method
    public void loginToSauceDemo() {

        login_page login = new login_page(driver);
        login.start("standard_user", "secret_sauce");
        login.login();
    }

    // Test 1: Product details and sorting
    @Test
    public void productDetailsAndSorting() throws InterruptedException {

        loginToSauceDemo();
        product_page products = new product_page(driver);
        // Logo verification log
        test.info("Starting product page logo verification");
        Assert.assertTrue(
            products.verifyLogo(),
            "Product page logo is not displayed");
        test.pass("Product page logo verification passed");
        System.out.println("Product page logo verification passed");
        
        // Product details log
        test.info("Displaying product details");
        System.out.println("Displaying product details:");
        products.printProductNames();
        test.pass("Product details displayed successfully");
        System.out.println("Product details displayed successfully");

        // Dropdown and sorting log
        test.info("Starting product sorting verification");
        System.out.println("Starting product sorting verification");
        products.sortProducts("Price (low to high)");
        Assert.assertTrue(
            products.verifyProductSorting(),
            "Products are not sorted by price");
        test.pass("Product sorting verification passed");
        System.out.println("Product sorting verification passed");
    }

    // Test 2: Add products and verify cart
    @Test
    public void addProductsToCart() {
        loginToSauceDemo();
        CartPage cart = new CartPage(driver);
        cart.AddtoCart();
        Assert.assertEquals(
            cart.getCartCount(),
            "6",
            "Cart count is incorrect"
        );
    }

    // Test 3: Complete checkout
    @Test
    public void checkoutTest() {
        loginToSauceDemo();
        CartPage cart = new CartPage(driver);
        cart.AddtoCart();
        Assert.assertEquals(cart.getCartCount(), "6");
        chkout_page checkout = new chkout_page(driver);
        checkout.checkout();
        checkout.EnterChkoutDetails("Gopika", "Menon","695003");
        checkout.continueclick();
        checkout.finish();
        Assert.assertEquals(
            checkout.getCompleteMessage(),
            "Thank you for your order!",
            "Order completion failed"
        );
    }

    // Test 4: Logout
    @Test
    public void verifyLogout() {
        loginToSauceDemo();
        product_page products = new product_page(driver);
        products.logout();
        boolean actual = driver.findElement(org.openqa.selenium.By.className("login_logo")).isDisplayed();
        Assert.assertTrue(actual, "Logout failed");
    }
}
