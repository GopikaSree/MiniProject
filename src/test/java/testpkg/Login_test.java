package testpkg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basepkg.base_class;
import pagepkg.login_page;
import UtilitiesPkg.ExcelUtils;

public class Login_test extends base_class {

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {

        String path =
            "C:\\Users\\gopik\\Downloads\\UserCredentials.xlsx";

        return ExcelUtils.getTestData(path, "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void LoginTest(String username, String password) {

        test.info("Starting login test");
        test.info("Testing username: " + username);
        
        System.out.println("Starting login test for the user :" + username );

        login_page login = new login_page(driver);

        login.start(username, password);
        test.info("Entered username and password");

        login.login();
        test.info("Clicked Login button");

        boolean loginStatus =
                driver.getCurrentUrl().contains("inventory");

        if(!loginStatus)
        {
        		System.out.println("Login failed for user: " +username);
        }
        Assert.assertTrue(
            loginStatus,
            "Login failed for user: " + username
        );

        test.pass("Login successful for user: " + username);
        System.out.println("Login successful for user: "+username);
    }
    @Test
    public void titleVerification() {

        test.info("Starting title verification");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";

        Assert.assertEquals(actualTitle, expectedTitle,
                "Title verification failed");

        String message = "Title verification passed. Actual title: " + actualTitle;

        test.pass(message);
        System.out.println("PASS: " + message);
    }
    @Test
    public void logoVerification() {

        test.info("Starting logo verification");

        boolean logoStatus = driver.findElement(
                By.className("login_logo")).isDisplayed();

        Assert.assertTrue(logoStatus, "Logo is not displayed");

        String message = "Logo verification passed";

        test.pass(message);
        System.out.println("PASS: " + message);
    }

    @Test
    public void buttonVerification() {

        test.info("Starting login button verification");

        boolean buttonStatus = driver.findElement(
                By.id("login-button")).isDisplayed();

        Assert.assertTrue(buttonStatus, "Login button is not displayed");

        String message = "Login button verification passed";

        test.pass(message);
        System.out.println("PASS: " + message);
    }
}