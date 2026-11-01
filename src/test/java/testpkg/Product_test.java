package testpkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import basepkg.base_class;
import pagepkg.login_page;
import pagepkg.product_page;


public class Product_test extends base_class {
	
	@Test
	public void LogoVerification1()
	{
	    boolean actual = driver.findElement(
	        By.className("login_logo")
	    ).isDisplayed();

	    Assert.assertTrue(actual, "Swag Labs logo is not displayed");
	    System.out.println("Product Logo verification Pass");		
	}

 @Test
    public void displayProductNames() {

        login_page login = new login_page(driver);
        login.start("standard_user", "secret_sauce");
        login.login();

        product_page  products = new product_page(driver);        
        products.printProductNames();
    }
 @Test
 public void productPageLogoVerification() {

     login_page login = new login_page(driver);

     login.start("standard_user", "secret_sauce");
     login.login();

     product_page products = new product_page(driver);

     boolean actual = products.verifyLogo();

     Assert.assertTrue(actual, "Product page logo is not displayed");

     System.out.println("Product Page Logo Verification Pass");
 }
 
 @Test
 public void verifyProductSorting() throws InterruptedException
 {
     login_page login = new login_page(driver);
     login.start("standard_user", "secret_sauce");
     login.login();

     product_page products = new product_page(driver);

     // Select dropdown option
     products.sortProducts("Price (low to high)");

     // Verify sorting
     boolean actual = products.verifyProductSorting();

     Assert.assertTrue(actual, "Products are not sorted by price");

     System.out.println("Product sorting verification Pass");
 }
 
 @Test
 public void verifyLogout()
 {
     // Login
     login_page login = new login_page(driver);
     login.start("standard_user", "secret_sauce");
     login.login();

     // Create product page object
     product_page products = new product_page(driver);

     // Logout
     products.logout();

     // Verify logout
     String actualUrl = driver.getCurrentUrl();

     Assert.assertTrue(actualUrl.contains("saucedemo.com"),
             "Logout failed");

     System.out.println("Logout successful");
 }

}
