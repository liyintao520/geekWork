package com.demo.week1.jvmTest;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName LocalVariableTest.java
 * @Description 调用进行计算 MovingAverage.java
 * @createTime 2021年08月07日 12:13:00
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
        System.out.println("计算结果：" + avg);
    }
}
