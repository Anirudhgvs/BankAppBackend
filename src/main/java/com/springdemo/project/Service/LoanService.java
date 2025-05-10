package com.springdemo.project.Service;

import com.springdemo.project.DTO.LoanApplicationDTO;
import com.springdemo.project.Entity.Loan;
import com.springdemo.project.Entity.LoanStatus;
import com.springdemo.project.Entity.User;
import com.springdemo.project.Repositories.LoanRepository;
import com.springdemo.project.Repositories.UserRepo;
import com.springdemo.project.Utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AuthUtil authUtil;

    @Transactional
    public Loan applyForLoan(LoanApplicationDTO application) {
        User currentUser = authUtil.getCurrentUser();

        Loan loan = Loan.builder()
                .user(currentUser)
                .amount(application.getAmount())
                .termMonths(application.getTermMonths())
                .purpose(application.getPurpose())
                .status(LoanStatus.PENDING)
                .applicationDate(LocalDateTime.now())
                .interestRate(calculateInterestRate(application.getAmount(), application.getTermMonths()))
                .build();

        return loanRepository.save(loan);
    }

    public List<Loan> getUserLoans() {
        User currentUser = authUtil.getCurrentUser();
        return loanRepository.findByUser(currentUser);
    }

    @Transactional
    public Loan approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setStatus(LoanStatus.APPROVED);
        loan.setApprovalDate(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    private Double calculateInterestRate(Double amount, Integer termMonths) {
        // Simple interest rate calculation based on amount and term
        // This is a placeholder - implement your actual interest rate calculation logic
        return 5.0 + (amount / 10000) + (termMonths / 12);
    }
}