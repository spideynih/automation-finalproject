package ui;

import config.CredentialsConfig;
import config.EnvConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;

public class AddEmployeeTest extends BaseTest {

    @BeforeMethod
    public void loginFirst() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.do_login(CredentialsConfig.EMAIL, CredentialsConfig.PASSWORD);
        loginPage.verifyDashboardPage();
        driver.get(EnvConfig.BASE_URL + "/admin/employee");
    }

    @Test(description = "TC_EMP_001 - Add Employee dengan data valid")
    public void testAddEmployeeValid() {
        test.set(extent.createTest("TC_EMP_001 - Add Employee dengan data valid"));
        EmployeePage p = new EmployeePage(driver);
        String email = "spidey" + System.currentTimeMillis() + "@mail.id";
        p.addEmployee("Spidey Tester", "EMP-01", email, "081234567890", "QA", "QA Batch 3+");
        p.verifyEmployeeAdded("Spidey Tester");
    }
}