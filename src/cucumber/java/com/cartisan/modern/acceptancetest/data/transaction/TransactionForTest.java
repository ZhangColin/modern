package com.cartisan.modern.acceptancetest.data.transaction;

import com.cartisan.modern.acceptancetest.data.transformer.DayToLocalDateTransformer;
import com.cartisan.modern.common.BeanUtils;
import com.cartisan.modern.transaction.domain.Transaction;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class TransactionForTest {
    private Transaction.Type type;
    private String description;
    @XStreamConverter(DayToLocalDateTransformer.class)
    private LocalDate date;
    private Integer amount;

    public static <T> List<T> expectedTransactions(List<TransactionForTest> expected, Class<T> clazz) {
        return expected.stream().map(each -> copy(each, clazz)).collect(toList());
    }

    private static <T> T copy(TransactionForTest each, Class<T> clazz) {
        T target = create(clazz);
        BeanUtils.copyProperties(target, each);
        return target;
    }

    private static <T> T create(Class<T> clazz) {
        T target = null;
        try{
            target = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
        }
        return target;
    }
}
