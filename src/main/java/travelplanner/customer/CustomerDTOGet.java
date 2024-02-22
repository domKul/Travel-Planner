package travelplanner.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import travelplanner.complaint.query.SimpleComplaintQueryDto;

import java.util.Date;
import java.util.List;
@Getter
@AllArgsConstructor
class CustomerDTOGet {

    private Long customerId;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String country;
    private String city;
    private String streetName;
    private String postalCode;
    private String email;
    private int phoneNumber;
    private List<SimpleComplaintQueryDto> complaints;
}
