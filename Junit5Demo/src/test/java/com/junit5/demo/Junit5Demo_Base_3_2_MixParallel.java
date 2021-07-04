package com.junit5.demo;

import com.util.Calculator;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试当并发执行加法和减法操作的时候，结果是否正确
 * 发现了计算器并发进行加法和减法运算的时候，程序会出错，原因是：result参数，在add 和subtract 中没有独立导致
 */

public class Junit5Demo_Base_3_2_MixParallel {
    @RepeatedTest(10)
    public void addTest(){
        int result = Calculator.add(4,2);
        System.out.println(result);
        assertEquals(6,result);
    }

    @RepeatedTest(10)
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }
}
