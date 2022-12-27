package com.example.dbmsprojectbackend.Recipient;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Customer.CustomerRepository;
import com.example.dbmsprojectbackend.PaymentDetails.PaymentDetails;
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
		return recipientRepository.findRecipientForACustomer(customerId);
	}

	@PostMapping(path = "{customerId}")
	public Recipient addNewRecipient(@RequestBody Recipient recipient, @PathVariable("customerId") Long customerId) {
		Customer customer = customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that id does not exist."));
		recipientService.addNewRecipient(recipient, customer);
		return recipient;
	}

	@DeleteMapping(path = "{recipientId}")
	public Recipient deleteRecipient(@PathVariable("recipientId") Long recipientId) {
		recipientService.deleteRecipient(recipientId);
		Recipient recipient;
		return  null;

	}
	@PutMapping(path = "{recipientId}")
	public Recipient updateRecipient(
			@PathVariable("recipientId") Long recipientId,
			@RequestBody Recipient recipient) {
		recipientService.updateRecipient(recipientId, recipient.getName(), recipient.getEmail(), recipient.getPhone(),  recipient.getBuildingNumber(),  recipient.getStreetNumber(),  recipient.getCity(),  recipient.getProvince());
		return recipient;
	}
	@GetMapping(path = "filter/{customerId}")
	public List<Recipient> getRecipientOfACustomerByFilter(@PathVariable Long customerId, @RequestBody Filters filters) {
		return recipientService.getRecipientByFilter(customerId, filters);
	}

}