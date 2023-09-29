package com.khun.studentregistration.demo;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateMaximumTest {
    @Test
    public void testFindMax(){
        assertEquals(4,CalculateMaximum.findMax(new
                int[]{1,3,4,2}));
//        assertEquals(-1,CalculateMaximum.findMax(new int[]{-
//                12,-1,-3,-4,-2}));
    }

    @Test
    public void test() {
        assertFalse(CalculateMaximum.m(-1));
        assertFalse(CalculateMaximum.m(0));
        assertTrue(CalculateMaximum.m(1));
        assertFalse(CalculateMaximum.mm());

    }
}