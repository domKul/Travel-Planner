package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.mapper.CustomerMapper;
import com.planner.travelplanner.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public Customer saveCustomer(final CustomerDTO customerDTO){
        Customer customer = customerMapper.mapToCustomer(customerDTO);
        return customerRepository.save(customer);
    }

    public List<CustomerDTO> showAllCustomers(){
        return customerMapper.mapToDTOList(customerRepository.findAll());
    }

    public CustomerDTO showCustomer(final long customerId)throws CustomerNotFoundException {
        if (customerRepository.existsById(customerId)){
            return customerMapper.mapToCustomerDTO(customerRepository.findById(customerId).get());
        }else{
            throw  new CustomerNotFoundException();
        }
    }

    public void deleteCustomer(final long customerId) throws CustomerNotFoundException{
        Optional<Customer>customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            customerRepository.deleteById(customerId);
        }else {
            throw new CustomerNotFoundException();
        }
    }

}
