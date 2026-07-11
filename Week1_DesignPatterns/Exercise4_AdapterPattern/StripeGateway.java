// Adaptee 2 - has its own method name
public class StripeGateway {
    public void executePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Stripe");
    }
}