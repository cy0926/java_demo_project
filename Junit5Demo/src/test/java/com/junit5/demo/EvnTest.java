package com.junit5.demo;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvnTest {
    @Test
    public void evnTest(){
        assertEquals(1,2,"实际结果与期望结果不符合");
    }
}
