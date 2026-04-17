
package com.example.expensetracker.controller;

import com.example.expensetracker.entity.User;
import com.example.expensetracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User u = userService.findByEmail(email);
        if (u == null || !u.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
        session.setAttribute("userId", u.getId());
        session.setAttribute("userName", u.getName());
        return "redirect:/expenses";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
