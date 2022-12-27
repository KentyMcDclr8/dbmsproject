package com.example.dbmsprojectbackend.Report;

import com.example.dbmsprojectbackend.Login.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity(name = "Report")
@Table(name = "report")
public class Report {
    public static Long reportId = 6000L;

    // properties
    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "reportName",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String reportName;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "query",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String query;

    @Column(
            name = "reportData",
            columnDefinition = "TEXT"
    )
    private String reportData;

    @Column(
            name = "createdDate",
            columnDefinition = "DATE"
    )
    private String createdDate;


    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] column) {
        this.columns = columns;
    }

    @Column(
            name = "columns",
            columnDefinition = "TEXT"
    )
    private String[] columns;

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    @Column(
            name = "data",
            columnDefinition = "TEXT"
    )
    private List<Map<String, String>> data;

    @JsonIgnore
   /* @ManyToOne(cascade = CascadeType.ALL)
    *//*@JoinColumn(name ="made_by", referencedColumnName ="id")
    private Admin made_by;*/

    // constructor
    public Report() {}

    public Report(String reportName, String description, String reportData, String createdDate) {
        this.reportName = reportName;
        this.description = description;
        this.reportData = reportData;
        this.createdDate = createdDate;
    }


    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

   /* public Admin getMade_by() {
        return made_by;
    }

    public void setMade_by(Admin made_by) {
        this.made_by = made_by;
    }*/

    public String getCreatedDate(){ return createdDate;}
    public void setCreatedDate(String createdDate){ this.createdDate = createdDate;}
}

