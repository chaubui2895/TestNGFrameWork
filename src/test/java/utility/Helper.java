package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Helper {

     public static String takeScreenShot(WebDriver driver, String methodName)
    {
        //String screenshotPath = System.getProperty("user.dir")+"/Screenshots/"+methodName+"_"+getCurrentDateTime()+".png";
        String screenshotPath = "C:/Users/INNOTECH_61/HybridFramework/Screenshots/"+methodName+"_"+getCurrentDateTime()+".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(src,new File(screenshotPath));
            System.out.println("ScreenShot captured.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't take the screenshot");
        }
        return screenshotPath;
    }
    public static String getCurrentDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
