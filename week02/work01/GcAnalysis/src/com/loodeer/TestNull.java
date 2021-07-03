package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-03 21:44
 */
public class TestNull {

    /**
     * // JDK16的空指针异常长这样
     * Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Object.toString()" because "localObj" is null
     *  at TestNull.main(TestNull.java:7)
     *
     *  // 执行的命令包括:
     * jenv versions
     * jenv local 16
     * javac -g TestNull.java
     * java TestNull
     */
    public static void main(String... args){
        Object localObj = "renfufei";
        if("".length() < 10){
            localObj = null;
        }
        System.out.println(localObj.toString());
    }
}
