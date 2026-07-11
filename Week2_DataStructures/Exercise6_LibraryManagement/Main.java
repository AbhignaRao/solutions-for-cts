public class Main {
    public static void main(String[] args) {

        // unsorted - for linear search
        Book[] books = {
            new Book(1, "Java Programming", "James Gosling"),
            new Book(2, "Clean Code", "Robert Martin"),
            new Book(3, "Design Patterns", "Gang of Four"),
            new Book(4, "Algorithms", "Thomas Cormen"),
            new Book(5, "Spring Boot", "Craig Walls")
        };

        // sorted by title - for binary search
        Book[] sortedBooks = {
            new Book(4, "Algorithms", "Thomas Cormen"),
            new Book(2, "Clean Code", "Robert Martin"),
            new Book(3, "Design Patterns", "Gang of Four"),
            new Book(1, "Java Programming", "James Gosling"),
            new Book(5, "Spring Boot", "Craig Walls")
        };

        // linear search
        System.out.println("-- Linear Search --");
        Book result1 = LibrarySearch.linearSearch(books, "Clean Code");
        System.out.println("Search 'Clean Code': " + 
                          (result1 != null ? result1 : "Not found"));

        Book result2 = LibrarySearch.linearSearch(books, "Python");
        System.out.println("Search 'Python': " + 
                          (result2 != null ? result2 : "Not found"));

        // binary search
        System.out.println("\n-- Binary Search --");
        Book result3 = LibrarySearch.binarySearch(sortedBooks, "Design Patterns");
        System.out.println("Search 'Design Patterns': " + 
                          (result3 != null ? result3 : "Not found"));

        Book result4 = LibrarySearch.binarySearch(sortedBooks, "Python");
        System.out.println("Search 'Python': " + 
                          (result4 != null ? result4 : "Not found"));

        System.out.println("\n-- Time Complexity --");
        System.out.println("Linear Search: O(n) - works on unsorted data");
        System.out.println("Binary Search: O(log n) - needs sorted data");
        System.out.println("Use Linear for small/unsorted, Binary for large/sorted");
    }
}