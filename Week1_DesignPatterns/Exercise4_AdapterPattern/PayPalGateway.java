// Adaptee 1 - has its own method name
public class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via PayPal");
    }
}