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

    @GetMapping(path = "{customer_id}")
    public List<Complaint> getComplaints1(@PathVariable Long customer_id) {
        return complaintService.getComplaintsByUser(customer_id);
    }

    @PostMapping(path = "{package_id}")
    public Complaint addNewComplaint(@PathVariable("package_id") Long package_id, @RequestBody Complaint complaint) { complaintService.addNewComplaint(package_id, complaint);
    return complaint;
    }

    @DeleteMapping(path = "{complaintId}")
    public Complaint deleteComplaint(@PathVariable("complaintId") Long complaintId) {
        complaintService.deleteComplaint(complaintId);
        Complaint complaint;
        return  null;
    }

    @PutMapping(path = "{complaintId}")
    public Complaint updateComplaint(
            @PathVariable("complaintId") Long complaintId,
            @RequestBody Complaint complaint) {
        complaintService.setComplaintFeedback(complaintId, complaint.getFeedback(), complaint.getStatus());
        return complaint;
    }
}
