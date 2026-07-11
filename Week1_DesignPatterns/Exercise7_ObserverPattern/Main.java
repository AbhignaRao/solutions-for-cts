public class Main {
    public static void main(String[] args) {

        StockMarket apple = new StockMarket("AAPL", 150.00);

        MobileApp mobileApp = new MobileApp("StockTracker");
        WebApp webApp = new WebApp("StockDashboard");

        // register observers
        apple.registerObserver(mobileApp);
        apple.registerObserver(webApp);

        // price changes - all observers notified
        apple.setPrice(155.00);
        apple.setPrice(160.00);

        // deregister mobile app
        System.out.println("\nDeregistering MobileApp...");
        apple.deregisterObserver(mobileApp);

        // only web app notified now
        apple.setPrice(165.00);
    }
}