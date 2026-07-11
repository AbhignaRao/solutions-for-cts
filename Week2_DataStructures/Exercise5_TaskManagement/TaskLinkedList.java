public class TaskLinkedList {
    private Task head;

    // O(n) - add at end
    public void addTask(Task task) {
        if (head == null) {
            head = task;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = task;
        }
        System.out.println("Added: " + task);
    }

    // O(n) - search by id
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) return current;
            current = current.next;
        }
        return null;
    }

    // O(n) - traverse all
    public void traverseTasks() {
        System.out.println("-- All Tasks --");
        Task current = head;
        if (current == null) {
            System.out.println("No tasks found");
            return;
        }
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    // O(n) - delete by id
    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        // if head is the task to delete
        if (head.taskId == taskId) {
            System.out.println("Deleted: " + head);
            head = head.next;
            return;
        }
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                System.out.println("Deleted: " + current.next);
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
        System.out.println("Task not found: " + taskId);
    }
}