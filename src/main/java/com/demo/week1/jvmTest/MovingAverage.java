package com.demo.week1.jvmTest;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName MovingAverage.java
 * @Description 移动平均数
 * @createTime 2021年08月07日 12:06:00
 */
public class MovingAverage {
    private int count = 0;
    private double sum = 0.0D;

    public void submit(double value) {
        this.count++;
        this.sum += value;
    }

    public double getAvg() {
        if (0 == this.count) {
            return sum;
        }
        return this.sum / this.count;
    }
}
