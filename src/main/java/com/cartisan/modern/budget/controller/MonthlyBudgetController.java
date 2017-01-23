package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import com.cartisan.modern.budget.view.PresentableAddMonthlyBudget;
import com.cartisan.modern.budget.view.PresentableMonthlyBudgetAmount;
import com.cartisan.modern.common.view.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

import static com.cartisan.modern.common.Formats.DAY;
import static com.cartisan.modern.common.controller.Urls.*;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Controller
@RequestMapping(MONTHLYBUDGET)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class MonthlyBudgetController {
    private final MonthlyBudgetPlanner planner;
    private final PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount;
    private final PresentableAddMonthlyBudget presentableAddMonthlyBudget;
    private final Message message;

    @Value("${monthlybudget.add.success}")
    String successMessage;

    @Value("${monthlybudget.add.failed")
    String failedMessage;

    @Autowired
    public MonthlyBudgetController(MonthlyBudgetPlanner planner,
                                   PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount,
                                   PresentableAddMonthlyBudget presentableAddMonthlyBudget, Message message) {
        this.planner = planner;
        this.presentableMonthlyBudgetAmount = presentableMonthlyBudgetAmount;
        this.presentableAddMonthlyBudget = presentableAddMonthlyBudget;
        this.message = message;
    }

    @PostMapping(ADD)
    public ModelAndView submitAddMonthlyBudget(
            @Valid @ModelAttribute MonthlyBudget monthlyBudget, BindingResult result) {
        if (!result.hasFieldErrors())
            planner.addMonthlyBudget(monthlyBudget)
                    .success(() -> message.display(successMessage))
                    .failed(() -> message.display(failedMessage));

        return addMonthlyBudget();
    }

    @GetMapping(ADD)
    public ModelAndView addMonthlyBudget() {
        return presentableAddMonthlyBudget;
    }

    @GetMapping(TOTALAMOUNT)
    public ModelAndView totalAmountOfMonthlyBudget(
            @RequestParam("startDate") @DateTimeFormat(pattern = DAY) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = DAY) Date endDate) {
        return presentableMonthlyBudgetAmount.display(planner.getAmount(startDate, endDate));
    }
}
