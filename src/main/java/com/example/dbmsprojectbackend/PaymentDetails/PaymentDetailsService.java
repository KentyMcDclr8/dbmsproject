package com.example.dbmsprojectbackend.PaymentDetails;

import com.example.dbmsprojectbackend.Customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsService(PaymentDetailsRepository paymentDetailsRepository) { this.paymentDetailsRepository = paymentDetailsRepository; }

    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetailsRepository.findAll();
    }

    public List<PaymentDetails> getPaymentDetailsByCustomerId(Long customerId) {
        return paymentDetailsRepository.findPaymentDetailsByCustomerId(customerId);
    }

    @Transactional
    public void addNewPaymentDetails(PaymentDetails paymentDetails, Customer customer) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findPaymentDetailsByAccountAndId(paymentDetails.getAccountNumber(), customer.getId());
        if (paymentDetailsOptional.isPresent()) {
            throw new IllegalStateException("Payment details with that account number already exist.");
        }
        entityManager.createNativeQuery("INSERT INTO payment_details (account_number, customer_id, account_title, expiry_date, cvv, last_used,  bank_name) VALUES (?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, paymentDetails.getAccountNumber())
                .setParameter(2, customer.getId())
                .setParameter(3, paymentDetails.getAccountTitle())
                .setParameter(4, paymentDetails.getExpiryDate())
                .setParameter(5, paymentDetails.getCvv())
                .setParameter(6, null)
                .setParameter(7, paymentDetails.getBankName())
                .executeUpdate();
    }

    public void deletePaymentDetails(Long accountNumber, Long customerId) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findPaymentDetailsByAccountAndId(accountNumber, customerId);
        if (!paymentDetailsOptional.isPresent()) {
            throw new IllegalStateException("Payment details with that account number do not exist.");
        }
        paymentDetailsRepository.deleteByAccountandId(accountNumber, customerId);
    }

    @Transactional
    public void updatePaymentDetails(Long accountNumber, Long customerId, String accountTitle, LocalDate expiryDate, Integer cvv, LocalDate lastUsed, String bankName) {
        Optional<PaymentDetails> paymentDetailsOptional = paymentDetailsRepository.findPaymentDetailsByAccountAndId(accountNumber, customerId);
        if (!paymentDetailsOptional.isPresent()) {
            throw new IllegalStateException("Payment details with that account number do not exist.");
        }
        PaymentDetails paymentDetails = paymentDetailsOptional.get();
        if (accountTitle != null && accountTitle.length() != 0 && !Objects.equals(accountTitle, paymentDetails.getAccountTitle())) {
            paymentDetails.setAccountTitle(accountTitle);
        }
        if (expiryDate != null && !Objects.equals(expiryDate, paymentDetails.getExpiryDate())) {
            paymentDetails.setExpiryDate(expiryDate);
        }
        if (cvv != null && cvv > 0 && cvv.intValue() == paymentDetails.getCvv()) {
            paymentDetails.setCvv(cvv.intValue());
        }
        if (lastUsed != null && !Objects.equals(lastUsed, paymentDetails.getLastUsed())) {
            paymentDetails.setLastUsed(lastUsed);
        }
        if (bankName != null && bankName.length() != 0 && !Objects.equals(bankName, paymentDetails.getBankName())) {
            paymentDetails.setBankName(bankName);
        }
    }

}
