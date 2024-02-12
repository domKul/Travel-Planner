package travelplanner.complaint;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import travelplanner.customer.CustomerService;
import travelplanner.enums.IdType;
import travelplanner.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ComplaintServiceTest {

    @InjectMocks
    private ComplaintService complaintService;
    @Mock
    private ComplaintRepository complaintRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private ComplaintMapper complaintMapper;
    private Complaint complaint;
    private ComplaintDTOCreate complaintDTOCreate;

    @Test
    public void shouldCreateComplaint() {
        // Given
        complaintDTOCreate = new ComplaintDTOCreate("title", "description", "status", 12L);
        complaint = new Complaint(1L, "title", "description", null, "status", 1);
        when(customerService.isCustomerExistById(complaintDTOCreate.getCustomerId())).thenReturn(true);
        when(complaintMapper.mapFromComplaintCreate(complaintDTOCreate, customerService)).thenReturn(complaint);
        when(complaintRepository.save(complaint)).thenReturn(complaint);
        // When
        Complaint create1 = complaintService.createComplaint(complaintDTOCreate);
        // Then
        assertEquals("title", create1.getTitle());
        assertEquals("description", create1.getDescription());
        assertEquals("status", create1.getStatus());
        assertEquals(1L, create1.getCustomer().getCustomerId());
    }

    @Test
    public void shouldThrowCustomerNotFoundOnWrongId() {
        //Given
        complaintDTOCreate = new ComplaintDTOCreate("titledto", "descritpiondto", "status", IdType.EMPTY_ID.getId());
        //When & Then
        assertThrows(NotFoundException.class, () -> {
            complaintService.createComplaint(complaintDTOCreate);
        });
    }

    @Test
    public void shouldShowAllComplaints() {
        //Given
        Complaint complaint1 = new Complaint(1L, "title", "description", null, "status", 1);
        Complaint complaint2 = new Complaint(1L, "title", "description", null, "status", 1);
        ComplaintDTO complaintDto1 = new ComplaintDTO(1L, "title", "description", null, "status", 1);
        ComplaintDTO complaintdto2 = new ComplaintDTO(2L, "title2", "description2", null, "status", 1);
        List<Complaint> complaintsList = List.of(complaint1, complaint2);
        List<ComplaintDTO> complaintDtoList = List.of(complaintdto2, complaintDto1);
        when(complaintRepository.findAll()).thenReturn(complaintsList);
        when(complaintMapper.mapToListDTO(complaintsList)).thenReturn(complaintDtoList);
        //When
        List<ComplaintDTO> complaints = complaintService.showAllComplaints();

        //Then
        assertEquals(2, complaints.size());
        assertEquals(1L, complaints.get(1).getComplaintId());
        assertEquals(2L, complaints.get(0).getComplaintId());
    }

    @Test
    public void shouldShowComplaintsByID() {
        //Given
        Complaint complaint1 = new Complaint(1L, "title", "description", null, "status", 1);
        ComplaintDTO complaintDto1 = new ComplaintDTO(1L, "title", "description", null, "status", 1);
        when(complaintRepository.findById(complaint1.getComplaintId())).thenReturn(Optional.of(complaint1));
        when(complaintMapper.mapToComplaintDTO(complaint1)).thenReturn(complaintDto1);
        //When
        ComplaintDTO findingComplaint = complaintService.showComplaintById(complaint1.getComplaintId());
        //Thne
        assertEquals(complaintDto1.getTitle(), findingComplaint.getTitle());
    }

    @Test
    public void shouldModifyExistingComplaint() {
        // Given
        Complaint complaint1 = new Complaint(1L, "titlee", "descriptionn", null, "statuss", 1);
        ComplaintDTOUpdate complaintDTOUpdate = new ComplaintDTOUpdate("update", "update", null, "status");
        ComplaintDTO complaintDto1 = new ComplaintDTO(1L, "updatetitle", "updatedescription", null, "status", 1);
        when(complaintRepository.findById(complaint1.getComplaintId())).thenReturn(Optional.of(complaint1));
        when(complaintMapper.mapToComplaintDTO(complaint1)).thenReturn(complaintDto1);
        when(complaintRepository.save(complaint1)).thenReturn(complaint1);
        // When
        ComplaintDTO update = complaintService.putComplaint(complaint1.getComplaintId(), complaintDTOUpdate);
        // Then
        assertEquals("updatetitle", update.getTitle());
        assertEquals("updatedescription", update.getDescription());
        assertEquals(complaint1.getComplaintDate(), update.getComplaintDate());
        assertEquals("status", update.getStatus());
    }

    @Test
    public void shouldDeleteComplaintsByID() {
        // Given
        Complaint complaint1 = new Complaint(1L, "titlee", "descriptionn", null, "statuss", 1);
        when(complaintRepository.findById(complaint1.getComplaintId())).thenReturn(Optional.of(complaint1));
        doNothing().when(complaintRepository).deleteById(complaint1.getComplaintId());
        // When
        complaintService.deleteComplainByID(complaint1.getComplaintId());
        // Then
        verify(complaintRepository, times(1)).deleteById(complaint1.getComplaintId());
    }
}
