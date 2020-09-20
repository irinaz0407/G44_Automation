package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public abstract class BaseTest {


    protected final Logger log = LogManager.getLogger("Test");

    protected WebDriver driver;

    @Before
    public void setUp() {

        switch (System.getProperty("browser", "chrome")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
//                System.getProperty("user.dir") +
                        "D:\\autotest\\drivers\\chrome\\v85\\chromedriver.exe");
                this.driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver",
//                System.getProperty("user.dir") +
                        "D:\\autotest\\drivers\\firefox\\v0.27.0\\geckodriver.exe");
                this.driver = new FirefoxDriver();
                break;

            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://github.com/login");

        }

    @After
    public void ternDown(){
        driver.quit();
    }
}
