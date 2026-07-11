public class Logger {
    
    // single instance - only one will ever exist
    private static Logger instance;
    
    // private constructor - nobody can do "new Logger()" from outside
    private Logger() {
        System.out.println("Logger instance created");
    }
    
    // global access point
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}