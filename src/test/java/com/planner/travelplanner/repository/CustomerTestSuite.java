package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Customer;
import org.hibernate.sql.ast.tree.expression.CaseSimpleExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerTestSuite {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1;
    private Customer customer2;

    private void dataForTests(){
        customer1 = new Customer(0L,"firstName1","lastName1",new Date(2000,2,11),"country","city","streetName","postalCode","email",1231231,"login","password",new ArrayList<>(), new ArrayList<>() );
        customer2 = new Customer(0L,"firstName2","lastName2",new Date(2000,2,11),"country","city","streetName","postalCode","email",1231231,"login","password",new ArrayList<>(), new ArrayList<>() );
    }

    @Test
    public void shouldCreateCustomer(){
        //Given
        dataForTests();

        //When
        Customer createCustomer = customerRepository.save(customer1);

        //Then
        assertEquals(1,customerRepository.count());
        assertEquals("firstName1",createCustomer.getFirstName());

        //CleanUp
        customerRepository.deleteAll();
    }

    @Test
    public void shouldDeleteCustomerById(){

        //Given
        dataForTests();

        //When
        Customer newCustomer1 = customerRepository.save(customer1);
        Customer newCustomer2 = customerRepository.save(customer2);
        customerRepository.deleteById(newCustomer2.getCustomerId());

        //Then
        assertEquals(1,customerRepository.count());
        assertEquals("firstName1",newCustomer1.getFirstName());

        //CleanUp
        customerRepository.deleteAll();

    }

    @Test
    public void shouldFinaAllCustomers(){
        //Given
        dataForTests();

        //When
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        List<Customer> findAll = customerRepository.findAll();

        //Then
        assertEquals(2,findAll.size());


        //CleanUp
        customerRepository.deleteAll();
    }

    @Test
    public void shouldModifyCustomer(){
        //Given
        dataForTests();

        //When
        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);
        savedCustomer1.setFirstName("UpdatedFirstName");

        //Then
        assertEquals("UpdatedFirstName",savedCustomer1.getFirstName());
        assertEquals("firstName2",savedCustomer2.getFirstName());

        //CleanUp
        customerRepository.deleteAll();
    }
}
