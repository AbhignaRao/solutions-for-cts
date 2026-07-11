public class Main {
    public static void main(String[] args) {

        // pay with credit card
        System.out.println("-- Credit Card Payment --");
        PaymentContext context = new PaymentContext(
            new CreditCardPayment("1234-5678-9012-3456", "Guna"));
        context.executePayment(500.00);

        // switch to PayPal at runtime
        System.out.println("\n-- PayPal Payment --");
        context.setPaymentStrategy(
            new PayPalPayment("guna@gmail.com"));
        context.executePayment(300.00);
    }
}