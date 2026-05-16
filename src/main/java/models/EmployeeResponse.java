package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResponse {

    public Data data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        public Employee createEmployee;
        public Employee updateEmployee;
        public String deleteEmployee;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Employee {
        public String id;
    }
}