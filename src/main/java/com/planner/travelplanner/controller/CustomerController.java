package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.dto.ToursDTO;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>>getAllCustomers(){
        return ResponseEntity.ok(customerService.showAllCustomers());
    }

    @GetMapping(value = "{customerId}")
    public ResponseEntity<CustomerDTO>getTourById(@PathVariable long customerId)throws CustomerNotFoundException {
        return ResponseEntity.ok(customerService.showCustomer(customerId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToursDTO>updateCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "{customerId}")
    public ResponseEntity<Void>deleteCustomer(@PathVariable long customerId)throws CustomerNotFoundException{
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
    }
}
