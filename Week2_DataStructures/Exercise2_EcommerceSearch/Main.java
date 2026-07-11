public class Main {
    public static void main(String[] args) {

        // unsorted array for linear search
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Mouse", "Electronics"),
            new Product(3, "Keyboard", "Electronics"),
            new Product(4, "Desk", "Furniture"),
            new Product(5, "Chair", "Furniture")
        };

        // sorted array for binary search
        Product[] sortedProducts = {
            new Product(5, "Chair", "Furniture"),
            new Product(4, "Desk", "Furniture"),
            new Product(3, "Keyboard", "Electronics"),
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Mouse", "Electronics")
        };

        // linear search
        System.out.println("-- Linear Search --");
        Product result1 = SearchAlgorithms.linearSearch(products, "Keyboard");
        System.out.println("Search 'Keyboard': " + (result1 != null ? result1 : "Not found"));

        Product result2 = SearchAlgorithms.linearSearch(products, "Monitor");
        System.out.println("Search 'Monitor': " + (result2 != null ? result2 : "Not found"));

        // binary search
        System.out.println("\n-- Binary Search --");
        Product result3 = SearchAlgorithms.binarySearch(sortedProducts, "Laptop");
        System.out.println("Search 'Laptop': " + (result3 != null ? result3 : "Not found"));

        Product result4 = SearchAlgorithms.binarySearch(sortedProducts, "Monitor");
        System.out.println("Search 'Monitor': " + (result4 != null ? result4 : "Not found"));

        System.out.println("\n-- Time Complexity --");
        System.out.println("Linear Search: O(n) - checks every element");
        System.out.println("Binary Search: O(log n) - divides search space in half");
        System.out.println("Binary Search is faster but needs sorted array");
    }
}