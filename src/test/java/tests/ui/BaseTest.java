package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {

    protected final Logger log = LogManager.getLogger("Test");

    protected WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") +
                        "\\src\\main\\resources\\drivers\\chrome\\v85\\chromedriver.exe");
        switch (System.getProperty("browser", "chrome")) {
            case "chrome":
                this.driver = new ChromeDriver();
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
