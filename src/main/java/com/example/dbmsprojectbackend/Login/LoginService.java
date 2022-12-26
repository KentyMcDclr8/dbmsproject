package com.example.dbmsprojectbackend.Login;

import com.example.dbmsprojectbackend.Courier.Courier;
import com.example.dbmsprojectbackend.Courier.CourierRepository;
import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Customer.CustomerRepository;
import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.Employee.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LoginService {
	@PersistenceContext
	private EntityManager entityManager;
	private final CustomerRepository customerRepository;
	private final EmployeeRepository employeeRepository;
	private final CourierRepository courierRepository;


	@Autowired

	public LoginService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, CourierRepository courierRepository) {
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
		this.courierRepository = courierRepository;
	}

	public Object customerLogin(long id, String password) {

		Query getCustomer = entityManager.createNativeQuery("SELECT * FROM customer WHERE id = ? AND password = ?").setParameter(1, id).setParameter(2, password);
		Query getEmployee = entityManager.createNativeQuery("SELECT * FROM employee WHERE id = ? AND password = ?").setParameter(1, id).setParameter(2, password);
		Query getCourier = entityManager.createNativeQuery("SELECT * FROM courier WHERE id = ? AND password = ?").setParameter(1, id).setParameter(2, password);

		List customerList = getCustomer.getResultList();
		List empoyeeList = getEmployee.getResultList();
		List courierList = getCourier.getResultList();


		if (customerList.isEmpty() && empoyeeList.isEmpty() &&courierList.isEmpty() ) {
			throw new IllegalStateException("ID/Password Incorrect");
		} else {
			if (!empoyeeList.isEmpty()) {
				Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException("An employee with that id does not exist."));
				return employee;
			} else if(!customerList.isEmpty()){
				Customer customer = customerRepository.findCustomerById(id).orElseThrow(() -> new IllegalStateException("A customer with that id does not exist."));
				return customer;
			}
			else {
				Courier courier = courierRepository.findCourierById(id).orElseThrow(() -> new IllegalStateException("A courier with that id does not exist."));
				if(!courier.isApproved()){
					throw new IllegalStateException("Courier is not approved");
				}
				return courier;
			}
		}
	}
}