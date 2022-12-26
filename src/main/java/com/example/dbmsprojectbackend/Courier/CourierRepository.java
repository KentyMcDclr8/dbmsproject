package com.example.dbmsprojectbackend.Courier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

	@Query(value = "SELECT * FROM courier c WHERE c.email = ?1", nativeQuery = true)
	Optional<Courier> findCourierByEmail(String email);
	@Query(value = "SELECT * FROM courier c WHERE c.phone = ?1", nativeQuery = true)
	Optional<Courier> findCourierByPhone(Long phone);

	@Query(value = "SELECT * FROM courier c WHERE c.id = ?1", nativeQuery = true)
	Optional<Courier> findCourierById(Long courierId);

	@Query(value = "SELECT * FROM courier", nativeQuery = true)
	List<Courier> findAll();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM courier c WHERE c.id = ?1", nativeQuery = true)
	void deleteById(Long courierId);

}
