package tests.ui;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SystemPropertyTest {

    @FindBy(name = "name")
    private WebElement element;

    @Test
    public void checkProps(){
        System.out.println(System.getProperty("username", ""));
    }
}
