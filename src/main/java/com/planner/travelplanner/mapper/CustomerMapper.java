package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.dto.CustomerDTOGet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {


    public Customer mapToCustomer(CustomerDTO customerDTO){
        return new Customer(IdType.EMPTY_ID.getId(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getBirthdate(),
                customerDTO.getCountry(),
                customerDTO.getCity(),
                customerDTO.getStreetName(),
                customerDTO.getPostalCode(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber(),
                customerDTO.getLogin(),
                customerDTO.getPassword(),
                null,null);
    }
    public Customer mapToCustomerForUpdate(final long customerId, CustomerDTO customerDTO){
        return new Customer(customerId,
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getBirthdate(),
                customerDTO.getCountry(),
                customerDTO.getCity(),
                customerDTO.getStreetName(),
                customerDTO.getPostalCode(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber(),
                customerDTO.getLogin(),
                customerDTO.getPassword(),
                null,null);
    }

    public CustomerDTO mapToCustomerDTO(Customer customer){
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthdate(),
                customer.getCountry(),
                customer.getCity(),
                customer.getStreetName(),
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
                customer.getStreetName(),
                customer.getPostalCode(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getComplaints());
    }
    public Customer mapToCustomerFromDTOGet(CustomerDTOGet customerDTOGet){
        return new Customer(customerDTOGet.getCustomerId(),
                customerDTOGet.getFirstName(),
                customerDTOGet.getLastName(),
                customerDTOGet.getBirthdate(),
                customerDTOGet.getCountry(),
                customerDTOGet.getCity(),
                customerDTOGet.getStreetName(),
                customerDTOGet.getEmail(),
                customerDTOGet.getPhoneNumber(),
                null,null);
    }

    public List<CustomerDTOGet>mapToDTOList(final List<Customer>customers){
        return customers.stream()
                .map(this::mapToCustomerDTOGet)
                .toList();
    }
}
