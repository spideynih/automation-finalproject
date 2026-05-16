package ui;

import config.CredentialsConfig;
import config.EnvConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;

public class EditEmployeeTest extends BaseTest {

    @BeforeMethod
    public void loginFirst() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.do_login(CredentialsConfig.EMAIL, CredentialsConfig.PASSWORD);
        loginPage.verifyDashboardPage();
        driver.get(EnvConfig.BASE_URL + "/admin/employee");
    }

    @Test(description = "TC_EDIT_EMP_001 - Edit Employee dengan data valid")
    public void testEditEmployeeValid() {
        test.set(extent.createTest("TC_EDIT_EMP_001 - Edit Employee dengan data valid"));
        EmployeePage p = new EmployeePage(driver);
        String email = "spideyedit" + System.currentTimeMillis() + "@mail.id";
        p.editEmployee("Spidey Tester", "Spidey Tester Updated", "EMP-02", email, "081234567891", "QA", "QA Batch 3+");
        driver.get(EnvConfig.BASE_URL + "/admin/employee");
        p.verifyEmployeeAdded("Spidey Tester Updated");
    }
}