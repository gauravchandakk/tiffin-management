package com.example.TiffinManagement.service;

import com.example.TiffinManagement.model.Payment;
import com.example.TiffinManagement.model.Payment.PaymentStatus;
import com.example.TiffinManagement.model.User;
import com.example.TiffinManagement.repository.PaymentRepository;
import com.example.TiffinManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TiffinService tiffinService;

    public String recordPayment(Long providerId, Long receiverId, Payment payment) {
        User provider = userRepository.findById(providerId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if (provider == null || receiver == null) {
            return "Provider or Receiver not found";
        }

        if (!provider.getRole().equals(User.Role.PROVIDER)) {
            return "Only a provider can record payments";
        }

        if (!receiver.getRole().equals(User.Role.CUSTOMER)) {
            return "Receiver must be a customer";
        }

        payment.setProvider(provider);
        payment.setReceiver(receiver);
        payment.setStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);
        return "Payment recorded successfully";
    }

    public List<Payment> getPaymentsByReceiver(Long receiverId) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        if (receiver == null) {
            return null;
        }
        return paymentRepository.findByReceiver(receiver);
    }

    public List<Payment> getPaymentsByProvider(Long providerId) {
        User provider = userRepository.findById(providerId).orElse(null);
        if (provider == null) {
            return null;
        }
        return paymentRepository.findByProvider(provider);
    }

    public Double calculateTotalPaid(Long providerId, Long receiverId) {
        User provider = userRepository.findById(providerId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);
        if (provider == null || receiver == null) {
            return 0.0;
        }
        List<Payment> payments = paymentRepository.findByProviderAndReceiver(provider, receiver);
        double totalPaid = 0.0;
        for (Payment payment : payments) {
            totalPaid += payment.getAmount();
        }
        return totalPaid;
    }

    public Map<String, Double> getPaymentSummary(Long providerId, Long receiverId) {
        Double totalCost = tiffinService.calculateTotalCost(providerId, receiverId);
        Double totalPaid = calculateTotalPaid(providerId, receiverId);
        Double balanceDue = totalCost - totalPaid;

        Map<String, Double> summary = new HashMap<>();
        summary.put("totalCost", totalCost);
        summary.put("totalPaid", totalPaid);
        summary.put("balanceDue", balanceDue);
        return summary;
    }
}