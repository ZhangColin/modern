package com.cartisan.modern.common;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils {
    public static void copyProperties(Object target, Object source) {
        try{
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
