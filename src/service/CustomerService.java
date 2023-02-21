package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CustomerService {

    private static CustomerService instance;

    private static Collection<Customer> customers = new ArrayList<>();

    public static CustomerService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CustomerService();
        }

        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        customers.add(new Customer(firstName,lastName,email));
    }

    public Customer getCustomer(String customerEmail) {

        return customers.stream()
                .filter(s -> s.email().equals(customerEmail))
                .findFirst()
                .orElse(null);
    }

    public Collection<Customer> getAllCustomers() {

        return customers;
    }
}
