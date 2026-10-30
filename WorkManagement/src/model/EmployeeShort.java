package model;

import java.util.Objects;

public class EmployeeShort {
    private String shortName;
    private String phone;

    public EmployeeShort(String shortName, String phone) {
        setShortName(shortName);
        setPhone(phone);
    }

    public String getShortName() {
        return shortName;
    }

    public final void setShortName(String shortName) {
        validateShortName(shortName);
        this.shortName = shortName;
    }

    public String getPhone() {
        return phone;
    }

    public final void setPhone(String phone) {
        this.phone = EmployeeValidation.normalizePhone(phone);
    }

    public static void validateShortName(String shortName) {
        EmployeeValidation.validateRequiredText(shortName, "Краткое ФИО не должно быть пустым.");
    }

    @Override
    public String toString() {
        return shortName + ", телефон: " + phone;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        EmployeeShort other = (EmployeeShort) object;
        return Objects.equals(phone, other.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
