public class Main {
    public static void main(String[] args) {

        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // first call - loads from server
        System.out.println("-- First display --");
        image1.display();

        // second call - uses cache
        System.out.println("\n-- Second display (cached) --");
        image1.display();

        // different image - loads from server
        System.out.println("\n-- Different image --");
        image2.display();
    }
}