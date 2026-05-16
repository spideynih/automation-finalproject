package ui;

import config.CredentialsConfig;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "TC_LOGIN_001 - Login dengan email dan password valid")
    public void testLoginValid() {
        test.set(extent.createTest("TC_LOGIN_001 - Login dengan email dan password valid"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.do_login(CredentialsConfig.EMAIL, CredentialsConfig.PASSWORD);
        loginPage.verifyDashboardPage();
    }
}