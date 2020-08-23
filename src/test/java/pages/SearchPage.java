package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) { //конструктор
        super(driver);
        changeLogger("SearchPage");
    }

    private final By searchInput = By.xpath("//input[contains(@class,'header-search-input')]");
    private final By userLink = By.partialLinkText(System.getProperty("userName"));

    public void projectSearch() {
        validateTrue(this.driver.findElement(searchInput));
        this.driver.findElement(searchInput).sendKeys("G44_Automation");
        this.driver.findElement(searchInput).sendKeys(Keys.RETURN);
        validateTrue(this.driver.findElement(userLink));
        this.driver.findElement(userLink).click();

    }


}
