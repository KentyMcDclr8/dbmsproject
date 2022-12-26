package com.example.dbmsprojectbackend.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptionalEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        Optional<Employee> employeeOptionalPhone = employeeRepository.findEmployeeByPhone(employee.getPhone());
        if (employeeOptionalEmail.isPresent()) {
            throw new IllegalStateException("An employee with that email address already exists.");
        }
        if (employeeOptionalPhone.isPresent()) {
            throw new IllegalStateException("An employee with that phone number already exists.");
        }
        Optional<Employee> employeeOptionalId = employeeRepository.findEmployeeById(employee.employeeId);
        while(employeeOptionalId.isPresent()){
            employee.employeeId++;
            employeeOptionalId = employeeRepository.findEmployeeById(employee.employeeId);
        }

        entityManager.createNativeQuery("INSERT INTO employee (id, password, name, email, phone, salary, start_date, end_date, status, position, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, employee.employeeId)
                .setParameter(2, employee.getPassword())
                .setParameter(3, employee.getName())
                .setParameter(4, employee.getEmail())
                .setParameter(5, employee.getPhone())
                .setParameter(6, employee.getSalary())
                .setParameter(7, employee.getStartDate())
                .setParameter(8, employee.getEndDate())
                .setParameter(9, employee.getStatus())
                .setParameter(10, employee.getPosition())
                .setParameter(11, "Employee")
                .executeUpdate();
        employee.employeeId++;
    }

    @Transactional
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new IllegalStateException("An employee with that ID does not exist.");
        }
        employeeRepository.deleteById(employeeId);

    }

    @Transactional
    public void updateEmployee(Long employeeId, String password, String name, String email, Long phone, Integer salary, LocalDate startDate, LocalDate endDate, String status, String position) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new IllegalStateException("An employee with that ID does not exist.");
        }
        Employee employee = employeeOptional.get();
        if (password != null && password.length() != 0 && !Objects.equals(password, employee.getPassword())) {
            employee.setPassword(password);
        }
        if (name != null && name.length() != 0 && !Objects.equals(name, employee.getName())) {
            employee.setName(name);
        }
        if (email != null && email.length() != 0 && !Objects.equals(email, employee.getEmail())) {
            Optional<Employee> employeeOptionalEmail = employeeRepository.findEmployeeByEmail(email);
            if (employeeOptionalEmail.isPresent()) {
                throw new IllegalStateException("An employee with that email address already exists");
            }
            employee.setEmail(email);
        }
        if (phone != null && phone != 0 && !Objects.equals(phone, employee.getPhone())) {
            Optional<Employee> employeeOptionalPhone = employeeRepository.findEmployeeByPhone(phone);
            if (employeeOptionalPhone.isPresent()) {
                throw new IllegalStateException("An employee with that phone number already exists");
            }
            employee.setPhone(phone);
        }
        if (salary!=null && salary > 0 && !Objects.equals(salary, employee.getSalary())) {
            employee.setSalary(salary.intValue());
        }
        if (startDate != null && !Objects.equals(startDate, employee.getStartDate())) {
            employee.setStartDate(startDate);
        }
        if (endDate != null && !Objects.equals(endDate, employee.getEndDate())) {
            employee.setEndDate(endDate);
        }
        if (status != null && status.length() != 0 && !Objects.equals(status, employee.getStatus())) {
            employee.setStatus(status);
        }
        if (position != null && position.length() != 0 && !Objects.equals(position, employee.getPosition())) {
            employee.setPosition(position);
        }
    }
}
