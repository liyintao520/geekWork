package com.demo.week1.work;

import com.demo.week1.jvm.HelloClassLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName A.java        https://github.com/liyintao520/geekWork.git
 * @Description 自定义一个 Classloader，加载一个 Hello.xlass 文件，
 *              执行 hello 方法，此文件内容是一个 Hello.class文件所有字节（x=255-x）处理后的文件。
 *              文件在/Users/liyintao/Desktop/Geek Learning/geekDemo/src/main/resources/file/Hello.xlass。
 * @createTime 2021年08月08日 19:27:00
 */
public class Base64HelloClassLoader extends ClassLoader {

    private static String inFile = "/Users/liyintao/Desktop/Geek Learning/geekDemo/src/main/resources/file/Hello.xlass";
    private static String outFile = "/Users/liyintao/Desktop/Geek Learning/geekDemo/src/main/java/com/demo/week1/work/Hello.class";

    public static void main(String[] args) {
        readFileByBytes();
        try {
//            new Base64HelloClassLoader().findClass("com.demo.week1.work.Hello").newInstance();
//            new Base64HelloClassLoader().findClass("Hello").newInstance();

            Class<?> hello = new Base64HelloClassLoader().loadClass("Hello");
            for (Method m : hello.getDeclaredMethods()) {
                System.out.println(hello.getSimpleName() + "." + m.getName());
            }
            Object instance = hello.newInstance();
            Method method = hello.getMethod("hello");
            method.invoke(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void readFileByBytes(){
        File file = new File(inFile);
        String fileName = file.getName();
//        System.out.println(fileName);
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            for(int i=0;i<bytes.length;i++){
                bytes[i] = (byte)(255-bytes[i]);
            }
            OutputStream out = new FileOutputStream(outFile);
            out.write(bytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAGEhlbGxvIENsYXNzIEluaXRpYWxpemVkIQcAGQwAGgAbAQAYY29tL2RlbW8vd2VlazEvanZtL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAAAAUqtwABsQAAAAEACgAAAAYAAQAAAAoACAALAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAADAAIAA0AAQAMAAAAAgAN";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }
    /**
     *
     * @param base64
     * @return
     */
    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
