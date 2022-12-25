package com.example.dbmsprojectbackend.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT * FROM customer c WHERE c.email = ?1", nativeQuery = true)
	Optional<Customer> findCustomerByEmail(String email);
	@Query(value = "SELECT * FROM customer c WHERE c.phone = ?1", nativeQuery = true)
	Optional<Customer> findCustomerByPhone(Long phone);

	@Query(value = "SELECT * FROM customer c WHERE c.id = ?1", nativeQuery = true)
	Optional<Customer> findCustomerById(Long customerId);

	@Query(value = "SELECT * FROM customer c", nativeQuery = true)
	List<Customer> findAll();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM customer c WHERE c.id = ?1", nativeQuery = true)
	void deleteById(Long customerId);

}
