package com.planner.travelplanner.complaint;

import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.customer.CustomerDTO;
import com.planner.travelplanner.customer.CustomerRepository;
import com.planner.travelplanner.customer.CustomerService;
import com.planner.travelplanner.enums.IdType;
import com.planner.travelplanner.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ComplaintServiceTestSuite {

    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    private Complaint complaint;
    private ComplaintDTOCreate complaintDTOCreate;
    private CustomerDTO customerDTO;
    private Customer customer;
    private ComplaintDTOUpdate complaintDTOUpdate;

    public void dataForTests() {
        customer = new Customer(IdType.EMPTY_ID.getId(), "firstName1", "lastName1", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "example@email.com", 1231231, new ArrayList<>());
        customerDTO = new CustomerDTO("firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        complaint = new Complaint(1L, "title", "descritpion", null, "status", 1);
        complaintDTOUpdate = new ComplaintDTOUpdate("update", "update", null, "status");
    }

    @BeforeEach
    void clearData() {
        complaintRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @BeforeEach
    public void clear() {
        clearData();
    }

    @Test
    public void shouldCreateComplaint() {
        //Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", savedCustomer.getCustomerId());
        long cusId = savedCustomer.getCustomerId();
        //When
        Complaint create1 = complaintService.createComplaint(complaintDTOCreate);
        Complaint create2 = complaintService.createComplaint(complaintDTOCreate);
        List<Complaint> listOfComplaints = complaintRepository.findAll();
        //Then
        assertEquals("titledto", listOfComplaints.get(0).getTitle());
        assertEquals("titledto", listOfComplaints.get(1).getTitle());

    }

    @Test
    public void shouldThrowCustomerNotFoundOnWrongId() {
        //Given
        dataForTests();
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", IdType.EMPTY_ID.getId());
        //When & Then
        assertThrows(NotFoundException.class, () -> {
            complaintService.createComplaint(complaintDTOCreate);
        });
    }

    @Test
    public void shouldShowAllComplaints() {
        //Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", savedCustomer.getCustomerId());
        long cusId = savedCustomer.getCustomerId();
        //When
        List<ComplaintDTO> emptyList = complaintService.showAllComplaints();
        Complaint create1 = complaintService.createComplaint(complaintDTOCreate);
        Complaint create2 = complaintService.createComplaint(complaintDTOCreate);
        List<ComplaintDTO> allComplaints = complaintService.showAllComplaints();
        //Then
        assertEquals(0, emptyList.size());
        assertEquals(2, allComplaints.size());
    }

    @Test
    public void shouldShowComplaintsByID() {
        //Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", savedCustomer.getCustomerId());
        long cusId = savedCustomer.getCustomerId();
        //When
        Complaint create1 = complaintService.createComplaint(complaintDTOCreate);
        ComplaintDTO findingComplaint = complaintService.showComplaintById(create1.getComplaintId());
        //Thne
        assertEquals(complaintDTOCreate.getTitle(), findingComplaint.getTitle());
    }

    @Test
    public void shouldModifyExistingComplaint() {
        //Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", savedCustomer.getCustomerId());
        long cusId = savedCustomer.getCustomerId();
        //When
        Complaint create1 = complaintService.createComplaint(complaintDTOCreate);
        ComplaintDTO update = complaintService.putComplaintStatus(create1.getComplaintId(), complaintDTOUpdate);
        //Then
        assertEquals(create1.getComplaintId(), update.getComplaintId());
    }

    @Test
    public void shouldDeleteComplaintsByID() {
        //Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", savedCustomer.getCustomerId());
        long cusId = savedCustomer.getCustomerId();
        //When
        Complaint create1 = complaintService.createComplaint(complaintDTOCreate);
        create1.setCustomer(customer);
        List<ComplaintDTO> findOne = complaintService.showAllComplaints();
        complaintService.deleteComplainByID(create1.getComplaintId());
        //Thne
        assertEquals(1, findOne.size());
        assertEquals(0, complaintService.showAllComplaints().size());
    }
}
