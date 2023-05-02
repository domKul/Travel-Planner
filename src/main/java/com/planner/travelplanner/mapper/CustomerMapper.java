package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.dto.CustomerDTOGet;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomerId(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getBirthdate(),
                customerDTO.getCountry(),
                customerDTO.getCity(),
                customerDTO.getPostalCode(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber(),
                customerDTO.getLogin(),
                customerDTO.getPassword());
    }

    public CustomerDTO mapToCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthdate(),
                customer.getCountry(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getLogin(),
                customer.getPassword());
    }

    public CustomerDTOGet mapToCustomerDTOGet(Customer customer){
        return new CustomerDTOGet(customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthdate(),
                customer.getCountry(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getEmail(),
                customer.getPhoneNumber());
    }
}
