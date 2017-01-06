package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import static com.cartisan.modern.common.Formats.DAY;

@Controller
public class MonthlyBudgetController {
    private final MonthlyBudgetPlanner planner;

    @Autowired
    public MonthlyBudgetController(MonthlyBudgetPlanner planner) {
        this.planner = planner;
    }

    @RequestMapping("/confirm_add_budget_for_month")
    public String confirm(@ModelAttribute MonthlyBudget monthlyBudget, Model model) {
        planner.addMonthlyBudget(monthlyBudget)
                .success(setMessage(model, "Successfully add budget for month"))
                .failed(setMessage(model, "Add budget for month failed"));

        return "add_budget_for_month";
    }

    @RequestMapping("/get_amount")
    public String getAmount(
            @RequestParam("startDate") @DateTimeFormat(pattern = DAY) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = DAY) Date endDate, Model model) {
        model.addAttribute("amount", planner.getAmount(startDate, endDate));

        return "get_amount";
    }

    private Runnable setMessage(Model model, String attributeValue) {
        return () -> model.addAttribute("message", attributeValue);
    }
}
