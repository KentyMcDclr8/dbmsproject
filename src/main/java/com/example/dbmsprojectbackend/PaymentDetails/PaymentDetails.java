package com.example.dbmsprojectbackend.PaymentDetails;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "PaymentDetails")
@Table(name = "payment_details")
public class PaymentDetails {

    //properties
    @Id
    @Column(
            name = "account_number",
            nullable = false
    )
    private Long accountNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @Column(
            name = "account_title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String accountTitle;

    @Column(
            name = "expiry_date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private LocalDate expiryDate;

    @Column(
            name = "cvv",
            nullable = false
    )
    private int cvv;

    @Column(
            name = "last_used",
            columnDefinition = "TEXT"
    )
    private LocalDate lastUsed;

    @Column(
            name = "bank_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String bankName;

    // constructors
    public PaymentDetails() {}

    public PaymentDetails(Long accountNumber, Customer customer, String accountTitle, LocalDate expiryDate, int cvv, String bankName) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountTitle = accountTitle;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.lastUsed = null;
        this.bankName = bankName;
    }

    // getters and setters
    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDate lastUsed) {
        this.lastUsed = lastUsed;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}