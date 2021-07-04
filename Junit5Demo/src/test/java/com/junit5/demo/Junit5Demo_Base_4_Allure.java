package com.junit5.demo;

import com.util.Calculator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 基础脚本，分别执行了加减乘除，并打印了结果
 * 1.进行了优化，添加自动断言，解决了需要人工检查结果的问题
 */

@Epic("Epic计算器项目")
@Feature("Feature冒烟测试用例")
public class Junit5Demo_Base_4_Allure {
    @Test
    @Description("Description 加法测试")
    @Story("Story 加法测试用例")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("Issue 看看这个是干啥的？")
    @TmsLink("TmsLink--看看这个是干啥的")
    public void addTest(){
        int result01= Calculator.add(4,2);
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
    @Description("Description 减法测试")
    @Story("Story 减法测试用例")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("Issue 看看这个是干啥的？")
    @TmsLink("TmsLink--看看这个是干啥的")
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }
}
