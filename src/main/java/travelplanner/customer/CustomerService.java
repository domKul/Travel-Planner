package travelplanner.customer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.exception.AlreadyExistException;
import travelplanner.exception.ExceptionMessages;
import travelplanner.jpa.AbstractRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService extends AbstractRepository<CustomerRepository, Customer> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

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
         SimpleCustomerQueryDto customerOrThrow = findCustomerOrThrow(customerId);
        return customerMapper.mapToCustomerDTOGet(Customer.mapFromQueryDto(customerOrThrow));

    }

     CustomerDTO updateCustomer(final long customerId, final CustomerDTO customerDTO) {
         SimpleCustomerQueryDto customerOrThrow = findCustomerOrThrow(customerId);
        Customer getCustomer = customerMapper.mapToCustomerForUpdate(customerOrThrow.getCustomerId(), customerDTO);
        Customer update = customerRepository.save(getCustomer);
        LOGGER.info("Customer updated");
        return customerMapper.mapToCustomerDTO(update);

    }

     void deleteCustomerById(final long customerId) {
         SimpleCustomerQueryDto customerOrThrow = findCustomerOrThrow(customerId);
        customerRepository.deleteById(customerOrThrow.getCustomerId());
        LOGGER.info("Customer deleted");
    }

    public SimpleCustomerQueryDto findCustomerOrThrow(long id) {
        Customer entity = findEntity(customerRepository, id);
        return Customer.mapToQueryDto(entity);

    }

    public boolean isCustomerExistById(long customerId){
        return existEntityById(customerRepository,customerId);
    }
}
