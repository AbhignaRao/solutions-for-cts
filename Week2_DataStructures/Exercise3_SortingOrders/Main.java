public class Main {
    public static void main(String[] args) {

        // orders for bubble sort
        Order[] orders1 = {
            new Order(1, "Alice", 250.00),
            new Order(2, "Bob", 100.00),
            new Order(3, "Charlie", 450.00),
            new Order(4, "Diana", 75.00),
            new Order(5, "Eve", 300.00)
        };

        // orders for quick sort
        Order[] orders2 = {
            new Order(1, "Alice", 250.00),
            new Order(2, "Bob", 100.00),
            new Order(3, "Charlie", 450.00),
            new Order(4, "Diana", 75.00),
            new Order(5, "Eve", 300.00)
        };

        // bubble sort
        System.out.println("-- Bubble Sort (by Total Price) --");
        SortingAlgorithms.bubbleSort(orders1);
        for (Order o : orders1) System.out.println(o);

        // quick sort
        System.out.println("\n-- Quick Sort (by Total Price) --");
        SortingAlgorithms.quickSort(orders2, 0, orders2.length - 1);
        for (Order o : orders2) System.out.println(o);

        System.out.println("\n-- Time Complexity --");
        System.out.println("Bubble Sort: O(n²) - slow for large data");
        System.out.println("Quick Sort : O(n log n) avg - faster for large data");
        System.out.println("Quick Sort is preferred over Bubble Sort");
    }
}