package pageObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utility.BrowserFactory;
import utility.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public Properties configProp;
    public ExtentReports reports;
    public ExtentTest logger;
    @BeforeSuite
    public void setUpSuite()
    {
        configProp = new Properties();
        ExtentSparkReporter extend = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/report"+Helper.getCurrentDateTime()+".html"));
        reports = new ExtentReports();
        reports.attachReporter(extend);

    }
   // @Parameters("browser")
    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        FileInputStream configPropfile = new FileInputStream("./Config/Config.properties");
        try {
            configProp.load(configPropfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = BrowserFactory.startApplication(driver,configProp.getProperty("Browser"), configProp.getProperty("URL"));
        //driver = BrowserFactory.startApplication(driver,browser, configProp.getProperty("URL"));
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        String methodName = result.getName();
        if(result.getStatus() == ITestResult.FAILURE)
        {
            Helper.takeScreenShot(driver,methodName);
            logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.takeScreenShot(driver,methodName)).build());
        }
        BrowserFactory.quitBrowser(driver);
        reports.flush();
    }
}
