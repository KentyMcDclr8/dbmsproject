package com.example.dbmsprojectbackend.Recipient;

import com.example.dbmsprojectbackend.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

	@Query(value = "SELECT * FROM recipient r WHERE r.email = ?1", nativeQuery = true)
	Optional<Recipient> findRecipientByEmail(String email);
	@Query(value = "SELECT * FROM recipient r WHERE r.phone = ?1", nativeQuery = true)
	Optional<Recipient> findRecipientByPhone(Long phone);

	@Query(value = "SELECT * FROM recipient r WHERE r.recipient_id = ?1", nativeQuery = true)
	Optional<Recipient> findRecipientById(Long recipient_id);

	@Query(value = "SELECT * FROM recipient r", nativeQuery = true)
	List<Recipient> findAll();

	@Query(value = "SELECT * FROM recipient r WHERE r.customer_id = ?1", nativeQuery = true)
	List<Recipient> findRecipientForACustomer(Long customerId);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM recipient r WHERE r.recipient_id = ?1", nativeQuery = true)
	void deleteById(Long recipientId);

}
