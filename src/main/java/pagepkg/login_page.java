package pagepkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class login_page {

WebDriver driver;
	
	@FindBy(id = "user-name")
	WebElement user;
	
	@FindBy(id = "password")
	WebElement pass;
	
	@FindBy(id = "login-button")
	WebElement button;
	
	public login_page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	public void start(String username, String password)
	{
		user.sendKeys(username);
		pass.sendKeys(password);
	}
	
	public void login()
	{
		button.click();
	}
}
