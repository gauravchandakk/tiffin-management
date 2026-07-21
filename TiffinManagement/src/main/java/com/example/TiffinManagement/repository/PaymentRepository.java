package com.example.TiffinManagement.repository;

import com.example.TiffinManagement.model.Payment;
import com.example.TiffinManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByProvider(User provider);

    List<Payment> findByReceiver(User receiver);

    List<Payment> findByProviderAndReceiver(User provider, User receiver);
}