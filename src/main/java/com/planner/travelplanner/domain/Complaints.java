package com.planner.travelplanner.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "complaints")
public class Complaints {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime complaintDate;
    @NotNull
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Complaints() {
    }

    public Complaints(Long id, String description, LocalDateTime complaintDate, String status) {
        this.id = id;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaints that = (Complaints) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(complaintDate, that.complaintDate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, complaintDate, status);
    }

    @Override
    public String toString() {
        return "Complaints{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", complaintDate=" + complaintDate +
                ", status='" + status + '\'' +
                '}';
    }
}
