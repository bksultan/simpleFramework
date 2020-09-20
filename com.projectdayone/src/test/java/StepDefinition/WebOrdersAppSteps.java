package StepDefinition;

import Pages.AddOrderPage;
import Utilities.ExcelUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utilities.CommonUtils;
import Utilities.Driver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebOrdersAppSteps {
    WebDriver driver = Driver.getDriver("chrome");
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    ListOfAllOrdersPage listOfAllOrdersPage = new ListOfAllOrdersPage();
    AddOrderPage addOrderPage = new AddOrderPage();
    OrderEditPage orderEditPage = new OrderEditPage();

    @Given("User navigates to WebOrders application")
    public void user_navigates_to_web_orders_application() {
        driver.get(CommonUtils.getProperty("WebOrdersURL"));
    }

    @When("User provides username {string} and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrdersLoginPage.logIn(CommonUtils.getProperty(username), CommonUtils.getProperty(password));
    }

    @When("User click on Order part")
    public void user_click_on_order_part() {
        listOfAllOrdersPage.ordersPart.click();
    }

    @When("User adds new order with data")
    public void user_adds_new_order_with_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        addOrderPage.quantity.clear();
        addOrderPage.quantity.sendKeys(data.get(0).get("Quantity").toString());
        addOrderPage.customerName.sendKeys(data.get(0).get("Customer name").toString());
        addOrderPage.street.sendKeys(data.get(0).get("Street").toString());
        addOrderPage.city.sendKeys(data.get(0).get("City").toString());
        addOrderPage.state.sendKeys(data.get(0).get("State").toString());
        addOrderPage.zip.sendKeys(data.get(0).get("Zip").toString());
        addOrderPage.visaCardRadio.click();
        addOrderPage.cardNumber.sendKeys(data.get(0).get("Card number").toString());
        addOrderPage.expireDate.sendKeys(data.get(0).get("Expire Date").toString());
    }

    @Then("User click on Process button and validates {string} message")
    public void user_click_on_process_button_and_validates_message(String message) {
        addOrderPage.processButton.click();
        Assert.assertTrue(addOrderPage.addMessage.isDisplayed());
        Assert.assertEquals(message, addOrderPage.addMessage.getText());
    }

    @When("User click View all orders part")
    public void user_click_view_all_orders_part() {
        listOfAllOrdersPage.allOrdersPart.click();
    }

    @Then("User created order is added to list")
    public void user_created_order_is_added_to_list(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        for (Map<String, Object> map : data) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    @When("User provides quantity {int}")
    public void user_provides_quantity(Integer quantity) {
        addOrderPage.provideQuantity(quantity);
    }

    @Then("Total price equals to price multiple to {int}")
    public void total_price_equals_to_price_multiple_to(Integer quantity) {
        Integer totalPriceInt = Integer.parseInt(addOrderPage.totalPrice.getAttribute("value"));
        Assert.assertEquals(totalPriceInt, addOrderPage.validatePrice(quantity));
    }

    @Then("User validates headers with {string} excel file expected result")
    public void user_validates_headers_with_excel_file_expected_result(String excelFile) {
        ExcelUtils.openExcelFile(excelFile, "sheet1");
        String expectedResult = ExcelUtils.getValue(1, 4);
        System.out.println(expectedResult);

        String[] results = expectedResult.split("\n");
        System.out.println(Arrays.toString(results));

        Assert.assertEquals(results[1], orderEditPage.productLabel.getText());
        Assert.assertEquals(results[2], orderEditPage.quantityLabel.getText());
        Assert.assertEquals(results[3], orderEditPage.unitPriceLabel.getText());
        Assert.assertEquals(results[4], orderEditPage.discountLabel.getText());
    }

    @Then("User updates {string} with {string}")
    public void user_updates_with(String excelFile, String status) {
        ExcelUtils.setValue(1, 6, status);
    }


    @When("User provides username {string} and password {string} without credentials")
    public void user_provides_username_and_password_without_credentials(String username, String password) {
        webOrdersLoginPage.logIn(username, password);
    }

    @Then("User creates all orders from {string} excel file")
    public void user_creates_all_orders_from_excel_file(String excelFile) {
        int lastRowNum = ExcelUtils.openExcelFile(excelFile, "sheet1").getLastRowNum();
        List<List<String>> excelData = new ArrayList<>();

        for (int i = 1; i <= lastRowNum; i++) {
            List<String> rowData = ExcelUtils.getRowValues(i);
            excelData.add(rowData);
        }

        for (int i = 0; i < excelData.size(); i++) {
            addOrderPage.quantity.clear();
            addOrderPage.quantity.sendKeys(excelData.get(i).get(0));
            addOrderPage.customerName.sendKeys(excelData.get(i).get(1));
            addOrderPage.street.sendKeys(excelData.get(i).get(2));
            addOrderPage.city.sendKeys(excelData.get(i).get(3));
            addOrderPage.state.sendKeys(excelData.get(i).get(4));
            addOrderPage.zip.sendKeys(excelData.get(i).get(5));
            addOrderPage.visaCardRadio.click();
            addOrderPage.cardNumber.sendKeys(excelData.get(i).get(6));
            addOrderPage.expireDate.sendKeys(excelData.get(i).get(7));
            addOrderPage.processButton.click();
        }
    }

    @Then("User validates that orders from {string} excel file is created")
    public void user_validates_that_orders_from_excel_file_is_created(String excelFile) {
        int lastRowNum = ExcelUtils.openExcelFile(excelFile, "sheet1").getLastRowNum();
        WebElement webTable = driver.findElement(By.xpath("//table[@class='SampleTable']"));
        List<WebElement> rows = webTable.findElements(By.tagName("tr"));
        List<WebElement> rowData;
        List<List<WebElement>> colData = new ArrayList();
        List<String> excelDataName = new ArrayList<>();
        List<String> webTableDataName = new ArrayList<>();

        for (int i = 2; i <= rows.size(); i++) {
            rowData = driver.findElements(By.xpath("//table[@class='SampleTable']//tr[" + i + "]/td"));
            colData.add(rowData);
        }
        for (int i = lastRowNum; i >= 0; i--) {
            excelDataName.add(ExcelUtils.getValue(i, 1));
        }
        for (int i = 0; i < lastRowNum; i++) {
            webTableDataName.add(colData.get(i).get(1).getText());
        }
        for (int i = 0; i < lastRowNum; i++) {
            Assert.assertEquals(excelDataName.get(i), webTableDataName.get(i));
        }
    }
}

