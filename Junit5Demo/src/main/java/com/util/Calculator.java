package com.util;

public class Calculator {
    public static int result = 0;

    public static int add(int x, int y){
        result = x + y;
        return result;
    }

    public static int count(int x) throws InterruptedException {
        int i = result;
        Thread.sleep(1000);
        result = i + x;
        return result;
    }

    public static int subtract(int x ,int y){
        result = x - y;
        return result;
    }

    public static int multipiy(int x, int y){
        result = x * y;
        return result;
    }

    public static int divide(int x, int y){
        result = x / y;
        return result;
    }

    public static void clear(){
        result = 0;
        System.out.println("当前结果已清零");
    }

}

