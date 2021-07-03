package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 基础脚本，分别执行了加减乘除，并打印了结果。
 * 1.进行了优化，添加自动断言，解决了需要人工检查结果的问题。
 * 2.进行了优化，使用Junit5提供的assertAll进行断言，增加了脚本的容错性。
 */

public class Junit5Demo_Base_2 {
    @Test
    public void addTest(){
        final int result01= Calculator.add(4,2);
        System.out.println(result01);


        int result02 = Calculator.add(5,2);
        System.out.println(result02);


        int result03 = Calculator.add(6,2);
        System.out.println(result03);

        assertAll("计算结果校验",
                ()-> assertEquals(6,result01),
                ()-> assertEquals(7,result02),
                ()-> assertEquals(8,result03)
                );
    }

    @Test
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }

    @Test
    public void multipyTest(){
        int result = Calculator.multipiy(3,3);
        System.out.println(result);
        assertEquals(9,result);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(9,3);
        System.out.println(result);
        assertEquals(3,result);
    }

    @BeforeEach
    public void clear(){
        Calculator.clear();
    }

    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(2);
        result = Calculator.count(3);
        result = Calculator.count(4);
        System.out.println(result);
        assertEquals(10,result);
    }
}
