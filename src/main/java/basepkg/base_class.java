package basepkg;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class base_class {

    public WebDriver driver;
    public String Url = "https://www.saucedemo.com/";

    public static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void startReport() {

        new File("./reports").mkdirs();

        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/demoreport.html");

        reporter.config().setDocumentTitle("AutomationReport");
        reporter.config().setReportName("Functional Testing");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("hostname", "localhost");
        extent.setSystemInfo("os", "Windows 11");
        extent.setSystemInfo("tester name", "Gopika");
        extent.setSystemInfo("browsername", "Chrome");
    }

    
    @BeforeMethod
    public void start(Method method)
    {

        test = extent.createTest(getClass().getSimpleName() + " - " + method.getName());

        ChromeOptions options = new ChromeOptions();

        // Disable Chrome password manager pop-ups
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Url);
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) throws IOException 
    {

        if (result.getStatus() == ITestResult.FAILURE) 
        {
            test.log(Status.FAIL, result.getThrowable());

            if (driver != null) 
            {
                new File("./screenshots").mkdirs();
                String screenshotPath = "./screenshots/" + result.getName() + "_"
                    + System.currentTimeMillis() + ".png";
                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileHandler.copy(source, new File(screenshotPath));
                test.addScreenCaptureFromPath(new File(screenshotPath).getAbsolutePath());
                System.out.println("Screenshot saved: " + screenshotPath);
            }
        }
        else if (result.getStatus() == ITestResult.SUCCESS) 
        {

            test.log(Status.PASS, "Test passed successfully");
        }
        else if (result.getStatus() == ITestResult.SKIP) 
        {

            test.log(Status.SKIP, "Test skipped");
        }

        if (driver != null) 
        {
            driver.quit();
        }
    }

    @AfterSuite
    public void finishReport() 
    {

        if (extent != null) 
        {
            extent.flush();
        }
    }
}
