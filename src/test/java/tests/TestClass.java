package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Driver;

public class TestClass {
    private WebDriver driver;
    private String browserName;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
        browserName = browser;

        if (browser.equalsIgnoreCase("chrome")){
            driver = Driver.getDriver(Driver.driverName.chrome);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = Driver.getDriver(Driver.driverName.firefox);
        } else if (browser.equalsIgnoreCase("ie")) {
            driver = Driver.getDriver(Driver.driverName.ie);
        } else {
            throw new Exception("Browser is not correct: " + browser);
        }
    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void testLoginPage(){
        String URL = "https://app.mjplatform.com/login";
        driver.get(URL);

        WebElement username = driver.findElement(By.cssSelector("#name"));
        WebElement password = driver.findElement(By.cssSelector("#password"));
        WebElement loginIntoAccount = driver.findElement(By.xpath("//button[.='Log into  Account']"));
        WebElement browserIncompatible = driver.findElement(By.xpath("//div[contains(text(),'your browser is most likely out of date or incompatible')]"));

        if (browserName.toLowerCase().equals("ie")) {
            Assert.assertTrue(browserIncompatible.isDisplayed());
        }

        Assert.assertTrue(username.isDisplayed());
        Assert.assertTrue(password.isDisplayed());
        Assert.assertTrue(loginIntoAccount.isDisplayed());
    }
}
