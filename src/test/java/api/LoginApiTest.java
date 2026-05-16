package api;

import config.CredentialsConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import models.ApiResponse;
import models.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

public class LoginApiTest extends BaseAuthenticatedTest {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Test(description = "API_LOGIN_001 - Login dengan kredensial valid")
    public void testLoginValid() {
        test.set(extent.createTest("API_LOGIN_001 - Login dengan kredensial valid"));
        ApiResponse<LoginResponse> response = AuthService.postLogin(
                CredentialsConfig.EMAIL, CredentialsConfig.PASSWORD, CredentialsConfig.COMPANY_ID);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getResponseBody().data.login.user);
        test.get().pass("Login API berhasil dengan status code 200");
    }
}