package com.cartisan.modern.acceptancetest.steps;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

public class AssertionHelper {
    public static void assertListDeepEquals(List expected, List actual){
        assertEquals(expected.size(), actual.size());
        assertReflectionEquals(expected, actual, IGNORE_DEFAULTS);
    }
}
