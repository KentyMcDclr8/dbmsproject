package com.example.dbmsprojectbackend.Complaint;

import com.example.dbmsprojectbackend.Package.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    @Query(value = "SELECT * FROM complaint c WHERE c.id = ?1", nativeQuery = true)
    Optional<Complaint> findComplaintById(Long complaintId);

    @Query(value = "SELECT * FROM complaint c", nativeQuery = true)
    List<Complaint> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM complaint c WHERE c.id = ?1", nativeQuery = true)
    void deleteById(Long complaintId);
}
