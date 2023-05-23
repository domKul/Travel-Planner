package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;
    @Column(name = "customer_Id")
    private long customerId;

    private String title;

    private String description;

    private LocalDateTime complaintDate;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    public Complaint() {
    }

    public Complaint(Long complaintId, String title, String description, LocalDateTime complaintDate, String status, Customer customer) {
        this.complaintId = complaintId;
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.customer = customer;
    }

    public Complaint(long complaintId, String title, String description, LocalDateTime complaintDate, String status, long customerId) {
        this.complaintId = complaintId;
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.customerId = customerId;
    }


    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDateTime complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint that = (Complaint) o;
        return Objects.equals(complaintId, that.complaintId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(complaintDate, that.complaintDate) && Objects.equals(status, that.status) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(complaintId, title, description, complaintDate, status, customer);
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + complaintId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", complaintDate=" + complaintDate +
                ", status='" + status + '\'' +
                ", customer=" + customer.getCustomerId() +
                '}';
    }
}
