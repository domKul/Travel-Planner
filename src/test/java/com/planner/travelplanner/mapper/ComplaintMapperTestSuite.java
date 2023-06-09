package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTO;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOCreate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComplaintMapperTestSuite {
    @Autowired
    private ComplaintMapper complaintMapper;

    private Complaint complaint1;
    private Complaint complaint2;
    private ComplaintDTOCreate complaintDTOCreate;
    private Customer customer;
    private ComplaintDTO complaintDTO;

    private void dataForTests(){
        LocalDateTime complaintDate = LocalDateTime.of(2023, 5, 15, 12, 0);
        customer = new Customer(0, "firstName", "lastName", new Date(2020,02,02), "string","string", "string", "string", "string", 1231231, new ArrayList<>(), new ArrayList<>());
        complaint1 = new Complaint(1,"title1","description1",complaintDate,"test1", customer.getCustomerId());
        complaint2 = new Complaint(1,"title2","description2",complaintDate,"test2", customer.getCustomerId());
        complaintDTO = new ComplaintDTO(1L,"title2","description2",complaintDate,"test2", 1L);
        complaintDTOCreate = new ComplaintDTOCreate("title2","description2",complaintDate,"test2", 1L);
    }

    @Test
    public void shouldMpToComplaintDTO(){
        //Given
        dataForTests();

        //When
        ComplaintDTO mapping = complaintMapper.mapToComplaintDTO(complaint1);

        //Then
        assertEquals(complaint1.getDescription(),mapping.getDescription());

    }

    @Test
    public void shouldMapToComplaintCreate(){
        //Give
        dataForTests();

        //When
        Complaint mappingFromDTO = ComplaintMapper.mapFromComplaintCreate(complaintDTOCreate);

        //Then
        assertEquals(Complaint.class,mappingFromDTO.getClass());
        assertEquals(complaintDTOCreate.getComplaintDate(),mappingFromDTO.getComplaintDate());
        assertEquals(complaintDTOCreate.getTitle(),mappingFromDTO.getTitle());
        assertEquals(complaintDTOCreate.getStatus(),mappingFromDTO.getStatus());
    }

    @Test
    public void shouldMapToComplaintDTO(){
        //Give
        dataForTests();

        //When
        Complaint mappingFromDTO = complaintMapper.mapFromComplaintCreate(complaintDTOCreate);

        //Then
        assertEquals(Complaint.class,mappingFromDTO.getClass());
        assertEquals(complaintDTOCreate.getComplaintDate(),mappingFromDTO.getComplaintDate());
        assertEquals(complaintDTOCreate.getTitle(),mappingFromDTO.getTitle());
        assertEquals(complaintDTOCreate.getStatus(),mappingFromDTO.getStatus());
    }

    @Test
    public void shoudlMapToComplaintDTOFormShow(){
        //Given
        dataForTests();
        complaint1.setCustomer(customer);

        //When
        ComplaintDTO mapping = complaintMapper.mapToComplaintDTOFormShow(complaint1);

        //When
        assertEquals(ComplaintDTO.class,mapping.getClass());
        assertEquals(complaint1.getTitle(),mapping.getTitle());
        assertEquals(complaint1.getDescription(),mapping.getDescription());
        assertEquals(complaint1.getStatus(),mapping.getStatus());
    }

    @Test
    public void shouldMapToListDTO(){
        //GIven
        dataForTests();
        customer.getComplaints().add(complaint1);
        complaint1.setCustomer(customer);
        customer.getComplaints().add(complaint2);
        complaint2.setCustomer(customer);


        //When
        List<ComplaintDTO> mappinglistToDTO = complaintMapper.mapToListDTO(customer.getComplaints());

        //Then
        assertEquals(2,mappinglistToDTO.size());
        assertEquals(complaint1.getTitle(),mappinglistToDTO.get(0).getTitle());
        assertEquals(complaint1.getDescription(),mappinglistToDTO.get(0).getDescription());
    }


}
