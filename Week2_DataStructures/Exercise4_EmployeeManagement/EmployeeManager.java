public class EmployeeManager {
    private Employee[] employees;
    private int size;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // O(1) - add at end
    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size] = employee;
            size++;
            System.out.println("Added: " + employee);
        } else {
            System.out.println("Array is full!");
        }
    }

    // O(n) - search by id
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    // O(n) - traverse all
    public void traverseEmployees() {
        System.out.println("-- All Employees --");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // O(n) - delete by shifting elements
    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                Employee removed = employees[i];
                // shift elements left
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null;
                size--;
                System.out.println("Deleted: " + removed);
                return;
            }
        }
        System.out.println("Employee not found: " + employeeId);
    }
}