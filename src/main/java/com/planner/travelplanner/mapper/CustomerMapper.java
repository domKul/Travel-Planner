package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.dto.customer.CustomerDTOGet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {


    public Customer mapToCustomer(CustomerDTO customerDTO) {
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
                null, null);
    }

    public Customer mapToCustomerForUpdate(final long customerId, CustomerDTO customerDTO) {
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
                null, null);
    }

    public CustomerDTO mapToCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthdate(),
                customer.getCountry(),
                customer.getCity(),
                customer.getStreetName(),
                customer.getPostalCode(),
                customer.getEmail(),
                customer.getPhoneNumber());
    }

    public CustomerDTOGet mapToCustomerDTOGet(Customer customer) {
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

    public List<CustomerDTOGet> mapToDTOList(final List<Customer> customers) {
        return customers.stream()
                .map(this::mapToCustomerDTOGet)
                .toList();
    }
}
