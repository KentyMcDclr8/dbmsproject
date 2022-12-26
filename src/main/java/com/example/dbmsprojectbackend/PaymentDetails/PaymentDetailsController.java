package com.example.dbmsprojectbackend.PaymentDetails;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Customer.CustomerRepository;
import com.example.dbmsprojectbackend.Package.Package;
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
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsController(CustomerRepository customerRepository, PaymentDetailsService paymentDetailsService,
                                    PaymentDetailsRepository paymentDetailsRepository) {
        this.customerRepository = customerRepository;
        this.paymentDetailsService = paymentDetailsService;
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    @GetMapping
    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetailsService.getPaymentDetails();
    }

    @GetMapping(path = "{customerId}")
    public List<PaymentDetails> getPaymentDetailsById(@PathVariable("customerId") Long customerId) { return paymentDetailsService.getPaymentDetailsByCustomerId(customerId); }

    @PostMapping(path = "{customerId}")
    public PaymentDetails addPaymentDetails(@RequestBody PaymentDetails paymentDetails, @PathVariable("customerId") Long customerId) {
        Customer customer = customerRepository.findCustomerById(customerId).orElseThrow(() -> new IllegalStateException("A customer with that id does not exist."));
        paymentDetailsService.addNewPaymentDetails(paymentDetails,customer);
        return paymentDetails;
    }

    @DeleteMapping(path = "{accountNumber}/{customerId}")
    public PaymentDetails deletePaymentDetails(@PathVariable("accountNumber") Long accountNumber, @PathVariable("customerId") Long customerId) {
        paymentDetailsService.deletePaymentDetails(accountNumber, customerId);
        PaymentDetails paymentDetails;
        return  paymentDetails = paymentDetailsRepository.findPaymentDetailsByAccountAndId(accountNumber, customerId).orElseThrow(() -> new IllegalStateException("A payment with that ID does not exist."));

    }

    @PutMapping(path = "{accountNumber}/{customerId}")
    public PaymentDetails updateEmployee(
            @PathVariable("accountNumber") Long accountNumber,
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String accountTitle,
            @RequestParam(required = false) LocalDate expiryDate,
            @RequestParam(required = false) Integer cvv,
            @RequestParam(required = false) LocalDate lastUsed,
            @RequestParam(required = false) String bankName) {
        paymentDetailsService.updatePaymentDetails(accountNumber, customerId, accountTitle, expiryDate, cvv, lastUsed, bankName);
        PaymentDetails paymentDetails;
        return  paymentDetails = paymentDetailsRepository.findPaymentDetailsByAccountAndId(accountNumber, customerId).orElseThrow(() -> new IllegalStateException("A payment with that ID does not exist."));

    }
}
