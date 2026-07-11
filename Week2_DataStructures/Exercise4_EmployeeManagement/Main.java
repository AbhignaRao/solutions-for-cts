public class Main {
    public static void main(String[] args) {

        EmployeeManager manager = new EmployeeManager(5);

        // add employees
        System.out.println("-- Adding Employees --");
        manager.addEmployee(new Employee(1, "Guna", "Developer", 75000));
        manager.addEmployee(new Employee(2, "Alice", "Manager", 95000));
        manager.addEmployee(new Employee(3, "Bob", "Designer", 65000));

        // traverse
        System.out.println();
        manager.traverseEmployees();

        // search
        System.out.println("\n-- Searching Employee --");
        Employee emp = manager.searchEmployee(2);
        System.out.println("Search ID 2: " + (emp != null ? emp : "Not found"));

        Employee emp2 = manager.searchEmployee(99);
        System.out.println("Search ID 99: " + (emp2 != null ? emp2 : "Not found"));

        // delete
        System.out.println("\n-- Deleting Employee --");
        manager.deleteEmployee(2);

        // traverse after delete
        System.out.println();
        manager.traverseEmployees();

        System.out.println("\n-- Time Complexity --");
        System.out.println("Add     : O(1)");
        System.out.println("Search  : O(n)");
        System.out.println("Traverse: O(n)");
        System.out.println("Delete  : O(n)");
    }
}