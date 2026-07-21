package com.example.TiffinManagement.controller;

import com.example.TiffinManagement.model.TiffinLog;
import com.example.TiffinManagement.service.TiffinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiffin")
public class TiffinController {

    @Autowired
    private TiffinService tiffinService;

    @PostMapping("/log/{providerId}/{receiverId}")
    public String logTiffin(
            @PathVariable Long providerId,
            @PathVariable Long receiverId,
            @RequestBody TiffinLog tiffinLog) {
        return tiffinService.logTiffin(providerId, receiverId, tiffinLog);
    }

    @GetMapping("/provider/{providerId}")
    public List<TiffinLog> getLogsByProvider(@PathVariable Long providerId) {
        return tiffinService.getLogsByProvider(providerId);
    }

    @GetMapping("/receiver/{receiverId}")
    public List<TiffinLog> getLogsByReceiver(@PathVariable Long receiverId) {
        return tiffinService.getLogsByReceiver(receiverId);
    }

    @GetMapping("/logs/{providerId}/{receiverId}")
    public List<TiffinLog> getLogsByProviderAndReceiver(
            @PathVariable Long providerId,
            @PathVariable Long receiverId) {
        return tiffinService.getLogsByProviderAndReceiver(providerId, receiverId);
    }

    @GetMapping("/total/{providerId}/{receiverId}")
    public Double calculateTotalCost(
            @PathVariable Long providerId,
            @PathVariable Long receiverId) {
        return tiffinService.calculateTotalCost(providerId, receiverId);
    }
}