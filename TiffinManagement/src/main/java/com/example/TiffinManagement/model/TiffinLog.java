package com.example.TiffinManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tiffin_log")
public class TiffinLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private Integer quantity;

    private Double pricePerUnit;

    private String note;

    public enum MealType {
        LUNCH, DINNER, BOTH
    }
}