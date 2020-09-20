package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static Utilities.Constants.*;

public class CommonUtils {

    public static void takeScreenshot(WebDriver driver, String testName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "src/test/java/screenshotFile/" + testName + System.currentTimeMillis() + ".png";
        File destScreenshot = new File(destination);
        FileUtils.copyFile(screenshot, destScreenshot);
    }

    public static Properties readPropertyFile(String pathToPropertyFile) throws IOException {
        Properties properties = new Properties();
        File propFile = new File(pathToPropertyFile);
        FileInputStream fileInputStream = new FileInputStream(propFile);
        properties.load(fileInputStream);

        return properties;
    }

    public static String getProperty(String key) {
        try {
            Properties properties = readPropertyFile("src/test/resources/Config/configurations.properties");
            String value = properties.getProperty(key);
            return value;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static WebElement fluentWait(int duration, int pollingTime, By locator) {
        Wait<WebDriver> fluentWait = new FluentWait<>(Driver.getDriver(getProperty(BROWSER)))
                .withTimeout(Duration.ofSeconds(duration)) //wait for 30 sec
                .pollingEvery(Duration.ofSeconds(pollingTime)) //check every 3 sec
                .ignoring(NoSuchElementException.class); //ignore NoSuchElementException

        WebElement element = fluentWait.until(driver -> driver.findElement(locator));
        return element;
    }
}
