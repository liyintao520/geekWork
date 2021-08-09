package com.demo.week1.work;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName Hello.java
 * @Description 涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码
 * @createTime 2021年08月09日 11:26:00
 */
public class Hello {
    public static void main(String[] args) {
        int num1 = 1;
        double num2 = 2.0D;
        long num3 = 3L;
        byte num4 = 4;
        if (true) {
            // 错误用法: num2 + num3 = 2.03
            System.out.println("错误用法: num2 + num3 = " + num2 + num3);
        }
        for (int i = 0; i < num1; i++) {
            // 四则运算: num1 * num4 = 4
            System.out.println("四则运算: num1 * num4 = " + num1 * num4);
        }
    }
}
