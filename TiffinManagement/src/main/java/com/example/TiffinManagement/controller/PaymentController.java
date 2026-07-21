package com.example.TiffinManagement.controller;

import com.example.TiffinManagement.model.Payment;
import com.example.TiffinManagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/record/{providerId}/{receiverId}")
    public String recordPayment(
            @PathVariable Long providerId,
            @PathVariable Long receiverId,
            @RequestBody Payment payment) {
        return paymentService.recordPayment(providerId, receiverId, payment);
    }

    @GetMapping("/receiver/{receiverId}")
    public List<Payment> getPaymentsByReceiver(@PathVariable Long receiverId) {
        return paymentService.getPaymentsByReceiver(receiverId);
    }

    @GetMapping("/provider/{providerId}")
    public List<Payment> getPaymentsByProvider(@PathVariable Long providerId) {
        return paymentService.getPaymentsByProvider(providerId);
    }

    @GetMapping("/summary/{providerId}/{receiverId}")
    public Map<String, Double> getPaymentSummary(
            @PathVariable Long providerId,
            @PathVariable Long receiverId) {
        return paymentService.getPaymentSummary(providerId, receiverId);
    }
}