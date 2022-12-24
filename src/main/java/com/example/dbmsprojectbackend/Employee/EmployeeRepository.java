package com.example.dbmsprojectbackend.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE e.email = ?1", nativeQuery = true)
    Optional<Employee> findEmployeeByEmail(String email);

    @Query(value = "SELECT * FROM employee e WHERE e.phone = ?1", nativeQuery = true)
    Optional<Employee> findEmployeeByPhone(Long phone);

    @Query(value = "SELECT * FROM employee e WHERE e.id = ?1", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long employeeId);

    @Query(value = "SELECT * FROM employee e", nativeQuery = true)
    List<Employee> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employee e WHERE e.id = ?1", nativeQuery = true)
    void deleteById(Long employeeId);


}
