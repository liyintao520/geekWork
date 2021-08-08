package com.demo.week1.jvm;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName Hello.java
 * @Description 编译，加密后可以删除该文件以及Hello.class文件   javac jvm/Hello.java;  2: base64 jvm/Hello.class 得到编译后的class文件
 * @createTime 2021年08月08日 16:59:00
 */
public class Hello {
    static {
        System.out.println("Hello Class Initialized!");
    }
}
