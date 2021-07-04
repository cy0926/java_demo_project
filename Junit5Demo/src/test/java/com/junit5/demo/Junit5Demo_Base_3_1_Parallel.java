package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * 基础脚本，分别执行了加减乘除，并打印了结果。
 * 1.进行了优化，添加自动断言，解决了需要人工检查结果的问题。
 * 2.进行了优化，使用Junit5提供的assertAll进行断言，增加了脚本的容错性。
 */

public class Junit5Demo_Base_3_1_Parallel {
    @RepeatedTest(10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println(result);
    }

    @RepeatedTest(10)
    public void countSynTest() throws InterruptedException {
        int result = Calculator.synCount(1);
        System.out.println(result);
    }
}
