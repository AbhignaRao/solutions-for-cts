public class Main {
    public static void main(String[] args) {

        // PayPal payment
        PayPalGateway payPal = new PayPalGateway();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        payPalProcessor.processPayment(150.00);

        // Stripe payment
        StripeGateway stripe = new StripeGateway();
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
        stripeProcessor.processPayment(250.00);
    }
}