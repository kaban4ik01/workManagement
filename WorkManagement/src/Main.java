import model.Employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.Strictness;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Gson gson = new GsonBuilder().setStrictness(Strictness.STRICT).create();
            System.out.println("Создание сотрудника");

            while (true) {
                try {
                    System.out.println("1 — ввод отдельных полей, 2 — строка через ;, 3 — JSON");
                    System.out.print("Способ ввода: ");
                    String choice = scanner.nextLine();
                    Employee employee;

                    switch (choice) {
                        case "1" -> {
                            System.out.print("ID сотрудника: ");
                            long employeeId = Long.parseLong(scanner.nextLine());

                            System.out.print("Фамилия: ");
                            String lastName = scanner.nextLine();

                            System.out.print("Имя: ");
                            String firstName = scanner.nextLine();

                            System.out.print("Отчество (можно оставить пустым): ");
                            String patronymic = scanner.nextLine();

                            System.out.print("Телефон (10–15 цифр, можно с + в начале): ");
                            String phone = scanner.nextLine();

                            System.out.print("Email: ");
                            String email = scanner.nextLine();

                            System.out.print("Оклад (например, 50000.00): ");
                            BigDecimal salary = new BigDecimal(scanner.nextLine());

                            employee = new Employee(
                                    employeeId, lastName, firstName, patronymic, phone, email, salary
                            );
                        }
                        case "2" -> {
                            System.out.println("Формат: ID;фамилия;имя;отчество;телефон;email;оклад");
                            System.out.print("Строка данных: ");
                            employee = new Employee(scanner.nextLine());
                        }
                        case "3" -> {
                            System.out.println("Введите JSON одной строкой: employeeId и salary — числа; "
                                    + "lastName, firstName, patronymic, phone, email — строки. "
                                    + "Отчество можно пропустить или указать null.");
                            JsonObject json = gson.fromJson(scanner.nextLine(), JsonObject.class);
                            employee = new Employee(json);
                        }
                        default -> throw new IllegalArgumentException("Выберите способ ввода 1, 2 или 3.");
                    }

                    System.out.println("Сотрудник успешно создан. ID: " + employee.getEmployeeId());
                    System.out.println("Полное представление: " + employee);
                    System.out.println("Краткое представление: " + employee.toShortString());
                    break;

                } catch (JsonParseException exception) {
                    System.out.println("Ошибка: введите корректный JSON-объект. "
                            + "Названия полей и строковые значения должны быть в двойных кавычках.");
                    System.out.println("Введите данные сотрудника заново.");
                } catch (NumberFormatException exception) {
                    System.out.println("Ошибка: ID должен быть целым числом в диапазоне long, "
                            + "а оклад — числом с точкой в качестве десятичного разделителя.");
                    System.out.println("Введите данные сотрудника заново.");
                } catch (IllegalArgumentException exception) {
                    System.out.println("Ошибка: " + exception.getMessage());
                    System.out.println("Введите данные сотрудника заново.");
                } catch (NoSuchElementException exception) {
                    System.out.println("Ввод завершён. Сотрудник не создан.");
                    break;
                }
            }
        }
    }

}
