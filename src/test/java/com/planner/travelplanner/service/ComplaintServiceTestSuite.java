package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTO;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOCreate;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOUpdate;
import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.mapper.IdType;
import com.planner.travelplanner.repository.ComplaintRepository;
import com.planner.travelplanner.repository.CustomerRepository;
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
public class ComplaintServiceTestSuite {

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
        customer = new Customer(IdType.EMPTY_ID.getId(), "firstName1", "lastName1", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        customerDTO = new CustomerDTO( "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        complaint = new Complaint(1L, "title", "descritpion", null, "status", new Customer());
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", null, "status", IdType.EMPTY_ID.getId());
        complaintDTOUpdate = new ComplaintDTOUpdate("update", "update", null, "status");


    }
    private void clearData(){
        customerRepository.deleteAll();
    }
    @BeforeEach
    public void clear(){
        clearData();
    }

    @Test
    public void shouldCreateComplaint() {
        //Given
        dataForTests();
        Customer savedCustomer =customerService.saveCustomer(customerDTO);
        long cusId = savedCustomer.getCustomerId();

        //When
        Complaint create1 = complaintService.createComplaint(cusId, complaintDTOCreate);
        Complaint create2 =complaintService.createComplaint(cusId, complaintDTOCreate);
        List<Complaint> listOfComplaints = complaintRepository.findAll();

        //Then
        assertEquals("titledto", listOfComplaints.get(0).getTitle());
        assertEquals("titledto", listOfComplaints.get(1).getTitle());

        //CleanUp
        long complId1 =create1.getComplaintId();
        long complId2 =create2.getComplaintId();
        customerRepository.deleteById(cusId);
        complaintRepository.deleteById(complId1);
        complaintRepository.deleteById(complId2);

    }

    @Test
    public void shouldThrowCustomerNotFoundOnWrongId() {
        //Given
        dataForTests();
        long cusId = 1321;

        //When & Then
        assertThrows(CustomerNotFoundException.class, () -> {
            complaintService.createComplaint(cusId, complaintDTOCreate);
        });
    }

    @Test
    public void shouldShowAllComplaints(){
        //Given
        dataForTests();
        Customer savedCustomer =customerService.saveCustomer(customerDTO);
        long cusId = savedCustomer.getCustomerId();

        //When
        List<ComplaintDTO>emptyList =  complaintService.showAllComplaints();
        Complaint create1 = complaintService.createComplaint(cusId, complaintDTOCreate);
        Complaint create2 =complaintService.createComplaint(cusId, complaintDTOCreate);
        List<ComplaintDTO>allComplaints =  complaintService.showAllComplaints();

        //Thne
        assertEquals(0,emptyList.size());
        assertEquals(2,allComplaints.size());

        //CleanUp
        long complId1 =create1.getComplaintId();
        long complId2 =create2.getComplaintId();
        customerRepository.deleteById(cusId);
        complaintRepository.deleteById(complId1);
        complaintRepository.deleteById(complId2);
    }

    @Test
    public void shouldShowComplaintsByID(){
        //Given
        dataForTests();
        Customer savedCustomer =customerService.saveCustomer(customerDTO);
        long cusId = savedCustomer.getCustomerId();

        //When
        Complaint create1 = complaintService.createComplaint(cusId, complaintDTOCreate);
        ComplaintDTO findingComplaint = complaintService.showComplaintById(create1.getComplaintId());

        //Thne
        assertEquals(complaintDTOCreate.getTitle(),findingComplaint.getTitle());


        //CleanUp
        long complId1 =create1.getComplaintId();
        customerRepository.deleteById(cusId);
        complaintRepository.deleteById(complId1);
    }

    @Test
    public void shouldModifyExistingComplaint(){
        //Given
        dataForTests();
        Customer savedCustomer =customerService.saveCustomer(customerDTO);
        long cusId = savedCustomer.getCustomerId();

        //When
        Complaint create1 = complaintService.createComplaint(cusId, complaintDTOCreate);
        ComplaintDTO update = complaintService.modifyComplaintStatus(create1.getComplaintId(),complaintDTOUpdate);

        //Then
        assertEquals(create1.getComplaintId(),update.getComplaintId());

        //CleanUp
        long complId1 =create1.getComplaintId();
        customerRepository.deleteById(cusId);
        complaintRepository.deleteById(complId1);
    }

    @Test
    public void shouldDeleteComplaintsByID(){
        //Given
        dataForTests();
        Customer savedCustomer =customerService.saveCustomer(customerDTO);
        long cusId = savedCustomer.getCustomerId();

        //When
        Complaint create1 = complaintService.createComplaint(cusId, complaintDTOCreate);
        create1.setCustomer(customer);
        List<ComplaintDTO> findOne =  complaintService.showAllComplaints();
        complaintService.deleteComplainByID(create1.getComplaintId());

        //Thne
        assertEquals(1,findOne.size());
        assertEquals(0,complaintService.showAllComplaints().size());

        //CleanUp
        long complId1 =create1.getComplaintId();
        customerRepository.deleteById(cusId);
        complaintRepository.deleteById(complId1);
    }


}
