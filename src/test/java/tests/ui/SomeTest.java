package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static java.lang.Thread.sleep;

public class SomeTest extends BaseTest {

    private String runAfter = "Yes";
    private LoginPage loginPage; //экземпляр класса
    private MainPage mainPage;
    private SearchPage searchPage;
    private TabsPage tabsPage;
    private IssuePage issuePage;

    @Before
    public void init() {
        this.loginPage = new LoginPage(this.driver); //экземпляр класса
        this.mainPage = new MainPage(this.driver);
        this.searchPage = new SearchPage(this.driver);
        this.tabsPage = new TabsPage(this.driver);
        this.issuePage = new IssuePage(this.driver);
    }

    @Description("Check negative login")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void negativeAuthLogin() {
        this.loginPage.login("TestUser", "TestPassword");
        Assert.assertEquals("Incorrect username or password.", driver.findElement(this.loginPage.getErrorMessage()).getText());
        this.runAfter = "No";
    }

    @Description("Check positive login")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void positiveAuthLogin() {
        this.loginPage.login();
        Assert.assertTrue(loginPage.getexitMenu());
    }

    @Description("Check Search Project G44")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void checkSearchProject() {
        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch("G44_Automation");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Description("Check Tabs correctness")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void tabsPrint() {
        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch("G44_Automation");
            this.tabsPage.printTabs();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Description("Check Issue creation")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void createIssue() throws Exception {
        String testTitle = "Test of issue creation title";
        String testBody = "Test issue body";
        int createdIssueId;
        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch("G44_Automation");
            this.issuePage.goToIssuesPage();
            this.issuePage.createIssue(testTitle, testBody);
            createdIssueId = this.issuePage.getCreatedIssueId();
            log.info("Checking issue #" + createdIssueId);
            this.issuePage.goToIssuesPage();
            this.issuePage.checkIssueById(createdIssueId, testTitle, testBody);
            sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Description("Check Selenium version in pom file")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void checkProject() {

        try {
            this.loginPage.login();
            sleep(1000);
            this.mainPage.githubTitle();
            this.mainPage.changeBranch();
            sleep(1000);
            this.mainPage.gitopenFile();
            Assert.assertEquals(this.mainPage.getExpectedSeleniumVersion(), this.mainPage.getSeleniumVersion());
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Description("Finalize all tests")
    @After
    public void finalActions() {
        if (this.runAfter.equals("Yes")) {
            this.mainPage.logOffFromGit();
            Assert.assertTrue(driver.getPageSource().contains(this.mainPage.getSignOutMessage()));
        }
    }
}
