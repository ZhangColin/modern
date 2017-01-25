package com.cartisan.modern.common.controller;

import org.junit.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PageableFactoryTest {
    @Test
    public void create_pageable_with_default_limit_per_page(){
        PageableFactory pageableFactory = new PageableFactory(10);

        Pageable pageable = pageableFactory.create(1);

        assertThat(pageable.getPageSize()).isEqualTo(10);
        assertThat(pageable.getPageNumber()).isEqualTo(1);
    }
}
