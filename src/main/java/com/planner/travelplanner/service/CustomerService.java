package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.dto.customer.CustomerDTOGet;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.mapper.CustomerMapper;
import com.planner.travelplanner.repository.CustomerRepository;
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
    public Customer saveCustomer(final CustomerDTO customerDTO){
        Customer customer = customerMapper.mapToCustomer(customerDTO);
        return customerRepository.save(customer);
    }

    public List<CustomerDTOGet> showAllCustomers(){
        return customerMapper.mapToDTOList(customerRepository.findAll());
    }

    public CustomerDTOGet showCustomerGetById(final long customerId) {
        if (customerRepository.existsById(customerId)){
            return customerMapper.mapToCustomerDTOGet(customerRepository.findById(customerId).get());
        }else{
            throw  new CustomerNotFoundException();
        }
    }
    @Transactional
    public CustomerDTO updateCustomer(final long customerId,final CustomerDTO customerDTO){
        if (customerRepository.existsById(customerId)){
        Customer getCustomer = customerMapper.mapToCustomerForUpdate(customerId,customerDTO);
        Customer update = customerRepository.save(getCustomer);
            return customerMapper.mapToCustomerDTO(update);
        }else {
            throw  new CustomerNotFoundException();
        }
    }
    @Transactional
    public void deleteCustomerById(final long customerId) throws CustomerNotFoundException{
        Optional<Customer>customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            customerRepository.deleteById(customerId);
        }else {
            throw new CustomerNotFoundException();
        }
    }

}
