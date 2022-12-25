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

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public List<Complaint> getComplaints() {
        return complaintService.getComplaints();
    }

    @PostMapping
    public void addNewComplaint(@RequestBody Complaint complaint) { complaintService.addNewComplaint(complaint); }

    @DeleteMapping(path = "{complaintId}")
    public void deleteComplaint(@PathVariable("complaintId") Long complaintId) {
        complaintService.deleteComplaint(complaintId);
    }

    @PutMapping(path = "{complaintId}")
    public void updateComplaint(
            @PathVariable("complaintId") Long complaintId,
            @RequestParam(required = true) String feedback,
            @RequestParam(required = true) String status) {
        complaintService.setComplaintFeedback(complaintId, feedback, status);
    }
}
