package com.springdemo.project.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private Integer termMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Column(nullable = false)
    private LocalDateTime applicationDate;

    private LocalDateTime approvalDate;

    @Column(nullable = false)
    private String purpose;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}