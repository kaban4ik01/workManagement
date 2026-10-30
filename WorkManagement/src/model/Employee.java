package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.Objects;

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
        setEmployeeId(employeeId);
        setLastName(lastName);
        setFirstName(firstName);
        setPatronymic(patronymic);
        setPhone(phone);
        setEmail(email);
        setSalary(salary);
    }

    public Employee(String data) {
        this(splitData(data));
    }

    private Employee(String[] fields) {
        this(parseEmployeeId(fields[0]), fields[1], fields[2], fields[3],
                fields[4], fields[5], parseSalary(fields[6]));
    }

    public Employee(JsonObject json) {
        this(readJsonEmployeeId(json), readJsonText(json, "lastName"),
                readJsonText(json, "firstName"), readJsonText(json, "patronymic"),
                readJsonText(json, "phone"), readJsonText(json, "email"),
                readJsonNumber(json, "salary"));
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public final void setEmployeeId(long employeeId) {
        validateEmployeeId(employeeId);
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public final void setPatronymic(String patronymic) {
        validatePatronymic(patronymic);
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public final void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public final void setSalary(BigDecimal salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic=" + (patronymic == null ? "null" : "'" + patronymic + "'") +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary.toPlainString() +
                '}';
    }

    public String toShortString() {
        String initials = getInitial(firstName);
        if (patronymic != null && !patronymic.isEmpty()) {
            initials += " " + getInitial(patronymic);
        }
        return lastName.strip() + " " + initials + ", телефон: " + phone;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Employee other = (Employee) object;
        return employeeId == other.employeeId
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(patronymic, other.patronymic)
                && Objects.equals(phone, other.phone)
                && Objects.equals(email, other.email)
                && salary.compareTo(other.salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, lastName, firstName, patronymic,
                phone, email, salary.stripTrailingZeros());
    }

    private static String getInitial(String name) {
        String stripped = name.strip();
        return stripped.substring(0, stripped.offsetByCodePoints(0, 1)) + ".";
    }

    public static void validateEmployeeId(long employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Идентификатор сотрудника должен быть больше нуля.");
        }
    }

    public static void validateLastName(String lastName) {
        validateRequiredText(lastName, "Фамилия не должна быть пустой.");
    }

    public static void validateFirstName(String firstName) {
        validateRequiredText(firstName, "Имя не должно быть пустым.");
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

    private static void validateRequiredText(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static String[] splitData(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Строка данных сотрудника не должна быть null.");
        }
        String[] fields = data.split(";", -1);
        if (fields.length != 7) {
            throw new IllegalArgumentException("Строка должна содержать ровно 7 полей через ;: "
                    + "ID;фамилия;имя;отчество;телефон;email;оклад.");
        }
        return fields;
    }

    private static long parseEmployeeId(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("ID должен быть целым числом в диапазоне long.", exception);
        }
    }

    private static BigDecimal parseSalary(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Оклад должен быть числом с точкой в качестве разделителя.", exception);
        }
    }

    private static JsonElement readJsonValue(JsonObject json, String field) {
        if (json == null) {
            throw new IllegalArgumentException("JSON-объект сотрудника не должен быть null.");
        }
        return json.get(field);
    }

    private static String readJsonText(JsonObject json, String field) {
        JsonElement value = readJsonValue(json, field);
        if (value == null || value.isJsonNull()) {
            return null;
        }
        if (!value.isJsonPrimitive() || !value.getAsJsonPrimitive().isString()) {
            throw new IllegalArgumentException("Поле JSON " + field + " должно быть строкой.");
        }
        return value.getAsString();
    }

    private static BigDecimal readJsonNumber(JsonObject json, String field) {
        JsonElement value = readJsonValue(json, field);
        if (value == null || !value.isJsonPrimitive() || !value.getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException("Поле JSON " + field + " должно быть числом.");
        }
        try {
            return value.getAsBigDecimal();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Поле JSON " + field + " содержит некорректное число.", exception);
        }
    }

    private static long readJsonEmployeeId(JsonObject json) {
        try {
            return readJsonNumber(json, "employeeId").longValueExact();
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("ID в JSON должен быть целым числом в диапазоне long.", exception);
        }
    }
}
