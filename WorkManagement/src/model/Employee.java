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
        validateEmployeeId(employeeId);
        validateLastName(lastName);
        validateFirstName(firstName);
        validatePatronymic(patronymic);
        validatePhone(phone);
        validateEmail(email);
        validateSalary(salary);

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
        validateEmployeeId(employeeId);
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        validatePatronymic(patronymic);
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    public static void validateEmployeeId(long employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Идентификатор сотрудника должен быть больше нуля.");
        }
    }

    public static void validateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Фамилия не должна быть пустой.");
        }
    }

    public static void validateFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Имя не должно быть пустым.");
        }
    }

    public static void validatePatronymic(String patronymic) {
        if (patronymic != null && !patronymic.isEmpty() && patronymic.isBlank()) {
            throw new IllegalArgumentException("Отчество не должно состоять только из пробелов.");
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\+?[0-9]{10,15}")) {
            throw new IllegalArgumentException(
                    "Телефон должен содержать от 10 до 15 цифр с необязательным + в начале.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.matches("(?U)[^\\s@]+@[^\\s@.]+(?:\\.[^\\s@.]+)+")) {
            throw new IllegalArgumentException("Email должен иметь формат имя@домен.зона без пробелов.");
        }
    }

    public static void validateSalary(BigDecimal salary) {
        if (salary == null || salary.signum() <= 0) {
            throw new IllegalArgumentException("Оклад должен быть больше нуля.");
        }
        if (salary.stripTrailingZeros().scale() > 2) {
            throw new IllegalArgumentException("Оклад должен задаваться с точностью до копеек.");
        }
    }
}
