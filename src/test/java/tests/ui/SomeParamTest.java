package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import static java.lang.Thread.sleep;

@RunWith(Parameterized.class)
public class SomeParamTest extends BaseTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private SearchPage searchPage;
    private IssuePage issuePage;
    private static FileHelper fileHelper;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    @Before
    public void init() {
        this.loginPage = new LoginPage(this.driver);
        log.debug("Login Page");
        this.mainPage = new MainPage(this.driver);
        log.debug("Main Page");
        this.searchPage = new SearchPage(this.driver);
        log.debug("Search Page");
        this.issuePage = new IssuePage(this.driver);
        this.fileHelper = new FileHelper();
    }

    @Parameterized.Parameters
    public static Collection<String[]> data() {
        return fileHelper.readTestDataFromFile();

    }

    @Parameterized.Parameter(value = 0)
    public String testTitle;
    @Parameterized.Parameter(value = 1)
    public String testBody;
    @Parameterized.Parameter(value = 2)
    public String testLabel;

    @Description("Create Set of issues based on infromation from file")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void label() {
        String checkLabel;

        try {
            fileHelper.addTestResult("Run parameters Title = " + testTitle + "; Body = " + testBody + "; Labels =" + testLabel, "", "");
            this.loginPage.login();
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Login to GIT", "Passed");
            sleep(1000);
            this.searchPage.projectSearch("G44_Automation");
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Project search", "Passed");
            this.issuePage.goToIssuesPage();
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Issues page navigation", "Passed");
            checkLabel = this.issuePage.createIssue(testTitle, testBody, testLabel);
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Issues labels creation", "Passed");

        } catch (Exception e) {
            log.debug("label() exception");
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Issues labels creation", "Failed");
            e.printStackTrace();

        }

    }

    @After
    public void finalActions() {
        try {
            this.mainPage.logOffFromGit();
            Assert.assertTrue(driver.getPageSource().contains(this.mainPage.getSignOutMessage()));
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Logoff", "Passed");
        } catch (Exception e) {
            log.debug("Logoff from GIT exception");
            fileHelper.addTestResult(dateFormat.format(Calendar.getInstance().getTime()), "Logoff", "Failed");
        } finally {
            fileHelper.addRowsToExcel();
        }
    }
}
