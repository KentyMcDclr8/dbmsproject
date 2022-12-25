package com.example.dbmsprojectbackend.Login;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Customer.CustomerRepository;
import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.Employee.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LoginService {
	@PersistenceContext
	private EntityManager entityManager;
	private final CustomerRepository customerRepository;
	private final EmployeeRepository employeeRepository;

	@Autowired

	public LoginService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
	}

	public Customer customerLogin(long customerId, String password){

		Query query = entityManager.createNativeQuery("SELECT * FROM customer WHERE id = ? AND password = ?").setParameter(1, customerId).setParameter(2, password);
		List customerList = query.getResultList();
		if(customerList.isEmpty()){
			throw new IllegalStateException("ID/Password Incorrect");
		}
		else {
		Customer customer =	customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that id does not exist."));
			return customer;
		}
	}
	public Employee employeeLogin(long employeeId, String password){

		Query query = entityManager.createNativeQuery("SELECT * FROM employee WHERE id = ? AND password = ?").setParameter(1, employeeId).setParameter(2, password);
		List customerList = query.getResultList();
		if(customerList.isEmpty()){
			throw new IllegalStateException("ID/Password Incorrect");
		}
		else {
			Employee employee =	employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("An employee with that id does not exist."));
			return employee;
		}
	}
}