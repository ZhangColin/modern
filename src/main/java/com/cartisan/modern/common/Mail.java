package com.cartisan.modern.common;

import lombok.Data;

@Data
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String text;
}
