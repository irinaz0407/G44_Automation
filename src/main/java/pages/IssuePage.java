package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

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

    @Step("Navigate to Issue page")
    public void goToIssuesPage() {
        validateTrue(this.driver.findElement(tabsSearch));
        List<WebElement> elList = this.driver.findElements(tabsSearch);
        elList.get(1).click();
    }

    @Step("Simple issue created Title={0}, Body={1}.")
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

    @Step("Get Id of just created issue.")
    public int getCreatedIssueId() {
        validateTrue(this.driver.findElement(issueConfirmation));
        log.debug(this.driver.findElement(issueConfirmation).getText().replace("#", ""));
        return Integer.parseInt(this.driver.findElement(issueConfirmation).getText().replace("#", ""));

    }

    @Step("Check that issue #{0} has Title={1} and Body+{2}.")
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

    @Step("Create issue with labels.")
    public String createIssue(String title, String body, String label) {

        String[] labels = label.split(",");
        WebDriverWait wait = new WebDriverWait(this.driver,3);


        validateTrue(this.driver.findElement(issueButton));
        this.driver.findElement(issueButton).click();
        validateTrue(this.driver.findElement(inputTitle));
        this.driver.findElement(inputTitle).sendKeys(title);
        validateTrue(this.driver.findElement(inputBody));
        this.driver.findElement(inputBody).sendKeys(body);

        this.driver.findElement(menuLabel).click();
        log.debug("Navigated label-select-menu ");

        for (String s : labels) {
            log.debug("Waiting for label "+s);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("label-filter-field")));
            this.driver.findElement(By.id("label-filter-field")).sendKeys(s);
            log.debug("Click on label "+s);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + s + "')]")));
            this.driver.findElement(By.xpath("//span[contains(text(),'" + s + "')]")).click();
            log.debug("Ctrl+A ");
            this.driver.findElement(By.id("label-filter-field")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            log.debug("Del ");
            this.driver.findElement(By.id("label-filter-field")).sendKeys(Keys.DELETE);
        }
        log.debug("Closing labels menu");
        this.driver.findElement(menuLabel).click();

        validateTrue(this.driver.findElement(submitButton));
        this.driver.findElement(submitButton).click();
        return "ok";
    }
    @Step("Delete issue by issue ID")
    public void deleteIssue(int issueId){
        final By issueItem = By.xpath("//a[@id='issue_" + issueId + "_link']");
        final By deleteButton = By.xpath("//strong[contains(text(),'Delete issue')]");
        final By confDeleteButton = By.xpath("//button[@name='verify_delete']");

        this.driver.findElement(issueItem).click();
        this.driver.findElement(deleteButton).click();
        this.driver.findElement(confDeleteButton).click();
    }

    @Step("Delete visible issues")
    public void deleteAllIssues(){
        final By issueList = By.xpath("//a[contains(@id,'issue')]");
        WebDriverWait wait = new WebDriverWait(this.driver,30);

        for(WebElement wwe: this.driver.findElements(issueList)){
            WebElement we = this.driver.findElement(issueList);
            log.debug("Deleting "+ we.getAttribute("id"));
            deleteIssue(Integer.parseInt(we.getAttribute("id").replace("issue_","").replace("_link","")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@id,'issue')]")));
        }
    }
}
