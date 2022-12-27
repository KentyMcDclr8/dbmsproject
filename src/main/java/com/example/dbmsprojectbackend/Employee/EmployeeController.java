package com.example.dbmsprojectbackend.Employee;

import com.example.dbmsprojectbackend.Complaint.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.lang.*;


@RestController
@RequestMapping("employee")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
        return employee;
    }

    @DeleteMapping(path = "{employeeId}")
    public Employee deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        Employee employee;
        return  employee = employeeRepository.findEmployeeById(employeeId).orElseThrow(() -> new IllegalStateException("A employee with that ID does not exist."));

    }

    @PutMapping(path = "{employeeId}")
    public Employee updateEmployee(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody Employee employee) {
        employeeService.updateEmployee(employeeId, employee.getPassword(), employee.getName(), employee.getEmail(), employee.getPhone(), employee.getSalary(), employee.getStartDate(), employee.getEndDate(), employee.getStatus(), employee.getPosition());
       return employee;
    }
}
