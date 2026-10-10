package model;

import java.math.BigDecimal;

public class Employee {
    private long employeeId;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phone;
    private String email;
    private BigDecimal salary;

    public Employee(long employeeId, String lastName, String firstName,
                    String patronymic, String phone, String email,
                    BigDecimal salary) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
