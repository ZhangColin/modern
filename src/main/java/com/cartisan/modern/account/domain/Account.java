package com.cartisan.modern.account.domain;

import com.cartisan.modern.common.validator.Unique;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Unique(fieldCheck = Accounts.class)
    private String name;

    private int balanceBroughtForward;
}
