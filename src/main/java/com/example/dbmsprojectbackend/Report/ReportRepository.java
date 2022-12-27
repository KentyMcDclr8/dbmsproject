package com.example.dbmsprojectbackend.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = "SELECT * FROM report c WHERE c.id = ?1", nativeQuery = true)
    Optional<Report> findReportById(Long reportId);

    @Query(value = "SELECT * FROM report c", nativeQuery = true)
    List<Report> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM report c WHERE c.id = ?1", nativeQuery = true)
    void deleteById(Long reportId);


}
