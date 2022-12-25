package com.example.dbmsprojectbackend.PaymentDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("paymentdetails")
public class PaymentDetailsController {

    private final PaymentDetailsService paymentDetailsService;

    @Autowired
    public PaymentDetailsController(PaymentDetailsService paymentDetailsService) { this.paymentDetailsService = paymentDetailsService; }

    @GetMapping
    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetailsService.getPaymentDetails();
    }

    @PostMapping
    public void addPaymentDetails(@RequestBody PaymentDetails paymentDetails) {
        paymentDetailsService.addNewPaymentDetails(paymentDetails);
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
