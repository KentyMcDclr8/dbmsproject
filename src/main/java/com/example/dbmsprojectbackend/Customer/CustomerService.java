package com.example.dbmsprojectbackend.Customer;

import com.example.dbmsprojectbackend.Employee.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class CustomerService {
	@PersistenceContext
	private EntityManager entityManager;
	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	@Transactional
//new
	public Long addNewCustomer(Customer customer) {
		Optional<Customer> customerOptionalEmail = customerRepository.findCustomerByEmail(customer.getEmail());
		Optional<Customer> customerOptionalPhone = customerRepository.findCustomerByPhone(customer.getPhone());
		if (customerOptionalEmail.isPresent()) {
			throw new IllegalStateException("An employee with that email address already exists.");
		}
		if (customerOptionalPhone.isPresent()) {
			throw new IllegalStateException("An employee with that phone number already exists.");
		}
		Optional<Customer> customerOptionalId = customerRepository.findCustomerById(customer.customerId);
		while(customerOptionalId.isPresent()){
			customer.customerId++;
			customerOptionalId = customerRepository.findCustomerById(customer.customerId);
		}
		entityManager.createNativeQuery("INSERT INTO customer (id, password, name, email, phone, building_number,  street_number,  city,  province, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
				.setParameter(1, customer.customerId)
				.setParameter(2, customer.getPassword())
				.setParameter(3, customer.getName())
				.setParameter(4, customer.getEmail())
				.setParameter(5, customer.getPhone())
				.setParameter(6, customer.getBuildingNumber())
				.setParameter(7, customer.getStreetNumber())
				.setParameter(8, customer.getCity())
				.setParameter(9, customer.getProvince())
				.setParameter(10, "Customer")
				.executeUpdate();
		customer.customerId++;
		Long temp = customer.customerId -1;
		return temp;

	}

	public void deleteCustomer(Long customerId) {
		Optional<Customer> customerOptional = customerRepository.findCustomerById(customerId);
		if (!customerOptional.isPresent()) {
			throw new IllegalStateException("An employee with that ID does not exist.");
		}
		customerRepository.deleteById(customerId);
	}

	@Transactional
	public void updateEmployee(Long customerId, String password, String name, String email, Long phone, String building_number, String street_number, String city, String province) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));
		if (password != null && password.length() != 0 && !Objects.equals(password, customer.getPassword())) {
			customer.setPassword(password);
		}
		if (name != null && name.length() != 0 && !Objects.equals(name, customer.getName())) {
			customer.setName(name);
		}
		if (email != null && email.length() != 0 && !Objects.equals(email, customer.getEmail())) {
			Optional<Customer> customerOptionalEmail = customerRepository.findCustomerByEmail(email);
			if (customerOptionalEmail.isPresent()) {
				throw new IllegalStateException("A customer with that email address already exists");
			}
			customer.setEmail(email);
		}
		if (phone != null && phone != 0 && !Objects.equals(phone, customer.getPhone())) {
			Optional<Customer> customerOptionalPhone = customerRepository.findCustomerByPhone(phone);
			if (customerOptionalPhone.isPresent()) {
				throw new IllegalStateException("A customer with that phone number already exists");
			}
			customer.setPhone(phone);
		}
		if (building_number != null && !Objects.equals(building_number, customer.getBuildingNumber())) {
			customer.setBuildingNumber(building_number);
		}
		if (street_number != null && !Objects.equals(street_number, customer.getStreetNumber())) {
			customer.setStreetNumber(street_number);
		}
		if (city != null && city.length() != 0 && !Objects.equals(city, customer.getCity())) {
			customer.setCity(city);
		}
		if (province != null && province.length() != 0 && !Objects.equals(province, customer.getProvince())) {
			customer.setProvince(province);
		}
	}
}
