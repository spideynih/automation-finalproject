package api;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import models.EmployeeInput;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

public class EmployeeApiTest extends BaseAuthenticatedTest {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static String createdEmployeeId;

    private static final String DIVISION_ID =
            "1b5b276e-7718-446a-a7c1-700edd1e31e0";

    @Test(description = "API_EMP_001 - Add Employee dengan data valid")
    public void testAddEmployee() {

        test.set(extent.createTest(
                "API_EMP_001 - Add Employee dengan data valid"));

        String email =
                "spideyapi" + System.currentTimeMillis() + "@mail.id";

        EmployeeInput input = new EmployeeInput(
                "Spidey API Tester",
                "EMP-API-01",
                email,
                "81234567890",
                DIVISION_ID,
                "QA"
        );

        Response response = EmployeeService.addEmployee(input);

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        createdEmployeeId =
                response.jsonPath()
                        .getString("data.createEmployee.id");

        System.out.println("CREATED EMPLOYEE ID: " + createdEmployeeId);

        Assert.assertNotNull(createdEmployeeId);

        test.get().pass(
                "Add Employee API berhasil dengan ID: "
                        + createdEmployeeId
        );
    }

    @Test(
            description = "API_EMP_002 - Edit Employee dengan data valid",
            dependsOnMethods = "testAddEmployee"
    )
    public void testEditEmployee() {

        test.set(extent.createTest(
                "API_EMP_002 - Edit Employee dengan data valid"));

        String email =
                "spideyapiedit" + System.currentTimeMillis() + "@mail.id";

        EmployeeInput input = new EmployeeInput(
                "Spidey API Tester Updated",
                "EMP-API-01",
                email,
                "81234567891",
                DIVISION_ID,
                "QA"
        );

        Response response =
                EmployeeService.editEmployee(createdEmployeeId, input);

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        String updatedId =
                response.jsonPath()
                        .getString("data.updateEmployee.id");

        Assert.assertNotNull(updatedId);

        test.get().pass("Edit Employee API berhasil");
    }

    @Test(
            description = "API_EMP_003 - Delete Employee",
            dependsOnMethods = "testEditEmployee"
    )
    public void testDeleteEmployee() {

        test.set(extent.createTest(
                "API_EMP_003 - Delete Employee"));

        Response response =
                EmployeeService.deleteEmployee(createdEmployeeId);

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        Object deletedData =
                response.jsonPath()
                        .get("data");

        Assert.assertNotNull(deletedData);

        test.get().pass("Delete Employee API berhasil");
    }
}