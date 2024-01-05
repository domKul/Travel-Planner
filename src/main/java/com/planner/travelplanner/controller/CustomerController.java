package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.dto.customer.CustomerDTOGet;
import com.planner.travelplanner.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("getCustomers")
    public ResponseEntity<List<CustomerDTOGet>> getAllCustomers() {
        return ResponseEntity.ok(customerService.showAllCustomers());
    }

    @GetMapping(value = "{customerId}")
    public ResponseEntity<CustomerDTOGet> getCustomerById(@PathVariable long customerId) {
        return ResponseEntity.ok(customerService.showCustomerGetById(customerId));
    }

    @PutMapping(value = "{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable long customerId, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customerDTO));
    }

    @DeleteMapping(value = "{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
