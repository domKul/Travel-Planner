package com.planner.travelplanner.customer;

import com.planner.travelplanner.exception.CustomerNotFoundException;
import com.planner.travelplanner.jpa.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService extends AbstractRepository<CustomerRepository, Customer> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Transactional
    public Customer saveCustomer(final CustomerDTO customerDTO) {
        Customer customer = customerMapper.mapToCustomer(customerDTO);
        return customerRepository.save(customer);
    }

    List<CustomerDTOGet> showAllCustomers() {
        List<Customer> all = customerRepository.findAll();
        if (all.isEmpty()) {
            return new ArrayList<>();
        }
        return customerMapper.mapToDTOList(all);
    }

    CustomerDTOGet showCustomerGetById(final long customerId) {
        Customer customerOrThrow = findCustomerOrThrow(customerId);
        return customerMapper.mapToCustomerDTOGet(customerOrThrow);

    }

    @Transactional
    public CustomerDTO updateCustomer(final long customerId, final CustomerDTO customerDTO) {
        Customer customerOrThrow = findCustomerOrThrow(customerId);
        Customer getCustomer = customerMapper.mapToCustomerForUpdate(customerOrThrow.getCustomerId(), customerDTO);
        Customer update = customerRepository.save(getCustomer);
        LOGGER.info("Customer updated");
        return customerMapper.mapToCustomerDTO(update);

    }

    @Transactional
    public void deleteCustomerById(final long customerId) throws CustomerNotFoundException {
        Customer customerOrThrow = findCustomerOrThrow(customerId);
        LOGGER.info("Customer deleted");
        customerRepository.deleteById(customerOrThrow.getCustomerId());
    }

    public Customer findCustomerOrThrow(long id) {
        return findEntity(customerRepository, id);
    }
}
