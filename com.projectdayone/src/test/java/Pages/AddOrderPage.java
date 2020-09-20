package Pages;

import Utilities.CommonUtils;
import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOrderPage {
    WebDriver driver = Driver.getDriver(CommonUtils.getProperty("browser"));

    public AddOrderPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantity;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement calculateButton;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtTotal")
    public WebElement totalPrice;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
    public WebElement pricePerUnit;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerName;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement street;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement city;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement state;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zip;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumber;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expireDate;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement visaCardRadio;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(tagName = "strong")
    public WebElement addMessage;

    public void provideQuantity(Integer quantity) {
        this.quantity.clear();
        this.quantity.sendKeys("" + quantity);
        this.calculateButton.click();
    }

    public Integer validatePrice(Integer quantity) {
        Integer totalPriceInt = Integer.parseInt(this.pricePerUnit.getAttribute("value"));
        Integer total = quantity * totalPriceInt;
        return total;
    }
}
