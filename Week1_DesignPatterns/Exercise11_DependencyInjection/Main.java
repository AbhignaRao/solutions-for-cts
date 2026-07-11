public class Main {
    public static void main(String[] args) {

        // inject dependency from outside
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        // use service
        System.out.println("-- Customer Lookup --");
        System.out.println("C001: " + service.getCustomerName("C001"));
        System.out.println("C002: " + service.getCustomerName("C002"));
        System.out.println("C003: " + service.getCustomerName("C003"));
        System.out.println("C999: " + service.getCustomerName("C999"));
    }
}