package models;

public class EmployeeInput {
    public String name;
    public String employeeId;
    public String email;
    public String phoneNumber;
    public String divisionId;
    public String employeeRole;
    public String gender;
    public String address;
    public String nik;
    public String npwp;

    public EmployeeInput(String name, String employeeId, String email,
                         String phoneNumber, String divisionId, String employeeRole) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
        this.employeeRole = employeeRole;
        this.gender = "";
        this.address = "";
        this.nik = "";
        this.npwp = "";
    }
}