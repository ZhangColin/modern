package com.cartisan.modern.transaction.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static com.cartisan.modern.common.Formats.DAY;

@Entity
@Table(name="transactions")
@Data
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Type type;
    private String description;

    @DateTimeFormat(pattern = DAY)
    private Date date;
    private Integer amount;

    public enum Type {
        Income
    }
}
