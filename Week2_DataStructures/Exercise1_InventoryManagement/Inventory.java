import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // HashMap gives O(1) add, update, delete
    private Map<Integer, Product> products = new HashMap<>();

    // O(1) - add product
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Added: " + product);
    }

    // O(1) - update product
    public void updateProduct(int productId, int quantity, double price) {
        Product product = products.get(productId);
        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Updated: " + product);
        } else {
            System.out.println("Product not found: " + productId);
        }
    }

    // O(1) - delete product
    public void deleteProduct(int productId) {
        Product removed = products.remove(productId);
        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Product not found: " + productId);
        }
    }

    // O(n) - display all products
    public void displayAll() {
        System.out.println("\n-- All Products --");
        if (products.isEmpty()) {
            System.out.println("No products in inventory");
            return;
        }
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }
}