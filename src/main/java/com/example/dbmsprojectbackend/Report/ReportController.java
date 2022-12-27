package com.example.dbmsprojectbackend.Report;

import com.example.dbmsprojectbackend.Package.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("report")
@CrossOrigin
public class ReportController {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportController(ReportService reportService, ReportRepository reportRepository) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }


    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping(path = "{reportId}")
    public tableData getReportData(@PathVariable("reportId") Long reportId) { return reportService.getReportData(reportId);}


    @PostMapping
    public void addNewReport(@RequestBody Report report) { reportService.addNewReport(report); }

    @DeleteMapping(path = "{reportId}")
    public void deleteReport(@PathVariable("reportId") Long reportId) {
        reportService.deleteReport(reportId);
    }

    @PutMapping(path = "description/{reportId}")
    public void updateDescription(
            @PathVariable("reportId") Long reportId,
            @RequestParam(required = true) String description) {
        reportService.setReportDescription(reportId, description);
    }

    @PutMapping(path = "update/{reportId}")
    public void updateReport(
            @PathVariable("reportId") Long reportId,
            @RequestParam(required = true) String name) {
        reportService.updateReport(reportId);
    }

    @PutMapping(path = "{reportId}")
    public void updateName(
            @PathVariable("reportId") Long reportId,
            @RequestParam(required = true) String name) {
        reportService.setReportName(reportId, name);
    }
}
