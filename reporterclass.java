package basepkg;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class reporterclass {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";

    ExtentSparkReporter reporter;
    public static ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void sam() {

        // Create reports folder if it does not exist
        new File("./reports").mkdirs();
        reporter = new ExtentSparkReporter("./reports/demoreport.html");
        reporter.config().setDocumentTitle("AutomationReport");
        reporter.config().setReportName("Functional Testing");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("hostname", "localhost");
        extent.setSystemInfo("os", "windows11");
        extent.setSystemInfo("tester name", "Gopika");
        extent.setSystemInfo("browsername", "chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void prepare() {
        driver.get(url);
    }

    // Single AfterMethod for PASS, FAIL and SKIP
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) 
        {

            test.log(Status.FAIL, "Test failed: " + result.getName());
            test.log(Status.FAIL, result.getThrowable());

            String screenshotPath = screenshotMethod(driver,result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
            System.out.println("Test Failed: " + result.getName());
            System.out.println("Screenshot saved: " + screenshotPath);
        }

        else if (result.getStatus() == ITestResult.SKIP)
        {

            if (test != null) 
            {
                test.log(Status.SKIP, "Test skipped: " + result.getName());
            }

            System.out.println("Test Skipped: " + result.getName());
            }

        		else if (result.getStatus() == ITestResult.SUCCESS) 
        		{

	            test.log(Status.PASS, "Test passed: " + result.getName());
	            System.out.println("Test Passed: " + result.getName());
        		}
    }

    @AfterSuite
    public void teardown() 
    {

        if (extent != null) 
        {
            extent.flush();
        }

        if (driver != null) 
        {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }

    public static String screenshotMethod(
        WebDriver driver,
        String screenshotname
    ) throws IOException 
    {

        File src = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.FILE);
        String destination = "./reports/" + screenshotname + ".png";
        FileHandler.copy(src, new File(destination));
        return destination;
    }
    
    public static void attachScreenshot(
            ExtentTest test, String screenshotPath) throws IOException {
        test.addScreenCaptureFromPath(screenshotPath);
    }
}