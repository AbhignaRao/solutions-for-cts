public class CustomerService {
    private CustomerRepository customerRepository;

    // constructor injection - dependency injected from outside
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getCustomerName(String id) {
        return customerRepository.findCustomerById(id);
    }
}