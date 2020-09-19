package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) { //конструктор
        super(driver);
        changeLogger("SearchPage");
        log.debug("Constructor OK");
    }

    @Step("Selecting project {0}")
    public void projectSearch(String projectName) {
        final By searchInput = By.xpath("//input[contains(@class,'header-search-input')]");
        final By userLink = By.partialLinkText(System.getProperty("userName"));
        //final By userLink = By.partialLinkText(this.connProps.getProperty("git.username"));
        validateTrue(this.driver.findElement(searchInput));
        this.driver.findElement(searchInput).sendKeys(projectName);
        this.driver.findElement(searchInput).sendKeys(Keys.RETURN);
        validateTrue(this.driver.findElement(userLink));
        this.driver.findElement(userLink).click();

    }


}
