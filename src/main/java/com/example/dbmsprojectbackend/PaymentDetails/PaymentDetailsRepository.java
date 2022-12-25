package com.example.dbmsprojectbackend.PaymentDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

    @Query(value = "SELECT * FROM payment_details p WHERE p.account_number = ?1 AND p.customer_id = ?2", nativeQuery = true)
    Optional<PaymentDetails> findPaymentDetailsByAccountAndId(Long accountNumber, Long customerId);

    @Query(value = "SELECT * FROM payment_details p WHERE p.customer_id = ?1", nativeQuery = true)
    List<PaymentDetails> findPaymentDetailsByCustomerId(Long customerId);

    @Query(value = "SELECT * FROM payment_details p", nativeQuery = true)
    List<PaymentDetails> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM payment_details p WHERE p.account_number = ?1 AND p.customer_id = ?2", nativeQuery = true)
    void deleteByAccountandId(Long accountNumber, Long customerId);
}
