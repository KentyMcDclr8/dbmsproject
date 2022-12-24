package com.example.dbmsprojectbackend.Customer;

import com.example.dbmsprojectbackend.Employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
	public final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@PostMapping
	public void addNewCustomer(@RequestBody Customer customer) {
		customerService.addNewCustomer(customer);
	}

	@DeleteMapping(path = "{customerId}")
	public void deleteEmployee(@PathVariable("customerId") Long customerId) {
		customerService.deleteCustomer(customerId);
	}
	@PutMapping(path = "{customerId}")
	public void updateEmployee(
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
	}
}