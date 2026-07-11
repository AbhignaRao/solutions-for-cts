public class Main {
    public static void main(String[] args) {

        TaskLinkedList taskList = new TaskLinkedList();

        // add tasks
        System.out.println("-- Adding Tasks --");
        taskList.addTask(new Task(1, "Design Database", "Pending"));
        taskList.addTask(new Task(2, "Build API", "In Progress"));
        taskList.addTask(new Task(3, "Write Tests", "Pending"));
        taskList.addTask(new Task(4, "Deploy App", "Pending"));

        // traverse
        System.out.println();
        taskList.traverseTasks();

        // search
        System.out.println("\n-- Searching Task --");
        Task t = taskList.searchTask(3);
        System.out.println("Search ID 3: " + (t != null ? t : "Not found"));

        Task t2 = taskList.searchTask(99);
        System.out.println("Search ID 99: " + (t2 != null ? t2 : "Not found"));

        // delete
        System.out.println("\n-- Deleting Task --");
        taskList.deleteTask(2);

        // traverse after delete
        System.out.println();
        taskList.traverseTasks();

        System.out.println("\n-- Time Complexity --");
        System.out.println("Add     : O(n)");
        System.out.println("Search  : O(n)");
        System.out.println("Traverse: O(n)");
        System.out.println("Delete  : O(n)");
        System.out.println("Advantage: Dynamic size unlike arrays");
    }
}