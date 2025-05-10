package com.springdemo.project.DTO;

import lombok.Data;

@Data
public class LoanApplicationDTO {
    private Double amount;
    private Integer termMonths;
    private String purpose;
}