package ui;

import config.CredentialsConfig;
import config.EnvConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;

public class DeleteEmployeeTest extends BaseTest {

    @BeforeMethod
    public void loginFirst() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.do_login(CredentialsConfig.EMAIL, CredentialsConfig.PASSWORD);
        loginPage.verifyDashboardPage();
        driver.get(EnvConfig.BASE_URL + "/admin/employee");
    }

    @Test(description = "TC_DEL_EMP_001 - Delete Employee")
    public void testDeleteEmployeeValid() {
        test.set(extent.createTest("TC_DEL_EMP_001 - Delete Employee"));
        EmployeePage p = new EmployeePage(driver);
        p.deleteEmployee("Spidey Tester Updated");
        p.verifyEmployeeDeleted("Spidey Tester Updated");
    }
}