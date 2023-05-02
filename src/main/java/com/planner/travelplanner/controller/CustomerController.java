package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.dto.ToursDTO;
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
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "customerId")
    public ResponseEntity<CustomerDTO>getTourById(@PathVariable Long customerId){
        return ResponseEntity.ok(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToursDTO>updateCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok().build();
    }
}
