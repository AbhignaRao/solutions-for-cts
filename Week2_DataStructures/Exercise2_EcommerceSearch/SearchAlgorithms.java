public class SearchAlgorithms {

    // O(n) - checks every element one by one
    public static Product linearSearch(Product[] products, String name) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // O(log n) - array must be sorted, divides search space in half each time
    public static Product binarySearch(Product[] products, String name) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = products[mid].getProductName()
                            .compareToIgnoreCase(name);

            if (compare == 0) return products[mid];
            else if (compare < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}