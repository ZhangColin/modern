package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import static com.cartisan.modern.Urls.MONTHLYBUDGET_ADD;
import static com.cartisan.modern.Urls.MONTHLYBUDGET_TOTALAMOUNT;
import static com.cartisan.modern.common.Formats.DAY;
import static com.cartisan.modern.common.controller.ControllerHelper.setMessage;

@Controller
public class MonthlyBudgetController {
    private final MonthlyBudgetPlanner planner;

    @Autowired
    public MonthlyBudgetController(MonthlyBudgetPlanner planner) {
        this.planner = planner;
    }

    @RequestMapping(value = MONTHLYBUDGET_ADD, method = RequestMethod.POST)
    public String submitAddMonthlyBudget(@ModelAttribute MonthlyBudget monthlyBudget, Model model) {
        planner.addMonthlyBudget(monthlyBudget)
                .success(setMessage(model, "Successfully add budget for month"))
                .failed(setMessage(model, "Add budget for month failed"));

        return MONTHLYBUDGET_ADD;
    }

    @RequestMapping(value = MONTHLYBUDGET_ADD, method = RequestMethod.GET)
    public String addMonthlyBudget() {
        return MONTHLYBUDGET_ADD;
    }

    @RequestMapping(value = MONTHLYBUDGET_TOTALAMOUNT, method = RequestMethod.GET)
    public String totalAmountOfMonthlyBudget(
            @RequestParam("startDate") @DateTimeFormat(pattern = DAY) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = DAY) Date endDate, Model model) {
        model.addAttribute("amount", planner.getAmount(startDate, endDate));

        return MONTHLYBUDGET_TOTALAMOUNT;
    }
}
