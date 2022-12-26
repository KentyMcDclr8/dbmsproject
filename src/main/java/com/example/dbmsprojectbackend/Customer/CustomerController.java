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
		Customer customer;
		return  customer = customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));
	}
	@PutMapping(path = "{customerId}")
	public Customer updateCustomer(
			@PathVariable("customerId") Long customerId,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Long phone,
			@RequestParam(required = false) String building_number,
			@RequestParam(required = false) String street_number,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String province) {
		customerService.updateEmployee(customerId, password, name, email, phone,  building_number,  street_number,  city,  province);
		Customer customer;
		return  customer = customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));

	}
}