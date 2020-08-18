package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;

import static java.lang.Thread.sleep;

public class SomeTest extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private String runAfter = "Yes";

    @Before
    public void init() {
        this.loginPage = new LoginPage(this.driver);
        this.mainPage = new MainPage(this.driver);
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
    public void checkProject() {

        try {
            this.loginPage.login();
            sleep(1000);
            this.mainPage.githubTitle();
            this.mainPage.changeBranch();
            sleep(1000);
            this.mainPage.gitopenFile();
            Assert.assertEquals(this.mainPage.getExpectedSeleniumVersion(),this.mainPage.getSeleniumVersion());
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void finalActions() {
        if (this.runAfter == "Yes") {
            this.mainPage.logOffFromGit();
            Assert.assertTrue(driver.getPageSource().contains(this.mainPage.getSignOutMessage()));
        }
    }
}
