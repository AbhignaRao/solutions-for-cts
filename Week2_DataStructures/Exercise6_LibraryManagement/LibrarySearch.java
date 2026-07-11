public class LibrarySearch {

    // O(n) - no sorting needed
    public static Book linearSearch(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    // O(log n) - array must be sorted by title
    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = books[mid].getTitle().compareToIgnoreCase(title);

            if (compare == 0) return books[mid];
            else if (compare < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}