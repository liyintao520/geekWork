package com.demo.week1.work;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

// 画一张图，展示 Xmx、Xms、Xmn、Metaspache、DirectMemory、Xss这些内存参数的关系。
// 对应地址：https://www.processon.com/mindmap/610ff1261e08536191cd9476

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName Base64HelloClassLoader.java
 * @Description 自定义一个 Classloader，加载一个 Hello.xlass 文件，
 * 通过反射执行Hello类的 hello 方法，此文件内容是一个 Hello.class文件所有字节（x=255-x）处理后的文件。
 * 文件在/Users/liyintao/Desktop/Geek Learning/geekDemo/src/main/resources/file/Hello.xlass。
 * @createTime 2021年08月08日 19:27:00
 */
@Slf4j
public class Base64HelloClassLoader extends ClassLoader {

    private static String inFile = "/Users/liyintao/Desktop/Geek Learning/geekWork/src/main/resources/file/Hello.xlass";
    private static String outFile = "/Users/liyintao/Desktop/Geek Learning/geekWork/src/main/java/com/demo/week1/work/Hello.class";

    public static void main(String[] args) {
//        readFileByBytes();

        try {
            Class<?> hello = new Base64HelloClassLoader().loadClass("Hello");
            // 查看里面有什么方法
            for (Method m : hello.getDeclaredMethods()) {
                System.out.println(hello.getSimpleName() + "." + m.getName());
            }
            // 创建对象
            Object instance = hello.newInstance();
            // 调用实例方法
            Method method = hello.getMethod("hello");
            method.invoke(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> newClass = null;
        try {
            // 第一种：直接读取文件得到byte数组，进行255-x操作
            File file = ResourceUtils.getFile("classpath:file/Hello.xlass");    //  读取文件
            byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            // 第二种：加密Hello.xlass：   base64 Hello.xlass  得到字符串，在得到byte数组进行255-X操作
//            String helloBase64 = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
//            byte[] bytes = decode(helloBase64);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            Files.write(Paths.get(outFile), bytes); //  写入文件
            newClass = defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return newClass;
    }

    /**
     * base64解码操作
     *
     * @param base64
     * @return
     */
    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    /**
     * 读取文件并写入文件
     */
    public static void readFileByBytes() {
        File file = new File(inFile);
        String fileName = file.getName();
        System.out.println(fileName);
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            OutputStream out = new FileOutputStream(outFile);
            out.write(bytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
