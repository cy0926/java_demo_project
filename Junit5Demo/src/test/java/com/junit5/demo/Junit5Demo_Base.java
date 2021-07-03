package com.junit5.demo;

import com.util.Calculator;
import org.junit.Test;

/**
 * 基础脚本，分别执行了加减乘除，并打印了结果
 */

public class Junit5Demo_Base {
    @Test
    public void addTest(){
        int result = Calculator.add(4,2);
        System.out.println(result);
    }

    @Test
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
    }

    @Test
    public void multipyTest(){
        int result = Calculator.multipiy(3,3);
        System.out.println(result);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(9,3);
        System.out.println(result);
    }

    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(2);
        result = Calculator.count(3);
        result = Calculator.count(4);
        System.out.println(result);
    }
}
