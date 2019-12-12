package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Driver;

public class TestClass {
    private WebDriver driver;
    private String browserName;

//    webelements
    private WebElement username;
    private WebElement password;
    private WebElement loginIntoAccount;

    @BeforeClass
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

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void atestLoginPage(){
        String URL = "https://app.mjplatform.com/login";
        driver.get(URL);

        username = driver.findElement(By.cssSelector("#name"));
        password = driver.findElement(By.cssSelector("#password"));
        loginIntoAccount = driver.findElement(By.xpath("//button[.='Log into  Account']"));
        WebElement browserIncompatible = driver.findElement(By.xpath("//div[contains(text(),'your browser is most likely out of date or incompatible')]"));

        if (browserName.toLowerCase().equals("ie")) {
            Assert.assertTrue(browserIncompatible.isDisplayed());
        }

        Assert.assertTrue(username.isDisplayed());
        Assert.assertTrue(password.isDisplayed());
        Assert.assertTrue(loginIntoAccount.isDisplayed());
    }

    @Test
    public void testInvalidCredentials() throws InterruptedException {
        username.sendKeys("username");
        password.sendKeys("password");
        loginIntoAccount.click();

//        expected elements
        WebElement invalidUsernameOrPassword = driver.findElement(By.xpath("//div[contains(text(), 'Invalid Username Or Password')]"));
        WebElement usernameHasError = driver.findElement(By.xpath("//div[contains(@class,'has-error')]/label[.='Username']"));
        WebElement passwordHasError = driver.findElement(By.xpath("//div[contains(@class,'has-error')]/label[.='Password']"));

        Assert.assertTrue(invalidUsernameOrPassword.isDisplayed());
        Assert.assertTrue(usernameHasError.isDisplayed());
        Assert.assertTrue(passwordHasError.isDisplayed());
        System.out.println("Waiting 5 seconds");
        Thread.sleep(5000);
    }

}
