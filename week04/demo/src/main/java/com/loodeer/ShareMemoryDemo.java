package com.loodeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luzuheng
 * @since 2021-07-15 10:32
 * 通过这个例子，我们想强调说明执行流、内存和程序代码之间的关系。1）该例中有三条执行流，一条执行main方法，另外两条执行ChildThread的run方法。2）不同执行流可以访问和操作相同的变量，如本例中的shared和list变量。3）不同执行流可以执行相同的程序代码，如本例中incrShared方法，ChildThread的run方法，被两条ChildThread执行流执行，incrShared方法是在外部定义的，但被ChildThread的执行流执行。在分析代码执行过程时，理解代码在被哪个线程执行是很重要的。4）当多条执行流执行相同的程序代码时，每条执行流都有单独的栈，方法中的参数和局部变量都有自己的一份。
 */
public class ShareMemoryDemo {
    private static int shared = 0;
    private static void incrShared() {
        shared ++;
    }
    static class ChildThread extends Thread {
        List<String> list;

        public ChildThread(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            incrShared();
            list.add(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();
        ChildThread t1 = new ChildThread(list);
        ChildThread t2 = new ChildThread(list);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(shared);
        System.out.println(list);
    }
}
