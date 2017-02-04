package com.cartisan.modern.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int balanceBroughtForward;
}
