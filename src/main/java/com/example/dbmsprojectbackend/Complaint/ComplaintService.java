package com.example.dbmsprojectbackend.Complaint;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.Recipient.Recipient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) { this.complaintRepository = complaintRepository; }

    public List<Complaint> getComplaints() {

        Query sql =  entityManager.createNativeQuery("SELECT c.id FROM complaint c");
        List<Long> r = sql.getResultList();
        List<Complaint> temp = new ArrayList<>() ;
        for(Long c : r){
            temp.add(complaintRepository.findById(c).orElseThrow(() -> new IllegalStateException("A complaint with that ID does not exist.")));

        }
        if(temp.isEmpty()){
            throw new IllegalStateException("No complaint");
        }
        return temp;
        // return complaintRepository.findAll();
    }
    public List<Complaint> getComplaintsByUser(Long customerId) {
        Query sql =  entityManager.createNativeQuery("SELECT c.id FROM complaint c, package p WHERE c.package_id = p.id and p.sent_by = ?1")
                .setParameter(1, customerId);
        List<Long> r = sql.getResultList();
        List<Complaint> temp = new ArrayList<>() ;
        for(Long c : r){
           temp.add(complaintRepository.findById(c).orElseThrow(() -> new IllegalStateException("A complaint with that ID does not exist.")));

        }
        if(temp.isEmpty()){
            throw new IllegalStateException("No complaint");
        }
        return temp;
       //return complaintRepository.findComplaintByCustomer(customerId);
    }

    @Transactional
    public void addNewComplaint(Long package_id, Complaint complaint) {
//        Optional<Complaint> complaintOptional = complaintRepository.findComplaintById(complaint.getId());
//        if (complaintOptional.isPresent()) {
//            throw new IllegalStateException("A complaint with that ID already exists.");
//        }
        Optional<Complaint> complaintOptionalId = complaintRepository.findComplaintById(complaint.complaintId);
        while(complaintOptionalId.isPresent()){
            complaint.complaintId++;
            complaintOptionalId = complaintRepository.findComplaintById(complaint.complaintId);
        }
        entityManager.createNativeQuery("INSERT INTO complaint (id, details, type, status, package_id) VALUES (?, ?, ?, ?, ?)")
                .setParameter(1, complaint.complaintId)
                .setParameter(2, complaint.getDetails())
                .setParameter(3, complaint.getType())
                .setParameter(4, "Processing")
                .setParameter(5, package_id)
                .executeUpdate();
        complaint.complaintId++;
    }

    @Transactional
    public void deleteComplaint(Long complaintId) {
        Optional<Complaint> complaintOptional = complaintRepository.findComplaintById(complaintId);
        if (!complaintOptional.isPresent()) {
            throw new IllegalStateException("A complaint with that ID does not exist.");
        }
        complaintRepository.deleteById(complaintId);
    }

    @Transactional
    public void setComplaintFeedback(Long
                                                 complaintId, String feedback, String status) {
        Optional<Complaint> complaintOptional = complaintRepository.findComplaintById(complaintId);
        if (!complaintOptional.isPresent()) {
            throw new IllegalStateException("A complaint with that ID does not exist.");
        }
        Complaint complaint = complaintOptional.get();
        complaint.setFeedback(feedback);
        complaint.setStatus(status);
    }
}
