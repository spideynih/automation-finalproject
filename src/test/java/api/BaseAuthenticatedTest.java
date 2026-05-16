package api;

import config.CredentialsConfig;
import org.testng.annotations.BeforeSuite;

public abstract class BaseAuthenticatedTest {

    @BeforeSuite
    public void authenticate() {
        AuthService.postLogin(
                CredentialsConfig.EMAIL,
                CredentialsConfig.PASSWORD,
                CredentialsConfig.COMPANY_ID
        );
    }
}