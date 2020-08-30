package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class IssueParamPage extends BasePage {
    public IssueParamPage(WebDriver driver) {
        super(driver);
        changeLogger("IssueParamPage");
    }

    private final By tabsSearch = By.xpath("//span[@data-content]");
    private final By issueButton = By.linkText("New issue");
    private final By inputTitle = By.id("issue_title");
    private final By inputBody = By.id("issue_body");
    private final By menuLabel = By.id("labels-select-menu");
    private final By menuItemLabel = By.xpath("//span[contains(text(),'s')]");
    private final By submitButton = By.xpath("//button[@type='submit' and @class='btn btn-primary']");


    public String createIssue(String title, String body, String label) {

        String[] labels = label.split(",");

        validateTrue(this.driver.findElement(issueButton));
        this.driver.findElement(issueButton).click();
        validateTrue(this.driver.findElement(inputTitle));
        this.driver.findElement(inputTitle).sendKeys(title);
        validateTrue(this.driver.findElement(inputBody));
        this.driver.findElement(inputBody).sendKeys(body);

        this.driver.findElement(menuLabel).click();
        for (String s : labels) {
            this.driver.findElement(By.id("label-filter-field")).sendKeys(s);
            this.driver.findElement(By.xpath("//span[contains(text(),'" + s + "')]")).click();
            this.driver.findElement(By.id("label-filter-field")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            this.driver.findElement(By.id("label-filter-field")).sendKeys(Keys.DELETE);
        }
        this.driver.findElement(menuLabel).click();

        validateTrue(this.driver.findElement(submitButton));
        this.driver.findElement(submitButton).click();
        return "ok";
    }

}
