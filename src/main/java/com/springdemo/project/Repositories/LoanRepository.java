package com.springdemo.project.Repositories;

import com.springdemo.project.Entity.Loan;
import com.springdemo.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser(User user);
}