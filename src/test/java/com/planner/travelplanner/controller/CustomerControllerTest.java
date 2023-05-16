package com.planner.travelplanner.controller;

import com.planner.travelplanner.mapper.CustomerMapper;
import com.planner.travelplanner.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

@SpringJUnitWebConfig
@WebMvcTest(CustomerController.class)
@Import(CustomerMapper.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

//    @Test
//    void shouldGetAllCustomers() throws Exception{
//        //Given
//        List<CustomerDTOGet> customers = List.of(
//                  new CustomerDTOGet( 1L,"firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>()));
//                  new CustomerDTOGet( 2L,"firstName", "lastName", new Date(2020,02,02), "string","string", "string", "string", "string", 1231231, new ArrayList<>());
//        given(customerService.showAllCustomers()).willReturn(customers);
//
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/v1/customers/getCustomers")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.size()", Matchers.is(2)))
//                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L))
//                .andExpect((ResultMatcher) jsonPath("$[1].firstName").value("firstName"))
//                .andExpect((ResultMatcher) jsonPath("$[0].firstName").value("firstName"));
//
//
//    }
}
