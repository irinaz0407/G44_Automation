package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("login_field");
    private final By passwordField = By.id("password");
    private final By signInButton = By.name("commit");
    private final By errorMessage = By.xpath("//div[@class ='container-lg px-2']");
    private final By exitMenu = By.xpath("//summary[@aria-label='View profile and more']");
    //private final String successMessage = "Learn Git and GitHub without any code!";
    //private final By successHead = By.xpath("//h2[@class = 'shelf-title']");

    public LoginPage(WebDriver driver) {
        super(driver);
        changeLogger("Login Page");
    }

    @Step("Check all elements on login page.")
    public void login() {


        validateTrue(this.driver.findElement(usernameField));
        validateTrue(this.driver.findElement(passwordField));
        validateTrue(this.driver.findElement(signInButton));

        this.driver.findElement(usernameField).sendKeys(System.getProperty("userName"));
        this.driver.findElement(passwordField).sendKeys(System.getProperty("password"));
        this.driver.findElement(signInButton).click();
        validateTrue(this.driver.findElement(exitMenu));
        //this.login(this.connProps.getProperty("git.username"), this.connProps.getProperty("git.password"));
        // validateTrue(this.driver.findElement(successHead));

    }

    @Step("Login as user {0}")
    public void login(String username, String password) {

        this.driver.findElement(usernameField).sendKeys(username);
        this.driver.findElement(passwordField).sendKeys(password);
        this.driver.findElement(signInButton).click();

    }

    public By getErrorMessage() {
        return errorMessage;
    }

    public boolean getexitMenu() {
        return driver.findElement(exitMenu).isEnabled();
    }
}
