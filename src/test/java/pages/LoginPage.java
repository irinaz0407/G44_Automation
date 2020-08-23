package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By usernameField = By.id("login_field");
    private final By passwordField = By.id("password");
    private final By signInButton = By.name("commit");
    private final By errorMessage = By.xpath("//div[@class ='container-lg px-2']");
    private final By successHead = By.xpath("//h2[@class = 'shelf-title']");
    private final String successMessage = "Learn Git and GitHub without any code!";

    public LoginPage(WebDriver driver){
        super(driver);
        changeLogger("Login Page");
    }
    public void login(){
        validateTrue(this.driver.findElement(usernameField));
        validateTrue(this.driver.findElement(passwordField));
        validateTrue(this.driver.findElement(signInButton));

        this.driver.findElement(usernameField).sendKeys(System.getProperty("userName"));
        this.driver.findElement(passwordField).sendKeys(System.getProperty("password"));
        this.driver.findElement(signInButton).click();
        validateTrue(this.driver.findElement(successHead));

    }

    public void login(String username, String password){

        this.driver.findElement(usernameField).sendKeys(username);
        this.driver.findElement(passwordField).sendKeys(password);
        this.driver.findElement(signInButton).click();

    }

 //   private void validateTrue(WebElement element) {
 //       log.debug("Check element:" + element);
 //       Assert.assertTrue(element.isEnabled());
//        log.debug("Test finished");
   // }
    public By getErrorMessage(){
        return errorMessage;
    }
    public String getSuccessMessage(){
        return successMessage;
    }
}
