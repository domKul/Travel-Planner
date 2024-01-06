package com.planner.travelplanner.customer;

import com.planner.travelplanner.exception.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

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

    public List<CustomerDTOGet> showAllCustomers() {
        return customerMapper.mapToDTOList(customerRepository.findAll());
    }

    public CustomerDTOGet showCustomerGetById(final long customerId) {
        if (customerRepository.existsById(customerId)) {
            return customerMapper.mapToCustomerDTOGet(customerRepository.findById(customerId).get());
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Transactional
    public CustomerDTO updateCustomer(final long customerId, final CustomerDTO customerDTO) {
        if (customerRepository.existsById(customerId)) {
            Customer getCustomer = customerMapper.mapToCustomerForUpdate(customerId, customerDTO);
            Customer update = customerRepository.save(getCustomer);
            return customerMapper.mapToCustomerDTO(update);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Transactional
    public void deleteCustomerById(final long customerId) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException();
        }
    }

}
