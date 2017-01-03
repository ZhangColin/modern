package com.cartisan.modern.budget.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static com.cartisan.modern.common.Formats.MONTH;

@Entity
@Table(name = "monthly_budgets")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MonthlyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @DateTimeFormat(pattern = MONTH)
    private Date month;

    @NonNull
    private Integer budget;
}
