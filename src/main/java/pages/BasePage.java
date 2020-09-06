package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BasePage {

    protected Logger log;

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        changeLogger("Base page :");
    }
    public void validateTrue(WebElement element) {
        log.debug("Check element:" + element);
        Assert.assertTrue(element.isEnabled());
    }

    public void changeLogger(String name) {
        log = LogManager.getLogger(name);
    }
}
