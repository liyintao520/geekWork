package com.demo.week1.jvmTest;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName ForLoopTest.java
 * @Description 循环
 * @createTime 2021年08月07日 12:31:00
 */
public class ForLoopTest {
    private static int[] numbers = {1, 6, 8};

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        for (int number : numbers) {
            ma.submit(number);
        }
        double avg = ma.getAvg();
        System.out.println("ForLoopTest--result：" + avg);
    }
}
