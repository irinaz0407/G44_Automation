package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.*;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Thread.sleep;

@RunWith(Parameterized.class)
public class SomeParamTest extends BaseTest {
    private String runAfter = "Yes";
    private LoginPage loginPage;
    private MainPage mainPage;
    private SearchPage searchPage;
    private IssuePage issuePage;
    private IssueParamPage issueParamPage;

    @Before
    public void init() {
        this.loginPage = new LoginPage(this.driver);
        log.debug("Login Page");
        this.mainPage = new MainPage(this.driver);
        log.debug("Main Page");
        this.searchPage = new SearchPage(this.driver);
        log.debug("Search Page");
        this.issuePage = new IssuePage(this.driver);
        this.issueParamPage = new IssueParamPage(this.driver);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Title1", "Comments1", "bug"},
                {"Title2", "Comments2", "documentation"},
                {"Title3", "Comments3", "question"},
                {"Title4", "Comments4", "bug,documentation"},
                {"Title5", "Comments5", "bug,question"},
                {"Title6", "Comments6", "documentation,question"},
                {"Title7", "Comments7", "bug,documentation,question"}
        });
    }

    @Parameterized.Parameter(value = 0)
    public String testTitle;
    @Parameterized.Parameter(value = 1)
    public String testBody;
    @Parameterized.Parameter(value = 2)
    public String testLabel;


    @Test
    public void label() {
        String checkLabel;

        try {
            this.loginPage.login();
            sleep(1000);
            this.searchPage.projectSearch();
            this.issuePage.goToIssuesPage();
            checkLabel = this.issueParamPage.createIssue(testTitle, testBody, testLabel);
            log.info("Check label is " + checkLabel);


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
