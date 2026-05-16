package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage {

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "input-username-or-email")
    private WebElement usernameTextField;

    @FindBy(id = "input-password")
    private WebElement passwordTextField;

    @FindBy(id = "button-sign-in")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class,'chakra-text') and contains(text(),'wrong')]")
    private WebElement errorMessage;

    public void inputUsernameTextField(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        usernameTextField.clear();
        usernameTextField.sendKeys(username);
    }

    public void inputPasswordTextField(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.clear();
        passwordTextField.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void do_login(String username, String password) {
        inputUsernameTextField(username);
        inputPasswordTextField(password);
        clickLoginButton();
    }

    public void verifyDashboardPage() {
        wait.until(ExpectedConditions.urlContains("/admin/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/admin/dashboard"),
                "Login Failed - Not redirected to dashboard page");
    }

    public void verifyStillOnLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        Assert.assertTrue(usernameTextField.isDisplayed(),
                "Login Failed - Invalid Credentials");
    }

    public void verifyErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertTrue(errorMessage.isDisplayed(),
                "Error message not displayed");
    }
}