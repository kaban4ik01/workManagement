package model;

public final class EmployeeValidation {
    private EmployeeValidation() {
    }

    public static void validateRequiredText(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validatePhone(String phone) {
        normalizePhone(phone);
    }

    public static String normalizePhone(String phone) {
        if (phone == null || !phone.matches("(?U)[+0-9\\s()-]+")) {
            throw new IllegalArgumentException("Телефон обязателен и может содержать только цифры, "
                    + "+ в начале, пробелы, скобки и дефисы.");
        }
        String number = phone.replaceAll("(?U)[\\s()-]", "");
        if (number.matches("[0-9]{10}")) {
            return "+7" + number;
        }
        if (number.matches("[78][0-9]{10}")) {
            return "+7" + number.substring(1);
        }
        if (number.matches("\\+7[0-9]{10}")) {
            return number;
        }
        throw new IllegalArgumentException("Введите российский номер: 10 цифр без кода страны "
                + "или 11 цифр с начальной 7/8; допускается +7.");
    }
}
