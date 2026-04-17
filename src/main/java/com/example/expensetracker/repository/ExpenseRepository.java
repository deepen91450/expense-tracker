
package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserIdOrderByDateDesc(Long userId);

    List<Expense> findByUserIdAndDeletedFalseOrderByDateDesc(Long userId);
    List<Expense> findByUserIdAndDeletedTrueOrderByDateDesc(Long userId);

}
