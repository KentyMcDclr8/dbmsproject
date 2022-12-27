package com.example.dbmsprojectbackend.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {
	public final CustomerService customerService;
	private final CustomerRepository customerRepository;


	@Autowired
	public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
		this.customerService = customerService;
		this.customerRepository = customerRepository;
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	//new
	@PostMapping
	public Long addNewCustomer(@RequestBody Customer customer) {
		return customerService.addNewCustomer(customer);
	}

	@DeleteMapping(path = "{customerId}")
	public Customer deleteCustomer(@PathVariable("customerId") Long customerId) {
		customerService.deleteCustomer(customerId);
		return new Customer();}

	@PutMapping(path = "{customerId}")
	public Customer updateCustomer(
			@PathVariable("customerId") Long customerId,
			@RequestBody Customer customer) {
		customerService.updateEmployee(customerId, customer.getPassword(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getBuildingNumber(), customer.getStreetNumber(), customer.getCity(), customer.getProvince());
		return customer;
	}
}