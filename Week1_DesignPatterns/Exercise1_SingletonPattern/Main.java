public class Main {
    public static void main(String[] args) {
        
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        logger1.log("First message");
        logger2.log("Second message");
        
        // prove both are SAME object
        System.out.println("Same instance? " + (logger1 == logger2));
    }
}