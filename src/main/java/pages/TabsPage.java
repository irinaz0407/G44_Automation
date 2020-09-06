package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class TabsPage extends BasePage {

    public TabsPage(WebDriver driver) {
        super(driver);
        changeLogger("TabsPage ");
    }

    private final By tabsSearch = By.xpath("//span[@data-content]");

    public void printTabs() {
        validateTrue(this.driver.findElement(tabsSearch));
        List<WebElement> elList = driver.findElements(tabsSearch);
        log.info("Tabs found: " + elList.size());
        for (WebElement tab : Collections.unmodifiableList(elList)) log.info(tab.getText());
    }
}
