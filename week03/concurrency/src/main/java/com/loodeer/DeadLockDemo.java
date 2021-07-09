package com.loodeer;

/**
 * @author luzuheng
 * @since 2021-07-09 14:11
 */
public class DeadLockDemo {
    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}

/*
jstack xxxxx

"Thread-0" #12 prio=5 os_prio=31 cpu=7.21ms elapsed=195.55s tid=0x00007fe01a0ed800 nid=0xa103 waiting for monitor entry  [0x000070000a02d000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.loodeer.DeadLockDemo$1.run(DeadLockDemo.java:25)
	- waiting to lock <0x000000070fecc8b0> (a java.lang.String)
	- locked <0x000000070fecc880> (a java.lang.String)
	at java.lang.Thread.run(java.base@11.0.5/Thread.java:834)

"Thread-1" #13 prio=5 os_prio=31 cpu=6.94ms elapsed=195.54s tid=0x00007fe01a0f0800 nid=0x9f03 waiting for monitor entry  [0x000070000a130000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.loodeer.DeadLockDemo$2.run(DeadLockDemo.java:34)
	- waiting to lock <0x000000070fecc880> (a java.lang.String)
	- locked <0x000000070fecc8b0> (a java.lang.String)
	at java.lang.Thread.run(java.base@11.0.5/Thread.java:834)

Found one Java-level deadlock:
=============================
"Thread-0":
  waiting to lock monitor 0x00007fe018e13f00 (object 0x000000070fecc8b0, a java.lang.String),
  which is held by "Thread-1"
"Thread-1":
  waiting to lock monitor 0x00007fe018e13e00 (object 0x000000070fecc880, a java.lang.String),
  which is held by "Thread-0"

Java stack information for the threads listed above:
===================================================
"Thread-0":
	at com.loodeer.DeadLockDemo$1.run(DeadLockDemo.java:25)
	- waiting to lock <0x000000070fecc8b0> (a java.lang.String)
	- locked <0x000000070fecc880> (a java.lang.String)
	at java.lang.Thread.run(java.base@11.0.5/Thread.java:834)
"Thread-1":
	at com.loodeer.DeadLockDemo$2.run(DeadLockDemo.java:34)
	- waiting to lock <0x000000070fecc880> (a java.lang.String)
	- locked <0x000000070fecc8b0> (a java.lang.String)
	at java.lang.Thread.run(java.base@11.0.5/Thread.java:834)

Found 1 deadlock.
 */