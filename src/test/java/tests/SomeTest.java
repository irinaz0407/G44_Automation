package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class SomeTest {

    private final Logger LOG = LogManager.getLogger("Web driver test");

    private WebDriver driver;

    @Before
    public void Before() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") +
                        "\\src\\main\\resources\\drivers\\chrome\\v84\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void someTest() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qalight.com.ua/o-nas/qalight-eto/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
        driver.quit();
    }

}