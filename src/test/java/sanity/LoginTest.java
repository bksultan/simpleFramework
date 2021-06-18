package sanity;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.Driver;
import utilities.Utilities;

import java.util.UUID;

public class LoginTest {
    WebDriver driver = Driver.getDriver("chrome");
    HomePage homePage = new HomePage();
    Utilities utilities = new Utilities();

    @BeforeTest
    public void test1() {
        driver.navigate().to("https://www.facebook.com/");
    }

    @Test
    public void errorLogin() {
        utilities.sendText(homePage.login, UUID.randomUUID().toString());
        utilities.sendText(homePage.password, UUID.randomUUID().toString());
        utilities.clickOnElement(homePage.loginBtn);
        utilities.assertElementIsVisible(homePage.errorMsg);
    }
}
