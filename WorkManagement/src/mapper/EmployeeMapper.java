package mapper;

import model.Employee;
import model.EmployeeShort;

public final class EmployeeMapper {
    private EmployeeMapper() {
    }

    public static EmployeeShort toShort(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Сотрудник для преобразования не должен быть null.");
        }

        String shortName = employee.getLastName().strip() + " " + getInitial(employee.getFirstName());
        String patronymic = employee.getPatronymic();
        if (patronymic != null && !patronymic.isEmpty()) {
            shortName += " " + getInitial(patronymic);
        }
        return new EmployeeShort(shortName, employee.getPhone());
    }

    private static String getInitial(String name) {
        String stripped = name.strip();
        return stripped.substring(0, stripped.offsetByCodePoints(0, 1)) + ".";
    }
}
