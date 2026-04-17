
package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense save(Expense expense) { return expenseRepository.save(expense); }
    public List<Expense> findByUserId(Long userId) { return expenseRepository.findByUserIdOrderByDateDesc(userId); }
    public void deleteById(Long id) { expenseRepository.deleteById(id); }


    public List<Expense> findActiveByUserId(Long userId) {
        return expenseRepository.findByUserIdAndDeletedFalseOrderByDateDesc(userId);
    }

    public List<Expense> findDeletedByUserId(Long userId) {
        return expenseRepository.findByUserIdAndDeletedTrueOrderByDateDesc(userId);
    }

    public void softDelete(Long id) {
        Expense e = expenseRepository.findById(id).orElse(null);
        if (e != null) {
            e.setDeleted(true);
            expenseRepository.save(e);
        }
    }

    public void restore(Long id) {
        Expense e = expenseRepository.findById(id).orElse(null);
        if (e != null) {
            e.setDeleted(false);
            expenseRepository.save(e);
        }
    }

}
