package pagepkg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class product_page {
	  WebDriver driver;
		@FindBy(id = "react-burger-menu-btn")
		WebElement logoutmenu;
		
		@FindBy(id = "logout_sidebar_link")
		WebElement logoutbtn;

	    public product_page(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void printProductNames() {

	    	List<WebElement> products = driver.findElements(
	    	        By.className("inventory_item")
	    	    );
	    	    for (WebElement product : products) 
	    	    {
	
	    	        String name = product.findElement(By.className("inventory_item_name")).getText();
	   	        String description = product.findElement(By.className("inventory_item_desc")).getText();
	   	        String price = product.findElement(By.className("inventory_item_price")).getText();
	    	        System.out.println("Product Name: " + name);
	    	        System.out.println("Description: " + description);
	    	        System.out.println("Price: " + price);
	    	        System.out.println("--------------------------------");

	        }
	    }
	    
	    public boolean verifyLogo() 
	    {

	        WebElement logo = driver.findElement(By.className("app_logo"));

	        return logo.isDisplayed();
	    }
	    
	 // Method to select a dropdown option
	    public void sortProducts(String option) throws InterruptedException
	    {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
	        Select select = new Select(dropdown);
	        select.selectByVisibleText(option);
	    }

	    // Method to verify price sorting
	    public boolean verifyProductSorting()
	    {
	        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
	        double previousPrice = 0;
	        for (WebElement element : priceElements)
	        {
	            double currentPrice = Double.parseDouble(element.getText().replace("$", "").trim());
	            if (currentPrice < previousPrice)
	            {
	                return false;
	            }
	            previousPrice = currentPrice;
	        }
	        return true;
	 }
	    public void logout() {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Click the hamburger menu
	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.id("react-burger-menu-btn"))).click();

	        // Wait until the Logout link is visible and clickable
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.id("logout_sidebar_link")));

	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.id("logout_sidebar_link"))).click();
	    }    
}
