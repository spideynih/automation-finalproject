package api;

import io.restassured.response.Response;
import models.EmployeeInput;

import java.util.Map;

public class EmployeeService {

    public static Response addEmployee(EmployeeInput input) {

        String query =
                utils.TestDataLoader.load("graphql/AddEmployee.graphql");

        return GraphQLClient.execute(
                query,
                Map.of("input", input)
        );
    }

    public static Response editEmployee(
            String employeeId,
            EmployeeInput input
    ) {

        String query =
                utils.TestDataLoader.load("graphql/EditEmployee.graphql");

        return GraphQLClient.execute(
                query,
                Map.of(
                        "id", employeeId,
                        "input", input
                )
        );
    }

    public static Response deleteEmployee(String employeeId) {

        String query =
                utils.TestDataLoader.load("graphql/DeleteEmployee.graphql");

        return GraphQLClient.execute(
                query,
                Map.of("id", employeeId)
        );
    }
}