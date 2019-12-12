package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Driver;

public class TestClass {
    private WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
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
    public void testLoginPage() {
        String URL = "https://app.mjplatform.com/login";
        driver.get(URL);
    }
}
