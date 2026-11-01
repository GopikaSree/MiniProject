package pagepkg;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class chkout_page {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "first-name")
    WebElement first_name;

    @FindBy(id = "last-name")
    WebElement last_name;

    @FindBy(id = "postal-code")
    WebElement postal_code;

    @FindBy(id = "finish")
    WebElement finish;

    @FindBy(id = "continue")
    WebElement continue1;

    @FindBy(id = "checkout")
    WebElement checkout;

    @FindBy(className = "complete-header")
    WebElement completeHeader;

    public chkout_page(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void checkout() {

        wait.until(ExpectedConditions.elementToBeClickable(checkout)).click();
    }

    public void EnterChkoutDetails(
        String firstname,
        String lastname,
        String postalcode) {

        wait.until(ExpectedConditions.visibilityOf(first_name)).sendKeys(firstname);

        last_name.sendKeys(lastname);
        postal_code.sendKeys(postalcode);
    }

    public void continueclick() {

        wait.until(ExpectedConditions.elementToBeClickable(continue1)).click();
    }

    public void finish() {

        wait.until(ExpectedConditions.elementToBeClickable(finish)).click();
    }

    public String getCompleteMessage() {

        return wait.until(ExpectedConditions.visibilityOf(completeHeader)).getText();
    }
}
