package com.example.dbmsprojectbackend.Complaint;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.Package.Package;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

@Entity(name = "Complaint")
@Table(name = "complaint")
public class Complaint {
    public static Long complaintId = 5000L;

    // properties
    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "details",
            nullable = false,
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String details;

    @Column(
            name = "type",
            nullable = false,
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String type;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String status;

    @Column(
            name = "feedback",
            columnDefinition = "TEXT"
    )
    private String feedback;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name ="handled_by", referencedColumnName ="id")
    private Employee handledBy;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private Package pack;

    // constructor
    public Complaint() {}

    public Complaint(String details, String type) {
        this.details = details;
        this.type = type;
        this.status = "Processing";
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Employee getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(Employee handledBy) {
        this.handledBy = handledBy;
    }
    public Package getpackage() {
        return pack;
    }
    public void setPackage(Package pack) {
        this.pack = pack;
    }
}

