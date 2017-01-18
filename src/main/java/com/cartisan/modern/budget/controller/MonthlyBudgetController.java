package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import com.cartisan.modern.budget.view.MonthlyBudgetAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

import static com.cartisan.modern.common.Formats.DAY;
import static com.cartisan.modern.common.controller.ControllerHelper.thenSetMessage;
import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_ADD;
import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;

@Controller
@PropertySource("classpath:resultMessages.properties")
public class MonthlyBudgetController {
    private final MonthlyBudgetPlanner planner;
    private final MonthlyBudgetAmount monthlyBudgetAmount;

    @Value("${monthlybudget.add.success}")
    String successMessage;

    @Value("${monthlybudget.add.failed")
    String failedMessage;

    @Autowired
    public MonthlyBudgetController(MonthlyBudgetPlanner planner, MonthlyBudgetAmount monthlyBudgetAmount) {
        this.planner = planner;
        this.monthlyBudgetAmount = monthlyBudgetAmount;
    }

    @RequestMapping(value = MONTHLYBUDGET_ADD, method = RequestMethod.POST)
    public String submitAddMonthlyBudget(@Valid @ModelAttribute MonthlyBudget monthlyBudget, BindingResult result, Model model) {
        if (!result.hasFieldErrors())
            planner.addMonthlyBudget(monthlyBudget)
                    .success(thenSetMessage(model, successMessage))
                    .failed(thenSetMessage(model, failedMessage));

        return addMonthlyBudget();
    }

    @RequestMapping(value = MONTHLYBUDGET_ADD, method = RequestMethod.GET)
    public String addMonthlyBudget() {
        return MONTHLYBUDGET_ADD;
    }

    @RequestMapping(value = MONTHLYBUDGET_TOTALAMOUNT, method = RequestMethod.GET)
    public String totalAmountOfMonthlyBudget(
            @RequestParam("startDate") @DateTimeFormat(pattern = DAY) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = DAY) Date endDate,
            Model model) {
        monthlyBudgetAmount.display(model, planner.getAmount(startDate, endDate));

        return MONTHLYBUDGET_TOTALAMOUNT;
    }
}
