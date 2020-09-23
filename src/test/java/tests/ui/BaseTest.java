package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public abstract class BaseTest {


    protected final Logger log = LogManager.getLogger("Test");

    protected WebDriver driver;


    @Before
    public void setUp() throws Exception {
        log.debug("Browser:"+System.getProperty("browser", "chrome"));
        if (System.getProperty("remote", "no").equals("no")) {
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
        }
        else if (System.getProperty("remote", "no").equals("yes")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (System.getProperty("browser", "chrome")) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    capabilities.setVersion("85.0");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    capabilities.setVersion("80.0");
                    break;
            }
            capabilities.setCapability("enableVNC", false);
            capabilities.setCapability("enableVideo", false);

            try {
                this.driver = new RemoteWebDriver(
                        URI.create("http://192.168.0.113:4444/wd/hub").toURL(),
                        capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
            throw new Exception("No driver selected");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://github.com/login");

    }

    @After
    public void ternDown(){
        driver.quit();
    }
}
