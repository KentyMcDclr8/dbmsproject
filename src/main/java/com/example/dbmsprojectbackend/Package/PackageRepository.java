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

    @Query(value = "SELECT * FROM package p", nativeQuery = true)
    List<Package> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM package p WHERE p.id = ?1", nativeQuery = true)
    void deleteById(Long packageId);
}
