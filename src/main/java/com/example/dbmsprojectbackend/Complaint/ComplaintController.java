package com.example.dbmsprojectbackend.Complaint;

import com.example.dbmsprojectbackend.Package.Package;
import com.example.dbmsprojectbackend.Package.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("complaint")
@CrossOrigin
public class ComplaintController {
    private final ComplaintService complaintService;
    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintController(ComplaintService complaintService,
                               ComplaintRepository complaintRepository) {
        this.complaintService = complaintService;
        this.complaintRepository = complaintRepository;
    }

    @GetMapping
    public List<Complaint> getComplaints() {
        return complaintService.getComplaints();
    }

    @PostMapping
    public Complaint addNewComplaint(@RequestBody Complaint complaint) { complaintService.addNewComplaint(complaint);
    return complaint;
    }

    @DeleteMapping(path = "{complaintId}")
    public Complaint deleteComplaint(@PathVariable("complaintId") Long complaintId) {
        complaintService.deleteComplaint(complaintId);
        Complaint complaint;
        return  complaint = complaintRepository.findComplaintById(complaintId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));
    }

    @PutMapping(path = "{complaintId}")
    public Complaint updateComplaint(
            @PathVariable("complaintId") Long complaintId,
            @RequestParam(required = true) String feedback,
            @RequestParam(required = true) String status) {
        complaintService.setComplaintFeedback(complaintId, feedback, status);
        Complaint complaint;
        return  complaint = complaintRepository.findComplaintById(complaintId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));

    }
}
