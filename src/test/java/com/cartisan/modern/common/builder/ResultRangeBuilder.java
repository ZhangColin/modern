package com.cartisan.modern.common.builder;

import com.cartisan.modern.common.controller.ResultRange;
import com.cartisan.modern.common.controller.ResultRangeFactory;

public class ResultRangeBuilder {
    public static ResultRangeBuilder defaultResultRange(){
        return new ResultRangeBuilder();
    }

    public ResultRange build(){
        return new ResultRangeFactory().create(1);
    }
}
