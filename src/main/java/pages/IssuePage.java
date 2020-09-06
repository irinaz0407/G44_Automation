package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IssuePage extends BasePage {

    public IssuePage(WebDriver driver) {
        super(driver);
        changeLogger("IssuePage ");
    }

    private final By tabsSearch = By.xpath("//span[@data-content]");
    private final By issueButton = By.linkText("New issue");
    private final By inputTitle = By.id("issue_title");
    private final By inputBody = By.id("issue_body");
    private final By submitButton = By.xpath("//button[@type='submit' and @class='btn btn-primary']");
    private final By issueConfirmation = By.xpath("//span[@class='f1-light text-gray-light']");
    private final By menuLabel = By.id("labels-select-menu");

    public void goToIssuesPage() {
        validateTrue(this.driver.findElement(tabsSearch));
        List<WebElement> elList = this.driver.findElements(tabsSearch);
        elList.get(1).click();
    }

    public void createIssue(String title, String body) {
        validateTrue(this.driver.findElement(issueButton));
        this.driver.findElement(issueButton).click();
        validateTrue(this.driver.findElement(inputTitle));
        this.driver.findElement(inputTitle).sendKeys(title);
        validateTrue(this.driver.findElement(inputBody));
        this.driver.findElement(inputBody).sendKeys(body);
        validateTrue(this.driver.findElement(submitButton));
        this.driver.findElement(submitButton).click();
    }

    public int getCreatedIssueId() {
        validateTrue(this.driver.findElement(issueConfirmation));
        log.debug(this.driver.findElement(issueConfirmation).getText().replace("#", ""));
        return Integer.parseInt(this.driver.findElement(issueConfirmation).getText().replace("#", ""));

    }

    public void checkIssueById(int id, String title, String body) throws Exception {
        final By issueItem = By.xpath("//a[@id='issue_" + id + "_link']");
        final By issueTitle = By.xpath("//span[@class='js-issue-title']");
        final By issueBody = By.xpath("//td[@class='d-block comment-body markdown-body  js-comment-body']/p");

        validateTrue(this.driver.findElement(issueItem));
        this.driver.findElement(issueItem).click();
        validateTrue(this.driver.findElement(issueTitle));
        if (!this.driver.findElement(issueTitle).getText().equals(title)) {
            final Exception titleEx = new Exception("Title is not matching to original! expected: '" + title + "' found '" + this.driver.findElement(issueTitle).getText() + "'");
            throw titleEx;
        }
        validateTrue(this.driver.findElement(issueBody));
        if (!this.driver.findElement(issueTitle).getText().equals(title)) {
            final Exception bodyEx = new Exception("Body is not matching to original! expected: '" + body + "' found '" + this.driver.findElement(issueBody).getText() + "'");
            throw bodyEx;
        }

    }

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
