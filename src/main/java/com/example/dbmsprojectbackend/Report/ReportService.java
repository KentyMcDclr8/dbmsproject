package com.example.dbmsprojectbackend.Report;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

class tableData{
    public String[] columns;
    public List <Map<String, String>> data;

    public tableData(String rawData){
        data = new ArrayList<Map<String, String>>();

        String[] separate = rawData.split("///");
        columns = separate[0].split(",");

        String dataAr[] = separate[1].split("---");

        for (int i = 0; i < dataAr.length; i++){
            String[] pairs = dataAr[i].split(",");
            Map<String, String> myMap = new HashMap<String, String>();
            for (int k=0;k<pairs.length;k++) {
                String pair = pairs[k];
                String[] keyValue = pair.split("=");
                myMap.put(keyValue[0], keyValue[1]);
            }
            data.add(myMap);
        }
    }
}

@Service
public class ReportService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) { this.reportRepository = reportRepository; }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    public tableData getReportData(Long id) {
        Optional<Report> optionalReport = reportRepository.findReportById(id);
        String data = optionalReport.get().getReportData();
        return new tableData(data);
    }

    @Transactional
    public void addNewReport(Report report) {
//        Optional<Report> reportOptional = reportRepository.findReportById(report.getId());
//        if (reportOptional.isPresent()) {
//            throw new IllegalStateException("A report with that ID already exists.");
//        }
        Optional<Report> reportOptionalId = reportRepository.findReportById(report.reportId);
        while(reportOptionalId.isPresent()){
            report.reportId++;
            reportOptionalId = reportRepository.findReportById(report.reportId);
        }


        Query query = entityManager.createNativeQuery(report.getQuery());
        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setTupleTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,Object>> attResults = nativeQuery.getResultList();
        int length = attResults.get(0).keySet().toString().length();
        String endData = attResults.get(0).keySet().toString().substring(1,length) + "///";

        for(int i=0;i<attResults.size();i++){
           length = attResults.get(i).toString().length();
           endData = endData + attResults.get(i).toString().substring(1,length) + "---";
        }

        report.setReportData(endData);

        entityManager.createNativeQuery("INSERT INTO report (id, report_name, description, query, report_data, created_date) VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, report.reportId)
                .setParameter(2, report.getReportName())
                .setParameter(3, report.getDescription())
                .setParameter(4, report.getQuery())
                .setParameter(5, report.getReportData())
                .setParameter(6, report.getCreatedDate())
                .executeUpdate();
        report.reportId++;
    }

    @Transactional
    public void deleteReport(Long reportId) {
        Optional<Report> reportOptional = reportRepository.findReportById(reportId);
        if (!reportOptional.isPresent()) {
            throw new IllegalStateException("A report with that ID does not exist.");
        }
        reportRepository.deleteById(reportId);
    }

    @Transactional
    public void setReportDescription(Long
                                             reportId, String description) {
        Optional<Report> reportOptional = reportRepository.findReportById(reportId);
        if (!reportOptional.isPresent()) {
            throw new IllegalStateException("A report with that ID does not exist.");
        }
        Report report = reportOptional.get();
        report.setDescription(description);
    }

    @Transactional
    public void setReportName(Long
                                      reportId, String name) {
        Optional<Report> reportOptional = reportRepository.findReportById(reportId);
        if (!reportOptional.isPresent()) {
            throw new IllegalStateException("A report with that ID does not exist.");
        }
        Report report = reportOptional.get();
        report.setReportName(name);
    }

    @Transactional
    public void updateReport(Long reportId) {
        Optional<Report> reportOptional = reportRepository.findReportById(reportId);
        if (!reportOptional.isPresent()) {
            throw new IllegalStateException("A report with that ID does not exist.");
        }
        Report report = reportOptional.get();
        Query query = entityManager.createNativeQuery(report.getQuery());
        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setTupleTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,Object>> attResults = nativeQuery.getResultList();
        int length = attResults.get(0).keySet().toString().length();
        String endData = attResults.get(0).keySet().toString().substring(1,length) + "///";

        for(int i=0;i<attResults.size();i++){
            length = attResults.get(i).toString().length();
            endData = endData + attResults.get(i).toString().substring(1,length) + "---";
        }

        report.setReportData(endData);

        entityManager.createNativeQuery("INSERT INTO report (id, report_name, description, query, report_data, created_date) VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, report.reportId)
                .setParameter(2, report.getReportName())
                .setParameter(3, report.getDescription())
                .setParameter(4, report.getQuery())
                .setParameter(5, report.getReportData())
                .setParameter(6, report.getCreatedDate())
                .executeUpdate();
    }
}

