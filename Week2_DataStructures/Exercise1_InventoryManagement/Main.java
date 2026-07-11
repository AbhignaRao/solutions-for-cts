public class Main {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        // add products
        System.out.println("-- Adding Products --");
        inventory.addProduct(new Product(1, "Laptop", 50, 999.99));
        inventory.addProduct(new Product(2, "Mouse", 200, 29.99));
        inventory.addProduct(new Product(3, "Keyboard", 150, 49.99));

        // display all
        inventory.displayAll();

        // update product
        System.out.println("\n-- Updating Product --");
        inventory.updateProduct(1, 45, 899.99);

        // delete product
        System.out.println("\n-- Deleting Product --");
        inventory.deleteProduct(2);

        // display after changes
        inventory.displayAll();
    }
}