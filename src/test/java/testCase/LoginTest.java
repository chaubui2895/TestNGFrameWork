package testCase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.BaseClass;
import pageObject.LoginPage;
import utility.ExcelDataProvider;
import utility.ExcelReader;

import java.util.ArrayList;
import java.util.Iterator;

public class LoginTest extends BaseClass {


    // get data by ExcelDataProvider class
    @DataProvider(name = "test1data")
    public Object[][] getData() {
        ExcelDataProvider excelDataProvider = new ExcelDataProvider();
        Object data[][] = excelDataProvider.getDataFromExcel("./TestData/testData.xlsx", "Login");
        return data;
    }

    // get data by ExcelReader
    @DataProvider(name = "test2data")
    public Object[][] getData2()
    {
        ExcelReader excelReader = new ExcelReader();
        Object data[][] = excelReader.getData("./TestData/testData.xlsx", "Login");
        return data;
    }

    @DataProvider(name = "test3data")
    public Iterator<Object[]> getData3()
    {
        ExcelDataProvider excelDataProvider = new ExcelDataProvider();
        ArrayList<Object[]>data = excelDataProvider.getDataXlsx("./TestData/testData.xlsx","Login");
        return data.iterator();
    }
    @DataProvider(name = "testData")
    public Iterator<Object[]> getdataMix()
    {
        ExcelDataProvider excelDataProvider = new ExcelDataProvider();
        ArrayList<Object[]>data = excelDataProvider.getDataMix("./TestData/testData.xlsx","Login");
        return data.iterator();
    }
    @Test(dataProvider = "testData")
    public void loginApp(String username, String password) {
        logger = reports.createTest("Login to homepage");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(username, password);
        logger.pass("Login successfull");
    }
}
