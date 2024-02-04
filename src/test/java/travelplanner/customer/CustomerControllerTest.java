package travelplanner.customer;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import travelplanner.customer.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(CustomerController.class)
//@Import( CustomerMapper.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CustomerController customerController;

    @BeforeEach
    void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setViewResolvers(viewResolver)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    void shouldGetAllCustomers() throws Exception {
        // Given
        List<CustomerDTOGet> customers = List.of(
                new CustomerDTOGet(1L, "firstName1", "lastName1", new Date(2020, 2, 2), "string", "string", "string", "string", "example@email.com", 1231231, new ArrayList<>()),
                new CustomerDTOGet(2L, "firstName2", "lastName2", new Date(2020, 2, 2), "string", "string", "string", "string", "example@email.com", 1231231, new ArrayList<>())
        );
        given(customerService.showAllCustomers()).willReturn(customers);
        // When & Then
        mockMvc.perform(get("/v1/customers/getCustomers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("firstName1"))
                .andExpect(jsonPath("$[1].firstName").value("firstName2"));
    }

    @Test
    void shouldAddCustomer() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO("string", "string", new Date(), "string", "string", "string", "string", "example@email.com", 123);
        Customer customer = new Customer(1L, "string", "string", new Date(), "string", "string", "string", "string", "example@email.com", 123);
        given(customerService.saveCustomer(any(CustomerDTO.class))).willReturn(customer);
        // When & Then
        mockMvc.perform(post("/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindCustomerByIdInDB() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO("string", "string", new Date(), "string", "string", "string", "string", "example@email.com", 123);
        Customer customer = new Customer(1L, "string", "string", new Date(), "string", "string", "string", "string", "example@email.com", 123);
        CustomerDTOGet customerget = new CustomerDTOGet(1L, "string", "string", new Date(), "string", "string", "string", "string", "example@email.com", 123, new ArrayList<>());
        given(customerService.saveCustomer(customerDTO)).willReturn(customer);
        given(customerService.showCustomerGetById(customerget.getCustomerId())).willReturn(customerget);
        // When & Then
        mockMvc.perform(get("/v1/customers/" + customerget.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerget.getCustomerId()))
                .andExpect(jsonPath("$.firstName").value(customerget.getFirstName()));
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        // Given
        long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO("newFirstName", "newLastName", new Date(), "newAddress", "newCity", "newState", "newCountry", "newEmail", 123456789);
        Customer updatedCustomer = new Customer(customerId, "newFirstName", "newLastName", new Date(), "newAddress", "newCity", "newState", "newCountry", "newEmail", 123456789);
        CustomerDTO updatedCustomerDTO = new CustomerDTO("newFirstName", "newLastName", new Date(), "newAddress", "newCity", "newState", "newCountry", "newEmail", 123456789);
        given(customerService.saveCustomer(customerDTO)).willReturn(updatedCustomer);
        given(customerService.updateCustomer(customerId, customerDTO)).willReturn(updatedCustomerDTO);
        // When & Then
        mockMvc.perform(put("/v1/customers/" + updatedCustomer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCustomerFromDB() throws Exception {
        // Given
        long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO("newFirstName", "newLastName", new Date(), "newAddress", "newCity", "newState", "newCountry", "newEmail", 123456789);
        Customer updatedCustomer = new Customer(customerId, "newFirstName", "newLastName", new Date(), "newAddress", "newCity", "newState", "newCountry", "newEmail", 123456789);
        given(customerService.saveCustomer(customerDTO)).willReturn(updatedCustomer);
        // When
        mockMvc.perform(delete("/v1/customers/{id}", customerId))
                .andExpect(status().isAccepted());
        // Then
        verify(customerService).deleteCustomerById(updatedCustomer.getCustomerId());
    }
}
