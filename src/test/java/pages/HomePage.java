package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {
    WebDriver driver = Driver.getDriver("chrome");

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    /*
        @CacheLookup - makes the lookup for the element happen just once.
        After that, it will be cached in the variable and accessible much faster.
    */

    @FindBy(how = How.ID, using = "email")
    @CacheLookup
    public WebElement login;

    @FindBy(how = How.ID, using = "pass")
    @CacheLookup
    public WebElement password;

    @FindBy(how = How.NAME, using = "login")
    @CacheLookup
    public WebElement loginBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='email_container']//div[contains(text(), 'email')]")
    @CacheLookup
    public WebElement errorMsg;
}
