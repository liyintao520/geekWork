package com.demo.week1.jvm;

import java.util.Base64;

/**
 * @author liyintao
 * @version 1.0.0
 * @ClassName HelloClassLoader.java
 * @Description 自定义ClassLoader。 加载Hello并执行
 * @createTime 2021年08月08日 17:00:00
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("com.demo.week1.jvm.Hello").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
