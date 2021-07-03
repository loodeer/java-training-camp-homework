package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-03 17:58
 */
public class BigObj {

    private static final int _1MB = 1024 *  1024;

    /**
     * 验证大对象直接在老年代上分配
     * ➜  src git:(main) ✗ java -XX:NewSize=10m -XX:MaxNewSize=10m -XX:InitialHeapSize=20m -XX:MaxHeapSize=20m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3m -XX:MaxTenuringThreshold=15 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps com.loodeer.BigObj
     *
     * -XX:PretenureSizeThreshold=3m 表示大于3MB的对象直接在老年代分配
     *
     * Heap
     *  par new generation   total 9216K, used 2719K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     *   eden space 8192K,  33% used [0x00000007bec00000, 0x00000007beea7f38, 0x00000007bf400000)
     *   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
     *   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
     *  concurrent mark-sweep generation total 10240K, used 3072K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000) 这里有 3MB 的内存使用
     *  Metaspace       used 2496K, capacity 4486K, committed 4864K, reserved 1056768K
     *   class space    used 268K, capacity 386K, committed 512K, reserved 1048576K
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * _1MB];
        byte[] array2 = new byte[3 * _1MB];
    }
}
