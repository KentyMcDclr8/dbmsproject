package com.example.dbmsprojectbackend.Recipient;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipient")
@CrossOrigin
public class RecipientController {
	public final RecipientService recipientService;
	public final CustomerRepository customerRepository;
	private final RecipientRepository recipientRepository;

	@Autowired
	public RecipientController(RecipientService recipientService, CustomerRepository customerRepository,
	                           RecipientRepository recipientRepository) {
		this.recipientService = recipientService;
		this.customerRepository = customerRepository;
		this.recipientRepository = recipientRepository;
	}
	@GetMapping(path = "all")
	public List<Recipient> getRecipient() {
		return recipientService.getRecipient();
	}
	@GetMapping(path = "{customerId}")
	public List<Recipient> getRecipientOfACustomer(@PathVariable Long customerId) {
		return recipientRepository.findReipientForACustomer(customerId);
	}

	@PostMapping(path = "{customerId}")
	public void addNewRecipient(@RequestBody Recipient recipient, @PathVariable("customerId") Long customerId) {
		Customer customer = customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that id does not exist."));
		recipientService.addNewRecipient(recipient, customer);
	}

	@DeleteMapping(path = "{recipientId}")
	public void deleteRecipient(@PathVariable("recipientId") Long recipientId) {
		recipientService.deleteRecipient(recipientId);
	}
	@PutMapping(path = "{recipientId}")
	public void updateRecipient(
			@PathVariable("recipientId") Long recipientId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Long phone,
			@RequestParam(required = false) String building_number,
			@RequestParam(required = false) String street_number,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String province) {
		recipientService.updateRecipient(recipientId, name, email, phone,  building_number,  street_number,  city,  province);
	}
}