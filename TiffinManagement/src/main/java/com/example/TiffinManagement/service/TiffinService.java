package com.example.TiffinManagement.service;

import com.example.TiffinManagement.model.TiffinLog;
import com.example.TiffinManagement.model.User;
import com.example.TiffinManagement.repository.TiffinLogRepository;
import com.example.TiffinManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiffinService {

    @Autowired
    private TiffinLogRepository tiffinLogRepository;

    @Autowired
    private UserRepository userRepository;

    public String logTiffin(Long providerId, Long receiverId, TiffinLog tiffinLog) {
        User provider = userRepository.findById(providerId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if (provider == null || receiver == null) {
            return "Provider or Receiver not found";
        }

        if (!provider.getRole().equals(User.Role.PROVIDER)) {
            return "Only a provider can log tiffins";
        }

        if (!receiver.getRole().equals(User.Role.CUSTOMER)) {
            return "Receiver must be a customer";
        }

        tiffinLog.setProvider(provider);
        tiffinLog.setReceiver(receiver);
        tiffinLogRepository.save(tiffinLog);
        return "Tiffin logged successfully";
    }

    public List<TiffinLog> getLogsByProvider(Long providerId) {
        User provider = userRepository.findById(providerId).orElse(null);
        if (provider == null) {
            return null;
        }
        return tiffinLogRepository.findByProvider(provider);
    }

    public List<TiffinLog> getLogsByReceiver(Long receiverId) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        if (receiver == null) {
            return null;
        }
        return tiffinLogRepository.findByReceiver(receiver);
    }

    public List<TiffinLog> getLogsByProviderAndReceiver(Long providerId, Long receiverId) {
        User provider = userRepository.findById(providerId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);
        if (provider == null || receiver == null) {
            return null;
        }
        return tiffinLogRepository.findByProviderAndReceiver(provider, receiver);
    }

    public Double calculateTotalCost(Long providerId, Long receiverId) {
        List<TiffinLog> logs = getLogsByProviderAndReceiver(providerId, receiverId);
        if (logs == null) {
            return 0.0;
        }
        double total = 0.0;
        for (TiffinLog log : logs) {
            total += log.getQuantity() * log.getPricePerUnit();
        }
        return total;
    }
}