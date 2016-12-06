package com.cartisan.modern;

import ca.digitalcave.moss.common.DateUtil;
import com.cartisan.modern.budget.BudgetCategory;
import com.cartisan.modern.budget.BudgetCategoryType;
import com.cartisan.modern.budget.BudgetCategoryTypeMonthly;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BudgetCategoryTest {
    @Test
    public void testBudgetCategory(){
        try {
            BudgetCategoryType bct = new BudgetCategoryTypeMonthly();
            BudgetCategory bc = new BudgetCategory();
            bc.setPeriodType(bct);
            bc.setAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), 100);
            bc.setAmount(DateUtil.getDate(2007, Calendar.MAY, 1), 200);
            bc.setAmount(DateUtil.getDate(2007, Calendar.JUNE, 1), 240);
            bc.setAmount(DateUtil.getDate(2007, Calendar.JULY, 1), 10);
            bc.setAmount(DateUtil.getDate(2007, Calendar.AUGUST, 1), 130);
            bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 1), 13);
            bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 333);
            bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 1), 331);

            assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1)), 1);
            assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 10)), 1);
            assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 28)), 1);

            assertEquals((double) 300, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), DateUtil.getDate(2007, Calendar.MAY, 31)), 1);
            assertEquals((double) 149, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 15), DateUtil.getDate(2007, Calendar.MAY, 15)), 1);

        }
        catch (Exception e){
            fail("Exception: " + e);
        }
    }
}
