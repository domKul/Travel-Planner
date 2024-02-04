package travelplanner.customer;

import org.springframework.stereotype.Component;
import travelplanner.enums.IdType;

import java.util.List;

@Component
class CustomerMapper {

    Customer mapToCustomer(CustomerDTO customerDTO) {
        return new Customer(IdType.EMPTY_ID.getId(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getBirthdate(),
                customerDTO.getCountry(),
                customerDTO.getCity(),
                customerDTO.getStreetName(),
                customerDTO.getPostalCode(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber());
    }

    Customer mapToCustomerForUpdate(final long customerId, CustomerDTO customerDTO) {
        return new Customer(customerId,
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getBirthdate(),
                customerDTO.getCountry(),
                customerDTO.getCity(),
                customerDTO.getStreetName(),
                customerDTO.getPostalCode(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber());
    }

    CustomerDTO mapToCustomerDTO(Customer customer) {
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

    CustomerDTOGet mapToCustomerDTOGet(Customer customer) {
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

    List<CustomerDTOGet> mapToDTOList(final List<Customer> customers) {
        return customers.stream()
                .map(this::mapToCustomerDTOGet)
                .toList();
    }
}
