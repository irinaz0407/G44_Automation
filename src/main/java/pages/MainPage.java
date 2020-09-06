package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        changeLogger("MainPage ");
    }

    private final By githubProject = By.partialLinkText("G44_Automation");
    private final By githubBranchName = By.id("branch-select-menu");
    private final By branchName = By.partialLinkText("irinaz-local");
    private final By fileLink = By.partialLinkText("pom.xml");
    private final By selElement = By.xpath("//*[text()='>selenium-java</']");
    private final By exitMenu = By.xpath("//summary[@aria-label='View profile and more']");
    private By versionElement;
    private final String seleniumVer = "3.141.59";
    private final By signOutLink = By.xpath("//button[contains(text(),'Sign out')]");
    private final String gitNotSignedIn = "Built for developers";


    public void githubTitle() {
        validateTrue(driver.findElement(githubProject));
        driver.findElement(githubProject).click();
    }

    public void changeBranch() {
        //validateTrue(driver.findElement(githubProject));
        driver.findElement(githubBranchName).click();
        driver.findElement(branchName).click();
    }

    public void gitopenFile() {
        validateTrue(driver.findElement(fileLink));
        driver.findElement(fileLink).click();
    }

    public String getSeleniumVersion() {
        String str;
        String newElId;
        validateTrue(driver.findElement(selElement));
        log.debug("Found!!!");
        str = driver.findElement(selElement).getAttribute("id");
        log.debug("Element id =" + str);
        newElId = "LC" + (Integer.parseInt(str.substring(2)) + 1);
        log.debug("New element id =" + newElId);
        versionElement = new By.ById(newElId);
        newElId = driver.findElement(versionElement).getText().replace(" ", "").replace("<version>", "").replace("</version>", "");
        log.debug("Selenium version is " + newElId);
        return newElId;
    }

    public void logOffFromGit() {
        validateTrue(driver.findElement(exitMenu));
        driver.findElement(exitMenu).click();
        validateTrue(driver.findElement(signOutLink));
        driver.findElement(signOutLink).click();
    }

    public String getSignOutMessage() {
        return gitNotSignedIn;
    }

    public String getExpectedSeleniumVersion() {
        return seleniumVer;
    }

}