package com.planner.travelplanner.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("getCustomers")
    ResponseEntity<List<CustomerDTOGet>> getAllCustomers() {
        return ResponseEntity.ok(customerService.showAllCustomers());
    }

    @GetMapping(value = "{customerId}")
    ResponseEntity<CustomerDTOGet> getCustomerById(@PathVariable long customerId) {
        return ResponseEntity.ok(customerService.showCustomerGetById(customerId));
    }

    @PutMapping(value = "{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDTO> updateCustomer(@PathVariable long customerId, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customerDTO));
    }

    @DeleteMapping(value = "{customerId}")
    ResponseEntity<Void> deleteCustomer(@PathVariable long customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
