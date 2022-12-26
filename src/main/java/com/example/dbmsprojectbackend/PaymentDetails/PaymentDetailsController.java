package com.example.dbmsprojectbackend.PaymentDetails;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("paymentdetails")
@CrossOrigin
public class PaymentDetailsController {
    final CustomerRepository customerRepository;

    private final PaymentDetailsService paymentDetailsService;

    @Autowired
    public PaymentDetailsController(CustomerRepository customerRepository, PaymentDetailsService paymentDetailsService) {
        this.customerRepository = customerRepository;
        this.paymentDetailsService = paymentDetailsService; }

    @GetMapping
    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetailsService.getPaymentDetails();
    }

    @GetMapping(path = "{customerId}")
    public List<PaymentDetails> getPaymentDetailsById(@PathVariable("customerId") Long customerId) { return paymentDetailsService.getPaymentDetailsByCustomerId(customerId); }

    @PostMapping(path = "{customerId}")
    public void addPaymentDetails(@RequestBody PaymentDetails paymentDetails, @PathVariable("customerId") Long customerId) {
        Customer customer = customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that id does not exist."));
        paymentDetailsService.addNewPaymentDetails(paymentDetails,customer);
    }

    @DeleteMapping(path = "{accountNumber}/{customerId}")
    public void deletePaymentDetails(@PathVariable("accountNumber") Long accountNumber, @PathVariable("customerId") Long customerId) {
        paymentDetailsService.deletePaymentDetails(accountNumber, customerId);
    }

    @PutMapping(path = "{accountNumber}/{customerId}")
    public void updateEmployee(
            @PathVariable("accountNumber") Long accountNumber,
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String accountTitle,
            @RequestParam(required = false) LocalDate expiryDate,
            @RequestParam(required = false) Integer cvv,
            @RequestParam(required = false) LocalDate lastUsed,
            @RequestParam(required = false) String bankName) {
        paymentDetailsService.updatePaymentDetails(accountNumber, customerId, accountTitle, expiryDate, cvv, lastUsed, bankName);
    }
}
