package pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "Email")
    @CacheLookup
    WebElement email;

    @FindBy(name="Password")
    @CacheLookup
    WebElement pass;

    @FindBy(xpath = "//button[@type='submit']")
    @CacheLookup
    WebElement btnLogin;

    public void Login(String username , String password)
    {
        email.clear();
        email.sendKeys(username);
        pass.clear();
        pass.sendKeys(password);
        btnLogin.click();
    }
}
