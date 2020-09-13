package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BasePage {

    protected Logger log;

    protected WebDriver driver;
    protected Properties connProps ;

    public BasePage(WebDriver driver){
        this.driver = driver;
        changeLogger("Base page :");
        connProps = new Properties();
        try {
            connProps.load(new FileInputStream(new File(System.getProperty("user.dir") +
                    "\\src\\test\\resources\\connection.properties")));
        } catch (IOException e) {
            log.error(e);
            connProps.setProperty("git.username","dummy");
            connProps.setProperty("git.password","dummy");
        }
    }
    public void validateTrue(WebElement element) {
        log.debug("Check element:" + element);
        Assert.assertTrue(element.isEnabled());
    }

    public void changeLogger(String name) {
        log = LogManager.getLogger(name);
    }
}
