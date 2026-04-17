
package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.entity.User;
import com.example.expensetracker.service.CategoryService;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String listExpenses(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        List<Expense> expenses = expenseService.findActiveByUserId(userId);
        model.addAttribute("expenses", expenses);
        model.addAttribute("userName", session.getAttribute("userName"));
        return "expenses";
    }

    @GetMapping("/add")
    public String addForm(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (session.getAttribute("userId") == null) return "redirect:/login";
        model.addAttribute("expense", new Expense());
        model.addAttribute("categories", categoryService.getCategoriesByUser(userId));
        return "add-expense";
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        User user = userService.findById(userId);
        expense.setUser(user);
        if (expense.getDate() == null) expense.setDate(LocalDate.now());
        expenseService.save(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        expenseService.deleteById(id);
        return "redirect:/expenses";
    }




    // Soft delete
    @GetMapping("/soft-delete/{id}")
    public String softDelete(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        expenseService.softDelete(id);
        return "redirect:/expenses";
    }

    // Trash page
    @GetMapping("/trash")
    public String trash(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        model.addAttribute("deletedExpenses", expenseService.findDeletedByUserId(userId));
        return "trash";
    }

    // Restore
    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        expenseService.restore(id);
        return "redirect:/expenses/trash";
    }

    // Export CSV
    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response, HttpSession session) throws IOException {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return;
        List<Expense> expenses = expenseService.findActiveByUserId(userId);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=expenses.csv");

        PrintWriter writer = response.getWriter();
        writer.println("Title,Category,Amount,Date");
        for (Expense e : expenses) {
            writer.println(e.getTitle() + "," + e.getCategory() + "," + e.getAmount() + "," + e.getDate());
        }
        writer.flush();
    }

    // Export PDF
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response, HttpSession session) throws IOException {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return;
        List<Expense> expenses = expenseService.findActiveByUserId(userId);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=expenses.pdf");

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new com.itextpdf.text.Paragraph("Expense Report"));
            document.add(new com.itextpdf.text.Paragraph(" "));

            com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(4);
            table.addCell("Title");
            table.addCell("Category");
            table.addCell("Amount");
            table.addCell("Date");

            for (Expense e : expenses) {
                table.addCell(e.getTitle());
                table.addCell(e.getCategory());
                table.addCell(String.valueOf(e.getAmount()));
                table.addCell(e.getDate().toString());
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

}
