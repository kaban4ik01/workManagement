import model.Employee;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Создание сотрудника");

            while (true) {
                try {
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

                    Employee employee = new Employee(
                            employeeId, lastName, firstName, patronymic, phone, email, salary
                    );

                    System.out.println("Сотрудник успешно создан. ID: " + employee.getEmployeeId());
                    break;

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
