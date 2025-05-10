package com.springdemo.project.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private String description;
}