public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        // simulating database lookup
        if (id.equals("C001")) return "Guna Byraju";
        if (id.equals("C002")) return "John Doe";
        if (id.equals("C003")) return "Jane Smith";
        return "Customer not found";
    }
}