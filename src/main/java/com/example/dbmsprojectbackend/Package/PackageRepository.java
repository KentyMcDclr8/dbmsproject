package com.example.dbmsprojectbackend.Package;

import com.example.dbmsprojectbackend.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {

    @Query(value = "SELECT * FROM package p WHERE p.id = ?1", nativeQuery = true)
    Optional<Package> findPackageById(Long packageId);

    @Query(value = "SELECT * FROM package p WHERE p.sent_by = ?1", nativeQuery = true)
    List<Package> findPackageBySenderId(Long senderId);

    @Query(value = "SELECT * FROM package p", nativeQuery = true)
    List<Package> findAll();

    @Query(value = "SELECT * FROM package p WHERE p.sent_by = ?1 AND p.delivery_status <> ?2 AND p.delivery_status <> ?3", nativeQuery = true)
    List<Package> getActivePackages(Long senderId, String status1, String status2);

    @Query(value = "SELECT * FROM package p WHERE p.sent_by = ?1 AND (p.delivery_status = ?2 OR p.delivery_status = ?3)", nativeQuery = true)
    List<Package> getInactivePackages(Long senderId, String status1, String status2);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM package p WHERE p.id = ?1", nativeQuery = true)
    void deleteById(Long packageId);
}
