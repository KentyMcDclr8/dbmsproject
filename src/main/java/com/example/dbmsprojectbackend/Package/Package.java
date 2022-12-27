package com.example.dbmsprojectbackend.Package;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.PaymentDetails.PaymentDetails;
import com.example.dbmsprojectbackend.Recipient.Recipient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity(name = "Package")
@Table(name = "package")
public class Package {
    public static Long packageId = 4000L;
    //properties
    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "volume",
            nullable = false
    )
    private double volume;

    @Column(
            name = "weight",
            nullable = false
    )
    private double weight;

    @Column(
            name = "type",
            nullable = false,
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String type;

    @Column(
            name = "delivery_status",
            columnDefinition = "TEXT"
    )
    private String deliveryStatus;

    @Column(
            name = "date_delivered",
            columnDefinition = "TEXT"
    )
    private LocalDate dateDelivered;

    @Column(
            name = "price"
    )
    private Integer price;

    @Column(
            name = "deliveryTime"
    )
    private Integer deliveryTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sent_by", referencedColumnName = "id")
    @JsonIgnore
    private Customer sentBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managed_by", referencedColumnName = "id")
    @JsonIgnore
    private Employee managedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paid_from", referencedColumnName = "account_number")
    @JsonIgnore
    private PaymentDetails paidFrom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "received_by", referencedColumnName = "recipient_id")
    @JsonIgnore
    private Recipient receivedBy;

    // constructors
    public Package() {}

    public Package(double volume, double weight, String type, Customer sentBy) {
        this.volume = volume;
        this.weight = weight;
        this.type = type;
        this.sentBy = sentBy;
        this.deliveryStatus = "to be assigned";
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        if (volume > 0) {
            this.volume = volume;
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0){
            this.weight = weight;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDate getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(LocalDate dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice() {
        this.price =Integer.valueOf ((int) ((this.weight/2) + this.volume));
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Customer getSentBy() {
        return sentBy;
    }

    public void setSentBy(Customer sentBy) {
        this.sentBy = sentBy;
    }

    public Employee getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Employee managedBy) {
        this.managedBy = managedBy;
    }

    public PaymentDetails getPaidFrom() {
        return paidFrom;
    }

    public void setPaidFrom(PaymentDetails paidFrom) {
        this.paidFrom = paidFrom;
    }

    public Recipient getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(Recipient receivedBy) {
        this.receivedBy = receivedBy;
    }
}
