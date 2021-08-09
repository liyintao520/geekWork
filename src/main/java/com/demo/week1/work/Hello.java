package com.demo.week1.work;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName Hello.java
 * @Description 涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码
 *              编译代码, 执行命令： javac -g Hello.java
 *              javap -v Hello.class
 * @createTime 2021年08月09日 11:26:00
 */
public class Hello {
    public static void main(String[] args) {
        int num1 = 1;
        double num2 = 1.0D;
        long num3 = 1L;
        byte num4 = 1;
        if (true) {
            // 错误用法: num2 + num3 = 1.01 不同类型操作，默认强制转换成
            Object obj = num2 + num3;
            System.out.println("错误用法: num2 + num3 = " + obj);
            System.out.println("结果类型为：" + obj.getClass().getCanonicalName());
        }
        for (int i = 0; i < num1; i++) {
            // 四则运算: num1 * num4 = 1
            System.out.println("四则运算: num1 * num4 = " + num1 * num4);
        }
    }
}
