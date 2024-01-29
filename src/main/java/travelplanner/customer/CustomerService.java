package travelplanner.customer;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import travelplanner.enums.ExceptionMessages;
import travelplanner.exception.AlreadyExistException;
import travelplanner.jpa.AbstractRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService extends AbstractRepository<CustomerRepository, Customer> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

     CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

     Customer saveCustomer(final CustomerDTO customerDTO) {
         if (customerRepository.existsByFirstNameAndLastName(customerDTO.getFirstName(), customerDTO.getLastName())){
             throw new AlreadyExistException(ExceptionMessages.TRAVELER_ALREADY_EXIST);
         }
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

     CustomerDTO updateCustomer(final long customerId, final CustomerDTO customerDTO) {
        Customer customerOrThrow = findCustomerOrThrow(customerId);
        Customer getCustomer = customerMapper.mapToCustomerForUpdate(customerOrThrow.getCustomerId(), customerDTO);
        Customer update = customerRepository.save(getCustomer);
        LOGGER.info("Customer updated");
        return customerMapper.mapToCustomerDTO(update);

    }

     void deleteCustomerById(final long customerId) {
        Customer customerOrThrow = findCustomerOrThrow(customerId);
        customerRepository.deleteById(customerOrThrow.getCustomerId());
        LOGGER.info("Customer deleted");
    }

    public Customer findCustomerOrThrow(long id) {
        return findEntity(customerRepository, id);
    }

    public boolean isCustomerExistById(long customerId){
        return existEntityById(customerRepository,customerId);
    }
}
