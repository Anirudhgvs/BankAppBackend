package com.springdemo.project.Controller;

import com.springdemo.project.DTO.LoanApplicationDTO;
import com.springdemo.project.Entity.Loan;
import com.springdemo.project.Entity.LoanStatus;
import com.springdemo.project.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PutMapping("/admin/approve/{loanId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.approveLoan(loanId));
    }
}