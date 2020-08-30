package tests;

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

    @Test
    public void negativeAuthLogin() {
        this.loginPage.login("TestUser", "TestPassword");
        Assert.assertEquals("Incorrect username or password.", driver.findElement(this.loginPage.getErrorMessage()).getText());
        this.runAfter = "No";
    }

    @Test
    public void positiveAuthLogin() {
        this.loginPage.login();
        Assert.assertTrue(driver.getPageSource().contains(this.loginPage.getSuccessMessage()));
    }

    @Test
    public void checkSearchProject() {
        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tabsPrint() {
        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch();
            this.tabsPage.printTabs();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createIssue() throws Exception {
        String testTitle = "Test of issue creation title";
        String testBody = "Test issue body";
        int createdIssueId;
        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch();
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
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void finalActions() {
        if (this.runAfter.equals("Yes")) {
            this.mainPage.logOffFromGit();
            Assert.assertTrue(driver.getPageSource().contains(this.mainPage.getSignOutMessage()));
        }
    }
}
