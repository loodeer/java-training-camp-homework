> **1.**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。



-XX:+UseSerialGC 
-XX:+UseParallelGC 
-XX:+UseConcMarkSweepGC 
-XX:+UseG1GC

-XX:+PrintGC 
-XX:+PrintGCDetails 
-XX:+PrintGCTimeStamps
-XX:+PrintGCDateStamps



**-XX:PretenureSizeThreshold=3m** 大于3MB的对象直接在老年代分配。

-XX:-UseAdaptiveSizePolicy	



It's known that the following options are unrecognized in Java 11:

- -d64
- PrintGCTimeStamps
- PrintGCDateStamps
- PrintTenuringDistribution
- PrintGCCause
- UseGCLogFileRotation
- NumberOfGCLogFiles
- GCLogFileSize

-XX:+IgnoreUnrecognizedVMOptions 可以忽略不识别的参数，使启动不报错。



执行时不加任何参数，默认会用机器内存的四分之一作为 JVM 的最大内存。



```bash
➜  src git:(main) ✗ tree
.
└── com
    └── loodeer
        ├── Main.class
        └── Main.java

2 directories, 2 files
```

```bash
➜  src git:(main) ✗ java -version
java version "11.0.5" 2019-10-15 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.5+10-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.5+10-LTS, mixed mode)
```

```bash
➜  src git:(main) ✗ java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGC com.loodeer.Main
[0.002s][warning][gc] -XX:+PrintGC is deprecated. Will use -Xlog:gc instead.
[0.010s][info   ][gc] Using Serial
正在执行...
[0.237s][info   ][gc] GC(0) Pause Young (Allocation Failure) 136M->49M(494M) 34.517ms
[0.315s][info   ][gc] GC(1) Pause Young (Allocation Failure) 185M->97M(494M) 49.669ms
[0.383s][info   ][gc] GC(2) Pause Young (Allocation Failure) 234M->147M(494M) 48.550ms
[0.473s][info   ][gc] GC(3) Pause Young (Allocation Failure) 284M->203M(494M) 65.101ms
[0.582s][info   ][gc] GC(4) Pause Young (Allocation Failure) 340M->257M(494M) 84.582ms
[0.668s][info   ][gc] GC(5) Pause Young (Allocation Failure) 394M->302M(494M) 59.316ms
[0.752s][info   ][gc] GC(7) Pause Full (Allocation Failure) 438M->245M(494M) 65.267ms
[0.752s][info   ][gc] GC(6) Pause Young (Allocation Failure) 438M->245M(494M) 65.351ms
[0.784s][info   ][gc] GC(8) Pause Young (Allocation Failure) 381M->292M(494M) 10.075ms
[0.858s][info   ][gc] GC(9) Pause Young (Allocation Failure) 429M->346M(494M) 51.795ms
[0.934s][info   ][gc] GC(11) Pause Full (Allocation Failure) 482M->289M(494M) 55.969ms
[0.934s][info   ][gc] GC(10) Pause Young (Allocation Failure) 482M->289M(494M) 56.035ms
[1.011s][info   ][gc] GC(13) Pause Full (Allocation Failure) 425M->307M(494M) 55.011ms
[1.011s][info   ][gc] GC(12) Pause Young (Allocation Failure) 425M->307M(494M) 55.081ms
[1.093s][info   ][gc] GC(15) Pause Full (Allocation Failure) 443M->303M(494M) 59.126ms
[1.093s][info   ][gc] GC(14) Pause Young (Allocation Failure) 443M->303M(494M) 59.193ms
执行结束!共生成对象次数:7656

➜  src git:(main) ✗ java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails com.loodeer.Main
[0.003s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
[0.009s][info   ][gc] Using Serial
[0.009s][info   ][gc,heap,coops] Heap address: 0x00000007e0000000, size: 512 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
正在执行...
[0.189s][info   ][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.228s][info   ][gc,heap      ] GC(0) DefNew: 139776K->17471K(157248K)
[0.228s][info   ][gc,heap      ] GC(0) Tenured: 0K->37218K(349568K)
[0.228s][info   ][gc,metaspace ] GC(0) Metaspace: 3907K->3907K(1056768K)
[0.228s][info   ][gc           ] GC(0) Pause Young (Allocation Failure) 136M->53M(494M) 38.909ms
[0.228s][info   ][gc,cpu       ] GC(0) User=0.03s Sys=0.01s Real=0.04s
[0.258s][info   ][gc,start     ] GC(1) Pause Young (Allocation Failure)
[0.313s][info   ][gc,heap      ] GC(1) DefNew: 157247K->17469K(157248K)
[0.313s][info   ][gc,heap      ] GC(1) Tenured: 37218K->81108K(349568K)
[0.313s][info   ][gc,metaspace ] GC(1) Metaspace: 3907K->3907K(1056768K)
[0.313s][info   ][gc           ] GC(1) Pause Young (Allocation Failure) 189M->96M(494M) 54.367ms
[0.313s][info   ][gc,cpu       ] GC(1) User=0.03s Sys=0.03s Real=0.05s
[0.335s][info   ][gc,start     ] GC(2) Pause Young (Allocation Failure)
[0.388s][info   ][gc,heap      ] GC(2) DefNew: 157245K->17472K(157248K)
[0.388s][info   ][gc,heap      ] GC(2) Tenured: 81108K->129700K(349568K)
[0.388s][info   ][gc,metaspace ] GC(2) Metaspace: 3907K->3907K(1056768K)
[0.389s][info   ][gc           ] GC(2) Pause Young (Allocation Failure) 232M->143M(494M) 53.477ms
[0.389s][info   ][gc,cpu       ] GC(2) User=0.02s Sys=0.02s Real=0.06s
[0.418s][info   ][gc,start     ] GC(3) Pause Young (Allocation Failure)
[0.464s][info   ][gc,heap      ] GC(3) DefNew: 157248K->17471K(157248K)
[0.464s][info   ][gc,heap      ] GC(3) Tenured: 129700K->179410K(349568K)
[0.464s][info   ][gc,metaspace ] GC(3) Metaspace: 3907K->3907K(1056768K)
[0.464s][info   ][gc           ] GC(3) Pause Young (Allocation Failure) 280M->192M(494M) 45.479ms
[0.464s][info   ][gc,cpu       ] GC(3) User=0.03s Sys=0.02s Real=0.04s
[0.488s][info   ][gc,start     ] GC(4) Pause Young (Allocation Failure)
[0.541s][info   ][gc,heap      ] GC(4) DefNew: 157247K->17471K(157248K)
[0.541s][info   ][gc,heap      ] GC(4) Tenured: 179410K->233872K(349568K)
[0.541s][info   ][gc,metaspace ] GC(4) Metaspace: 3907K->3907K(1056768K)
[0.541s][info   ][gc           ] GC(4) Pause Young (Allocation Failure) 328M->245M(494M) 52.847ms
[0.541s][info   ][gc,cpu       ] GC(4) User=0.03s Sys=0.02s Real=0.06s
[0.570s][info   ][gc,start     ] GC(5) Pause Young (Allocation Failure)
[0.624s][info   ][gc,heap      ] GC(5) DefNew: 157247K->17471K(157248K)
[0.624s][info   ][gc,heap      ] GC(5) Tenured: 233872K->285444K(349568K)
[0.624s][info   ][gc,metaspace ] GC(5) Metaspace: 3907K->3907K(1056768K)
[0.624s][info   ][gc           ] GC(5) Pause Young (Allocation Failure) 381M->295M(494M) 53.322ms
[0.624s][info   ][gc,cpu       ] GC(5) User=0.03s Sys=0.02s Real=0.05s
[0.645s][info   ][gc,start     ] GC(6) Pause Young (Allocation Failure)
[0.716s][info   ][gc,heap      ] GC(6) DefNew: 157247K->17470K(157248K)
[0.716s][info   ][gc,heap      ] GC(6) Tenured: 285444K->334217K(349568K)
[0.716s][info   ][gc,metaspace ] GC(6) Metaspace: 3907K->3907K(1056768K)
[0.716s][info   ][gc           ] GC(6) Pause Young (Allocation Failure) 432M->343M(494M) 71.376ms
[0.716s][info   ][gc,cpu       ] GC(6) User=0.03s Sys=0.02s Real=0.07s
[0.748s][info   ][gc,start     ] GC(7) Pause Young (Allocation Failure)
[0.749s][info   ][gc,start     ] GC(8) Pause Full (Allocation Failure)
[0.749s][info   ][gc,phases,start] GC(8) Phase 1: Mark live objects
[0.752s][info   ][gc,phases      ] GC(8) Phase 1: Mark live objects 2.736ms
[0.752s][info   ][gc,phases,start] GC(8) Phase 2: Compute new object addresses
[0.753s][info   ][gc,phases      ] GC(8) Phase 2: Compute new object addresses 0.999ms
[0.753s][info   ][gc,phases,start] GC(8) Phase 3: Adjust pointers
[0.754s][info   ][gc,phases      ] GC(8) Phase 3: Adjust pointers 0.956ms
[0.754s][info   ][gc,phases,start] GC(8) Phase 4: Move objects
[0.823s][info   ][gc,phases      ] GC(8) Phase 4: Move objects 69.371ms
[0.823s][info   ][gc             ] GC(8) Pause Full (Allocation Failure) 479M->263M(494M) 74.521ms
[0.823s][info   ][gc,heap        ] GC(7) DefNew: 157246K->0K(157248K)
[0.823s][info   ][gc,heap        ] GC(7) Tenured: 334217K->269422K(349568K)
[0.823s][info   ][gc,metaspace   ] GC(7) Metaspace: 3907K->3907K(1056768K)
[0.823s][info   ][gc             ] GC(7) Pause Young (Allocation Failure) 479M->263M(494M) 74.982ms
[0.823s][info   ][gc,cpu         ] GC(7) User=0.07s Sys=0.00s Real=0.07s
[0.852s][info   ][gc,start       ] GC(9) Pause Young (Allocation Failure)
[0.862s][info   ][gc,heap        ] GC(9) DefNew: 139776K->17470K(157248K)
[0.862s][info   ][gc,heap        ] GC(9) Tenured: 269422K->302757K(349568K)
[0.862s][info   ][gc,metaspace   ] GC(9) Metaspace: 3907K->3907K(1056768K)
[0.862s][info   ][gc             ] GC(9) Pause Young (Allocation Failure) 399M->312M(494M) 10.582ms
[0.862s][info   ][gc,cpu         ] GC(9) User=0.01s Sys=0.00s Real=0.01s
[0.886s][info   ][gc,start       ] GC(10) Pause Young (Allocation Failure)
[0.886s][info   ][gc,start       ] GC(11) Pause Full (Allocation Failure)
[0.886s][info   ][gc,phases,start] GC(11) Phase 1: Mark live objects
[0.887s][info   ][gc,phases      ] GC(11) Phase 1: Mark live objects 1.643ms
[0.887s][info   ][gc,phases,start] GC(11) Phase 2: Compute new object addresses
[0.888s][info   ][gc,phases      ] GC(11) Phase 2: Compute new object addresses 0.873ms
[0.888s][info   ][gc,phases,start] GC(11) Phase 3: Adjust pointers
[0.889s][info   ][gc,phases      ] GC(11) Phase 3: Adjust pointers 0.864ms
[0.889s][info   ][gc,phases,start] GC(11) Phase 4: Move objects
[0.939s][info   ][gc,phases      ] GC(11) Phase 4: Move objects 49.931ms
[0.939s][info   ][gc             ] GC(11) Pause Full (Allocation Failure) 449M->288M(494M) 53.647ms
[0.939s][info   ][gc,heap        ] GC(10) DefNew: 157246K->0K(157248K)
[0.939s][info   ][gc,heap        ] GC(10) Tenured: 302757K->295846K(349568K)
[0.939s][info   ][gc,metaspace   ] GC(10) Metaspace: 3907K->3907K(1056768K)
[0.939s][info   ][gc             ] GC(10) Pause Young (Allocation Failure) 449M->288M(494M) 53.766ms
[0.939s][info   ][gc,cpu         ] GC(10) User=0.06s Sys=0.00s Real=0.06s
[0.973s][info   ][gc,start       ] GC(12) Pause Young (Allocation Failure)
[0.983s][info   ][gc,heap        ] GC(12) DefNew: 139578K->17471K(157248K)
[0.983s][info   ][gc,heap        ] GC(12) Tenured: 295846K->329116K(349568K)
[0.983s][info   ][gc,metaspace   ] GC(12) Metaspace: 3907K->3907K(1056768K)
[0.983s][info   ][gc             ] GC(12) Pause Young (Allocation Failure) 425M->338M(494M) 10.334ms
[0.983s][info   ][gc,cpu         ] GC(12) User=0.01s Sys=0.00s Real=0.01s
[1.007s][info   ][gc,start       ] GC(13) Pause Young (Allocation Failure)
[1.007s][info   ][gc,start       ] GC(14) Pause Full (Allocation Failure)
[1.007s][info   ][gc,phases,start] GC(14) Phase 1: Mark live objects
[1.012s][info   ][gc,phases      ] GC(14) Phase 1: Mark live objects 5.290ms
[1.012s][info   ][gc,phases,start] GC(14) Phase 2: Compute new object addresses
[1.014s][info   ][gc,phases      ] GC(14) Phase 2: Compute new object addresses 2.026ms
[1.014s][info   ][gc,phases,start] GC(14) Phase 3: Adjust pointers
[1.017s][info   ][gc,phases      ] GC(14) Phase 3: Adjust pointers 2.600ms
[1.017s][info   ][gc,phases,start] GC(14) Phase 4: Move objects
[1.072s][info   ][gc,phases      ] GC(14) Phase 4: Move objects 55.262ms
[1.073s][info   ][gc             ] GC(14) Pause Full (Allocation Failure) 474M->310M(494M) 65.885ms
[1.073s][info   ][gc,heap        ] GC(13) DefNew: 157247K->0K(157248K)
[1.073s][info   ][gc,heap        ] GC(13) Tenured: 329116K->317654K(349568K)
[1.073s][info   ][gc,metaspace   ] GC(13) Metaspace: 3907K->3907K(1056768K)
[1.073s][info   ][gc             ] GC(13) Pause Young (Allocation Failure) 474M->310M(494M) 66.033ms
[1.073s][info   ][gc,cpu         ] GC(13) User=0.06s Sys=0.00s Real=0.07s
执行结束!共生成对象次数:8300
[1.136s][info   ][gc,heap,exit   ] Heap
[1.136s][info   ][gc,heap,exit   ]  def new generation   total 157248K, used 93302K [0x00000007e0000000, 0x00000007eaaa0000, 0x00000007eaaa0000)
[1.136s][info   ][gc,heap,exit   ]   eden space 139776K,  66% used [0x00000007e0000000, 0x00000007e5b1da30, 0x00000007e8880000)
[1.136s][info   ][gc,heap,exit   ]   from space 17472K,   0% used [0x00000007e9990000, 0x00000007e9990000, 0x00000007eaaa0000)
[1.136s][info   ][gc,heap,exit   ]   to   space 17472K,   0% used [0x00000007e8880000, 0x00000007e8880000, 0x00000007e9990000)
[1.136s][info   ][gc,heap,exit   ]  tenured generation   total 349568K, used 317654K [0x00000007eaaa0000, 0x0000000800000000, 0x0000000800000000)
[1.136s][info   ][gc,heap,exit   ]    the space 349568K,  90% used [0x00000007eaaa0000, 0x00000007fe0d5808, 0x00000007fe0d5a00, 0x0000000800000000)
[1.136s][info   ][gc,heap,exit   ]  Metaspace       used 4894K, capacity 4965K, committed 5120K, reserved 1056768K
[1.136s][info   ][gc,heap,exit   ]   class space    used 407K, capacity 444K, committed 512K, reserved 1048576K
```

#### 截取一次年轻代 GC 的日志如下:

[0.189s][info ][gc,start     ] GC(0) Pause Young (Allocation Failure)

- 发生一次年轻代 GC，原因是内存分配失败

[0.228s][info ][gc,heap      ] GC(0) DefNew: 139776K->17471K(157248K)

- GC 前 eden 内存为 139776K = 136 MB，GC 后为 17471K = 17MB。139776K 为 eden 区的总大小（可以在后面看到），满了触发 GC。
- 年轻代（这里指 eden + 1个s区）的总内存大小为 157248K = 153.56 MB ≈ 512 MB * 30%，因为默认年轻代:老年代为 3:7

[0.228s][info ][gc,heap      ] GC(0) Tenured: 0K->37218K(349568K)


- GC 前老年代为 0MB，GC 后为 37218K = 36MB，说明 36 MB 的对象晋升到了老年代（**这里说 36 MB 都是晋升来的是错误的）

  - **为啥第一次 GC 就会有对象晋升老年代？**

    - 大对象直接在老年代分配。(这里不是这种情况)
    - 本次执行指定的堆内存参数为 -Xms512m -Xmx512m ，eden 区最大为 136 MB，s0 和 s1 都为 17 MB。
    - eden 区达到 136 MB 触发第一次 GC，将存活对象(53 MB)复制到 from 区，**from 区最多能放 17 MB，剩余36 MB 就进入了老年代**。

[0.228s][info ][gc,metaspace ] GC(0) Metaspace: 3907K->3907K(1056768K)

- 元数据区没变化 3907K = 3.8 MB，这个区域主要是已加载对象的信息

[0.228s][info ][gc           ] GC(0) Pause Young (Allocation Failure) 136M->53M(494M) 38.909ms

- GC 总结，只有 -XX:+PrintGC 没有 -XX:+PrintGCDetails 的话只会输出这句
- 年轻代 GC，原因内存分配失败，堆内存从 136 MB 变为 53 MB，耗时 38.9 毫秒

[0.228s][info ][gc,cpu       ] GC(0) User=0.03s Sys=0.01s Real=0.04s

- 用户空间耗时 30 毫秒，内核空间 10 毫秒，总耗时 40 毫秒

-----

#### 内存情况

[1.136s][info ][gc,heap,exit   ] Heap
[1.136s][info ][gc,heap,exit   ]  def new generation   total 157248K, used 93302K [0x00000007e0000000, 0x00000007eaaa0000, 0x00000007eaaa0000)
[1.136s][info ][gc,heap,exit   ]   eden space 139776K,  66% used [0x00000007e0000000, 0x00000007e5b1da30, 0x00000007e8880000)
[1.136s][info ][gc,heap,exit   ]   from space 17472K,   0% used [0x00000007e9990000, 0x00000007e9990000, 0x00000007eaaa0000)
[1.136s][info ][gc,heap,exit   ]   to   space 17472K,   0% used [0x00000007e8880000, 0x00000007e8880000, 0x00000007e9990000)
[1.136s][info ][gc,heap,exit   ]  tenured generation   total 349568K, used 317654K [0x00000007eaaa0000, 0x0000000800000000, 0x0000000800000000)
[1.136s][info ][gc,heap,exit   ]    the space 349568K,  90% used [0x00000007eaaa0000, 0x00000007fe0d5808, 0x00000007fe0d5a00, 0x0000000800000000)

- 堆内存情况
- 年轻代共 139776K = 153MB，已使用 91MB。 这里只计算了 eden 加一个 s 区。
- eden 区 139776K = 136.5 MB，已使用 66%
- from 区 17472K = 17 MB
- to 区 17472K = 17 MB
- 老年代 349568K = 341 MB，已使用 317654K = 310 MB
- 老年代共 349568K = 341 MB，90% 已使用

[1.136s][info ][gc,heap,exit   ]  Metaspace       used 4894K, capacity 4965K, committed 5120K, reserved 1056768K
[1.136s][info ][gc,heap,exit   ]   class space    used 407K, capacity 444K, committed 512K, reserved 1048576K



```bash
➜  src git:(main) ✗ java -XX:+UseParallelGC -Xms512m -Xmx512m -XX:+PrintGCDetails com.loodeer.Main
[0.002s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
[0.011s][info   ][gc] Using Parallel
[0.011s][info   ][gc,heap,coops] Heap address: 0x00000007e0000000, size: 512 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
正在执行...
[0.194s][info   ][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.209s][info   ][gc,heap      ] GC(0) PSYoungGen: 131584K->21502K(153088K)
[0.209s][info   ][gc,heap      ] GC(0) ParOldGen: 0K->22408K(349696K)
[0.209s][info   ][gc,metaspace ] GC(0) Metaspace: 3908K->3908K(1056768K)
[0.209s][info   ][gc           ] GC(0) Pause Young (Allocation Failure) 128M->42M(491M) 15.044ms
[0.209s][info   ][gc,cpu       ] GC(0) User=0.01s Sys=0.02s Real=0.02s
[0.241s][info   ][gc,start     ] GC(1) Pause Young (Allocation Failure)
[0.266s][info   ][gc,heap      ] GC(1) PSYoungGen: 153086K->21503K(153088K)
[0.266s][info   ][gc,heap      ] GC(1) ParOldGen: 22408K->68406K(349696K)
[0.266s][info   ][gc,metaspace ] GC(1) Metaspace: 3908K->3908K(1056768K)
[0.266s][info   ][gc           ] GC(1) Pause Young (Allocation Failure) 171M->87M(491M) 24.926ms
[0.266s][info   ][gc,cpu       ] GC(1) User=0.04s Sys=0.04s Real=0.02s
[0.290s][info   ][gc,start     ] GC(2) Pause Young (Allocation Failure)
[0.312s][info   ][gc,heap      ] GC(2) PSYoungGen: 153087K->21503K(153088K)
[0.312s][info   ][gc,heap      ] GC(2) ParOldGen: 68406K->114907K(349696K)
[0.312s][info   ][gc,metaspace ] GC(2) Metaspace: 3908K->3908K(1056768K)
[0.312s][info   ][gc           ] GC(2) Pause Young (Allocation Failure) 216M->133M(491M) 21.921ms
[0.312s][info   ][gc,cpu       ] GC(2) User=0.03s Sys=0.03s Real=0.02s
[0.330s][info   ][gc,start     ] GC(3) Pause Young (Allocation Failure)
[0.371s][info   ][gc,heap      ] GC(3) PSYoungGen: 153087K->21489K(153088K)
[0.371s][info   ][gc,heap      ] GC(3) ParOldGen: 114907K->160966K(349696K)
[0.371s][info   ][gc,metaspace ] GC(3) Metaspace: 3908K->3908K(1056768K)
[0.371s][info   ][gc           ] GC(3) Pause Young (Allocation Failure) 261M->178M(491M) 40.830ms
[0.371s][info   ][gc,cpu       ] GC(3) User=0.05s Sys=0.04s Real=0.04s
[0.397s][info   ][gc,start     ] GC(4) Pause Young (Allocation Failure)
[0.420s][info   ][gc,heap      ] GC(4) PSYoungGen: 152872K->21501K(153088K)
[0.420s][info   ][gc,heap      ] GC(4) ParOldGen: 160966K->209275K(349696K)
[0.420s][info   ][gc,metaspace ] GC(4) Metaspace: 3908K->3908K(1056768K)
[0.420s][info   ][gc           ] GC(4) Pause Young (Allocation Failure) 306M->225M(491M) 23.728ms
[0.420s][info   ][gc,cpu       ] GC(4) User=0.04s Sys=0.03s Real=0.03s
[0.447s][info   ][gc,start     ] GC(5) Pause Young (Allocation Failure)
[0.471s][info   ][gc,heap      ] GC(5) PSYoungGen: 153085K->21490K(80384K)
[0.471s][info   ][gc,heap      ] GC(5) ParOldGen: 209275K->256193K(349696K)
[0.471s][info   ][gc,metaspace ] GC(5) Metaspace: 3908K->3908K(1056768K)
[0.471s][info   ][gc           ] GC(5) Pause Young (Allocation Failure) 353M->271M(420M) 24.322ms
[0.472s][info   ][gc,cpu       ] GC(5) User=0.04s Sys=0.03s Real=0.03s
[0.486s][info   ][gc,start     ] GC(6) Pause Young (Allocation Failure)
[0.493s][info   ][gc,heap      ] GC(6) PSYoungGen: 80370K->36751K(116736K)
[0.493s][info   ][gc,heap      ] GC(6) ParOldGen: 256193K->261975K(349696K)
[0.493s][info   ][gc,metaspace ] GC(6) Metaspace: 3908K->3908K(1056768K)
[0.493s][info   ][gc           ] GC(6) Pause Young (Allocation Failure) 328M->291M(455M) 7.774ms
[0.493s][info   ][gc,cpu       ] GC(6) User=0.02s Sys=0.00s Real=0.01s
[0.504s][info   ][gc,start     ] GC(7) Pause Young (Allocation Failure)
[0.518s][info   ][gc,heap      ] GC(7) PSYoungGen: 95631K->54378K(116736K)
[0.518s][info   ][gc,heap      ] GC(7) ParOldGen: 261975K->265208K(349696K)
[0.518s][info   ][gc,metaspace ] GC(7) Metaspace: 3908K->3908K(1056768K)
[0.518s][info   ][gc           ] GC(7) Pause Young (Allocation Failure) 349M->312M(455M) 14.029ms
[0.518s][info   ][gc,cpu       ] GC(7) User=0.03s Sys=0.01s Real=0.01s
[0.527s][info   ][gc,start     ] GC(8) Pause Young (Allocation Failure)
[0.543s][info   ][gc,heap      ] GC(8) PSYoungGen: 113258K->57842K(116736K)
[0.543s][info   ][gc,heap      ] GC(8) ParOldGen: 265208K->282833K(349696K)
[0.543s][info   ][gc,metaspace ] GC(8) Metaspace: 3908K->3908K(1056768K)
[0.543s][info   ][gc           ] GC(8) Pause Young (Allocation Failure) 369M->332M(455M) 15.668ms
[0.543s][info   ][gc,cpu       ] GC(8) User=0.03s Sys=0.01s Real=0.02s
[0.543s][info   ][gc,start     ] GC(9) Pause Full (Ergonomics)
[0.543s][info   ][gc,phases,start] GC(9) Marking Phase
[0.549s][info   ][gc,phases      ] GC(9) Marking Phase 5.781ms
[0.549s][info   ][gc,phases,start] GC(9) Summary Phase
[0.549s][info   ][gc,phases      ] GC(9) Summary Phase 0.015ms
[0.549s][info   ][gc,phases,start] GC(9) Adjust Roots
[0.550s][info   ][gc,phases      ] GC(9) Adjust Roots 0.712ms
[0.550s][info   ][gc,phases,start] GC(9) Compaction Phase
[0.588s][info   ][gc,phases      ] GC(9) Compaction Phase 37.922ms
[0.588s][info   ][gc,phases,start] GC(9) Post Compact
[0.590s][info   ][gc,phases      ] GC(9) Post Compact 2.107ms
[0.590s][info   ][gc,heap        ] GC(9) PSYoungGen: 57842K->0K(116736K)
[0.590s][info   ][gc,heap        ] GC(9) ParOldGen: 282833K->239450K(349696K)
[0.590s][info   ][gc,metaspace   ] GC(9) Metaspace: 3908K->3908K(1056768K)
[0.590s][info   ][gc             ] GC(9) Pause Full (Ergonomics) 332M->233M(455M) 46.792ms
[0.590s][info   ][gc,cpu         ] GC(9) User=0.12s Sys=0.01s Real=0.05s
[0.598s][info   ][gc,start       ] GC(10) Pause Young (Allocation Failure)
[0.602s][info   ][gc,heap        ] GC(10) PSYoungGen: 58793K->29365K(116736K)
[0.602s][info   ][gc,heap        ] GC(10) ParOldGen: 239450K->239450K(349696K)
[0.602s][info   ][gc,metaspace   ] GC(10) Metaspace: 3908K->3908K(1056768K)
[0.602s][info   ][gc             ] GC(10) Pause Young (Allocation Failure) 291M->262M(455M) 3.605ms
[0.602s][info   ][gc,cpu         ] GC(10) User=0.02s Sys=0.00s Real=0.01s
[0.610s][info   ][gc,start       ] GC(11) Pause Young (Allocation Failure)
[0.618s][info   ][gc,heap        ] GC(11) PSYoungGen: 88245K->23775K(116736K)
[0.618s][info   ][gc,heap        ] GC(11) ParOldGen: 239450K->266608K(349696K)
[0.618s][info   ][gc,metaspace   ] GC(11) Metaspace: 3908K->3908K(1056768K)
[0.618s][info   ][gc             ] GC(11) Pause Young (Allocation Failure) 320M->283M(455M) 7.962ms
[0.618s][info   ][gc,cpu         ] GC(11) User=0.01s Sys=0.00s Real=0.00s
[0.636s][info   ][gc,start       ] GC(12) Pause Young (Allocation Failure)
[0.644s][info   ][gc,heap        ] GC(12) PSYoungGen: 82655K->21852K(116736K)
[0.644s][info   ][gc,heap        ] GC(12) ParOldGen: 266608K->287927K(349696K)
[0.644s][info   ][gc,metaspace   ] GC(12) Metaspace: 3908K->3908K(1056768K)
[0.645s][info   ][gc             ] GC(12) Pause Young (Allocation Failure) 341M->302M(455M) 8.164ms
[0.645s][info   ][gc,cpu         ] GC(12) User=0.02s Sys=0.00s Real=0.01s
[0.654s][info   ][gc,start       ] GC(13) Pause Young (Allocation Failure)
[0.665s][info   ][gc,heap        ] GC(13) PSYoungGen: 80644K->23065K(116736K)
[0.666s][info   ][gc,heap        ] GC(13) ParOldGen: 287927K->308133K(349696K)
[0.666s][info   ][gc,metaspace   ] GC(13) Metaspace: 3908K->3908K(1056768K)
[0.666s][info   ][gc             ] GC(13) Pause Young (Allocation Failure) 359M->323M(455M) 11.914ms
[0.666s][info   ][gc,cpu         ] GC(13) User=0.02s Sys=0.02s Real=0.01s
[0.666s][info   ][gc,start       ] GC(14) Pause Full (Ergonomics)
[0.666s][info   ][gc,phases,start] GC(14) Marking Phase
[0.669s][info   ][gc,phases      ] GC(14) Marking Phase 3.815ms
[0.669s][info   ][gc,phases,start] GC(14) Summary Phase
[0.670s][info   ][gc,phases      ] GC(14) Summary Phase 0.018ms
[0.670s][info   ][gc,phases,start] GC(14) Adjust Roots
[0.670s][info   ][gc,phases      ] GC(14) Adjust Roots 0.380ms
[0.670s][info   ][gc,phases,start] GC(14) Compaction Phase
[0.709s][info   ][gc,phases      ] GC(14) Compaction Phase 38.740ms
[0.709s][info   ][gc,phases,start] GC(14) Post Compact
[0.709s][info   ][gc,phases      ] GC(14) Post Compact 0.698ms
[0.709s][info   ][gc,heap        ] GC(14) PSYoungGen: 23065K->0K(116736K)
[0.709s][info   ][gc,heap        ] GC(14) ParOldGen: 308133K->267458K(349696K)
[0.709s][info   ][gc,metaspace   ] GC(14) Metaspace: 3908K->3908K(1056768K)
[0.709s][info   ][gc             ] GC(14) Pause Full (Ergonomics) 323M->261M(455M) 43.830ms
[0.709s][info   ][gc,cpu         ] GC(14) User=0.11s Sys=0.00s Real=0.05s
[0.721s][info   ][gc,start       ] GC(15) Pause Young (Allocation Failure)
[0.732s][info   ][gc,heap        ] GC(15) PSYoungGen: 58880K->24739K(116736K)
[0.732s][info   ][gc,heap        ] GC(15) ParOldGen: 267458K->267458K(349696K)
[0.732s][info   ][gc,metaspace   ] GC(15) Metaspace: 3908K->3908K(1056768K)
[0.732s][info   ][gc             ] GC(15) Pause Young (Allocation Failure) 318M->285M(455M) 11.061ms
[0.732s][info   ][gc,cpu         ] GC(15) User=0.03s Sys=0.00s Real=0.01s
[0.743s][info   ][gc,start       ] GC(16) Pause Young (Allocation Failure)
[0.752s][info   ][gc,heap        ] GC(16) PSYoungGen: 83545K->23535K(116736K)
[0.752s][info   ][gc,heap        ] GC(16) ParOldGen: 267458K->290814K(349696K)
[0.752s][info   ][gc,metaspace   ] GC(16) Metaspace: 3908K->3908K(1056768K)
[0.752s][info   ][gc             ] GC(16) Pause Young (Allocation Failure) 342M->306M(455M) 8.759ms
[0.752s][info   ][gc,cpu         ] GC(16) User=0.02s Sys=0.00s Real=0.01s
[0.761s][info   ][gc,start       ] GC(17) Pause Young (Allocation Failure)
[0.769s][info   ][gc,heap        ] GC(17) PSYoungGen: 82415K->23620K(116736K)
[0.769s][info   ][gc,heap        ] GC(17) ParOldGen: 290814K->313441K(349696K)
[0.769s][info   ][gc,metaspace   ] GC(17) Metaspace: 3908K->3908K(1056768K)
[0.769s][info   ][gc             ] GC(17) Pause Young (Allocation Failure) 364M->329M(455M) 7.859ms
[0.769s][info   ][gc,cpu         ] GC(17) User=0.02s Sys=0.00s Real=0.01s
[0.769s][info   ][gc,start       ] GC(18) Pause Full (Ergonomics)
[0.769s][info   ][gc,phases,start] GC(18) Marking Phase
[0.771s][info   ][gc,phases      ] GC(18) Marking Phase 1.579ms
[0.771s][info   ][gc,phases,start] GC(18) Summary Phase
[0.771s][info   ][gc,phases      ] GC(18) Summary Phase 0.012ms
[0.771s][info   ][gc,phases,start] GC(18) Adjust Roots
[0.771s][info   ][gc,phases      ] GC(18) Adjust Roots 0.204ms
[0.771s][info   ][gc,phases,start] GC(18) Compaction Phase
[0.809s][info   ][gc,phases      ] GC(18) Compaction Phase 37.878ms
[0.809s][info   ][gc,phases,start] GC(18) Post Compact
[0.810s][info   ][gc,phases      ] GC(18) Post Compact 0.802ms
[0.810s][info   ][gc,heap        ] GC(18) PSYoungGen: 23620K->0K(116736K)
[0.810s][info   ][gc,heap        ] GC(18) ParOldGen: 313441K->284410K(349696K)
[0.810s][info   ][gc,metaspace   ] GC(18) Metaspace: 3908K->3908K(1056768K)
[0.810s][info   ][gc             ] GC(18) Pause Full (Ergonomics) 329M->277M(455M) 40.654ms
[0.810s][info   ][gc,cpu         ] GC(18) User=0.10s Sys=0.01s Real=0.04s
[0.821s][info   ][gc,start       ] GC(19) Pause Young (Allocation Failure)
[0.824s][info   ][gc,heap        ] GC(19) PSYoungGen: 58880K->20375K(116736K)
[0.824s][info   ][gc,heap        ] GC(19) ParOldGen: 284410K->284410K(349696K)
[0.824s][info   ][gc,metaspace   ] GC(19) Metaspace: 3908K->3908K(1056768K)
[0.824s][info   ][gc             ] GC(19) Pause Young (Allocation Failure) 335M->297M(455M) 2.632ms
[0.824s][info   ][gc,cpu         ] GC(19) User=0.01s Sys=0.00s Real=0.00s
[0.832s][info   ][gc,start       ] GC(20) Pause Young (Allocation Failure)
[0.844s][info   ][gc,heap        ] GC(20) PSYoungGen: 79255K->24581K(116736K)
[0.844s][info   ][gc,heap        ] GC(20) ParOldGen: 284410K->303299K(349696K)
[0.844s][info   ][gc,metaspace   ] GC(20) Metaspace: 3908K->3908K(1056768K)
[0.844s][info   ][gc             ] GC(20) Pause Young (Allocation Failure) 355M->320M(455M) 12.488ms
[0.844s][info   ][gc,cpu         ] GC(20) User=0.04s Sys=0.00s Real=0.01s
[0.856s][info   ][gc,start       ] GC(21) Pause Young (Allocation Failure)
[0.867s][info   ][gc,heap        ] GC(21) PSYoungGen: 83461K->22821K(116736K)
[0.867s][info   ][gc,heap        ] GC(21) ParOldGen: 303299K->325295K(349696K)
[0.867s][info   ][gc,metaspace   ] GC(21) Metaspace: 3908K->3908K(1056768K)
[0.867s][info   ][gc             ] GC(21) Pause Young (Allocation Failure) 377M->339M(455M) 11.104ms
[0.867s][info   ][gc,cpu         ] GC(21) User=0.02s Sys=0.01s Real=0.01s
[0.867s][info   ][gc,start       ] GC(22) Pause Full (Ergonomics)
[0.867s][info   ][gc,phases,start] GC(22) Marking Phase
[0.870s][info   ][gc,phases      ] GC(22) Marking Phase 2.336ms
[0.870s][info   ][gc,phases,start] GC(22) Summary Phase
[0.870s][info   ][gc,phases      ] GC(22) Summary Phase 0.018ms
[0.870s][info   ][gc,phases,start] GC(22) Adjust Roots
[0.870s][info   ][gc,phases      ] GC(22) Adjust Roots 0.614ms
[0.870s][info   ][gc,phases,start] GC(22) Compaction Phase
[0.910s][info   ][gc,phases      ] GC(22) Compaction Phase 39.504ms
[0.910s][info   ][gc,phases,start] GC(22) Post Compact
[0.910s][info   ][gc,phases      ] GC(22) Post Compact 0.693ms
[0.910s][info   ][gc,heap        ] GC(22) PSYoungGen: 22821K->0K(116736K)
[0.910s][info   ][gc,heap        ] GC(22) ParOldGen: 325295K->293583K(349696K)
[0.910s][info   ][gc,metaspace   ] GC(22) Metaspace: 3908K->3908K(1056768K)
[0.910s][info   ][gc             ] GC(22) Pause Full (Ergonomics) 339M->286M(455M) 43.326ms
[0.910s][info   ][gc,cpu         ] GC(22) User=0.11s Sys=0.00s Real=0.05s
[0.926s][info   ][gc,start       ] GC(23) Pause Young (Allocation Failure)
[0.932s][info   ][gc,heap        ] GC(23) PSYoungGen: 58721K->24186K(116736K)
[0.932s][info   ][gc,heap        ] GC(23) ParOldGen: 293583K->293583K(349696K)
[0.932s][info   ][gc,metaspace   ] GC(23) Metaspace: 3908K->3908K(1056768K)
[0.932s][info   ][gc             ] GC(23) Pause Young (Allocation Failure) 344M->310M(455M) 6.196ms
[0.932s][info   ][gc,cpu         ] GC(23) User=0.01s Sys=0.00s Real=0.01s
[0.945s][info   ][gc,start       ] GC(24) Pause Young (Allocation Failure)
[0.953s][info   ][gc,heap        ] GC(24) PSYoungGen: 82887K->23416K(116736K)
[0.953s][info   ][gc,heap        ] GC(24) ParOldGen: 293583K->316060K(349696K)
[0.953s][info   ][gc,metaspace   ] GC(24) Metaspace: 3908K->3908K(1056768K)
[0.953s][info   ][gc             ] GC(24) Pause Young (Allocation Failure) 367M->331M(455M) 7.618ms
[0.953s][info   ][gc,cpu         ] GC(24) User=0.02s Sys=0.00s Real=0.01s
[0.953s][info   ][gc,start       ] GC(25) Pause Full (Ergonomics)
[0.953s][info   ][gc,phases,start] GC(25) Marking Phase
[0.955s][info   ][gc,phases      ] GC(25) Marking Phase 2.009ms
[0.955s][info   ][gc,phases,start] GC(25) Summary Phase
[0.955s][info   ][gc,phases      ] GC(25) Summary Phase 0.024ms
[0.955s][info   ][gc,phases,start] GC(25) Adjust Roots
[0.956s][info   ][gc,phases      ] GC(25) Adjust Roots 0.537ms
[0.956s][info   ][gc,phases,start] GC(25) Compaction Phase
[0.993s][info   ][gc,phases      ] GC(25) Compaction Phase 37.093ms
[0.993s][info   ][gc,phases,start] GC(25) Post Compact
[0.994s][info   ][gc,phases      ] GC(25) Post Compact 0.627ms
[0.994s][info   ][gc,heap        ] GC(25) PSYoungGen: 23416K->0K(116736K)
[0.994s][info   ][gc,heap        ] GC(25) ParOldGen: 316060K->303377K(349696K)
[0.994s][info   ][gc,metaspace   ] GC(25) Metaspace: 3908K->3908K(1056768K)
[0.994s][info   ][gc             ] GC(25) Pause Full (Ergonomics) 331M->296M(455M) 40.478ms
[0.994s][info   ][gc,cpu         ] GC(25) User=0.12s Sys=0.00s Real=0.04s
[1.017s][info   ][gc,start       ] GC(26) Pause Young (Allocation Failure)
[1.020s][info   ][gc,heap        ] GC(26) PSYoungGen: 58880K->25683K(116736K)
[1.020s][info   ][gc,heap        ] GC(26) ParOldGen: 303377K->303377K(349696K)
[1.020s][info   ][gc,metaspace   ] GC(26) Metaspace: 3908K->3908K(1056768K)
[1.020s][info   ][gc             ] GC(26) Pause Young (Allocation Failure) 353M->321M(455M) 3.125ms
[1.020s][info   ][gc,cpu         ] GC(26) User=0.01s Sys=0.00s Real=0.01s
[1.028s][info   ][gc,start       ] GC(27) Pause Young (Allocation Failure)
[1.035s][info   ][gc,heap        ] GC(27) PSYoungGen: 84080K->21288K(116736K)
[1.035s][info   ][gc,heap        ] GC(27) ParOldGen: 303377K->327170K(349696K)
[1.035s][info   ][gc,metaspace   ] GC(27) Metaspace: 3908K->3908K(1056768K)
[1.036s][info   ][gc             ] GC(27) Pause Young (Allocation Failure) 378M->340M(455M) 7.358ms
[1.036s][info   ][gc,cpu         ] GC(27) User=0.02s Sys=0.01s Real=0.01s
[1.036s][info   ][gc,start       ] GC(28) Pause Full (Ergonomics)
[1.036s][info   ][gc,phases,start] GC(28) Marking Phase
[1.038s][info   ][gc,phases      ] GC(28) Marking Phase 2.028ms
[1.038s][info   ][gc,phases,start] GC(28) Summary Phase
[1.038s][info   ][gc,phases      ] GC(28) Summary Phase 0.013ms
[1.038s][info   ][gc,phases,start] GC(28) Adjust Roots
[1.038s][info   ][gc,phases      ] GC(28) Adjust Roots 0.628ms
[1.038s][info   ][gc,phases,start] GC(28) Compaction Phase
[1.076s][info   ][gc,phases      ] GC(28) Compaction Phase 37.712ms
[1.076s][info   ][gc,phases,start] GC(28) Post Compact
[1.077s][info   ][gc,phases      ] GC(28) Post Compact 1.001ms
[1.077s][info   ][gc,heap        ] GC(28) PSYoungGen: 21288K->0K(116736K)
[1.077s][info   ][gc,heap        ] GC(28) ParOldGen: 327170K->305593K(349696K)
[1.077s][info   ][gc,metaspace   ] GC(28) Metaspace: 3908K->3908K(1056768K)
[1.077s][info   ][gc             ] GC(28) Pause Full (Ergonomics) 340M->298M(455M) 41.621ms
[1.077s][info   ][gc,cpu         ] GC(28) User=0.11s Sys=0.00s Real=0.04s
[1.086s][info   ][gc,start       ] GC(29) Pause Young (Allocation Failure)
[1.090s][info   ][gc,heap        ] GC(29) PSYoungGen: 58560K->25362K(116736K)
[1.090s][info   ][gc,heap        ] GC(29) ParOldGen: 305593K->305593K(349696K)
[1.090s][info   ][gc,metaspace   ] GC(29) Metaspace: 3908K->3908K(1056768K)
[1.090s][info   ][gc             ] GC(29) Pause Young (Allocation Failure) 355M->323M(455M) 4.096ms
[1.090s][info   ][gc,cpu         ] GC(29) User=0.02s Sys=0.00s Real=0.01s
执行结束!共生成对象次数:8782
[1.091s][info   ][gc,heap,exit   ] Heap
[1.091s][info   ][gc,heap,exit   ]  PSYoungGen      total 116736K, used 28295K [0x00000007f5580000, 0x0000000800000000, 0x0000000800000000)
[1.091s][info   ][gc,heap,exit   ]   eden space 58880K, 4% used [0x00000007f5580000,0x00000007f585d5f0,0x00000007f8f00000)
[1.091s][info   ][gc,heap,exit   ]   from space 57856K, 43% used [0x00000007fc780000,0x00000007fe0449d8,0x0000000800000000)
[1.091s][info   ][gc,heap,exit   ]   to   space 57856K, 0% used [0x00000007f8f00000,0x00000007f8f00000,0x00000007fc780000)
[1.091s][info   ][gc,heap,exit   ]  ParOldGen       total 349696K, used 305593K [0x00000007e0000000, 0x00000007f5580000, 0x00000007f5580000)
[1.091s][info   ][gc,heap,exit   ]   object space 349696K, 87% used [0x00000007e0000000,0x00000007f2a6e4e0,0x00000007f5580000)
[1.091s][info   ][gc,heap,exit   ]  Metaspace       used 3918K, capacity 4490K, committed 4864K, reserved 1056768K
[1.091s][info   ][gc,heap,exit   ]   class space    used 334K, capacity 386K, committed 512K, reserved 1048576K
```

```bash
➜  src git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -XX:+PrintGCDetails com.loodeer.Main
Java HotSpot(TM) 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
[0.002s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
[0.017s][info   ][gc] Using Concurrent Mark Sweep
[0.017s][info   ][gc,heap,coops] Heap address: 0x00000007e0000000, size: 512 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
正在执行...
[0.205s][info   ][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.205s][info   ][gc,task      ] GC(0) Using 4 workers of 4 for evacuation
[0.224s][info   ][gc,heap      ] GC(0) ParNew: 139776K->17471K(157248K)
[0.224s][info   ][gc,heap      ] GC(0) CMS: 0K->39163K(349568K)
[0.224s][info   ][gc,metaspace ] GC(0) Metaspace: 3908K->3908K(1056768K)
[0.224s][info   ][gc           ] GC(0) Pause Young (Allocation Failure) 136M->55M(494M) 19.129ms
[0.224s][info   ][gc,cpu       ] GC(0) User=0.03s Sys=0.03s Real=0.02s
[0.247s][info   ][gc,start     ] GC(1) Pause Young (Allocation Failure)
[0.247s][info   ][gc,task      ] GC(1) Using 4 workers of 4 for evacuation
[0.284s][info   ][gc,heap      ] GC(1) ParNew: 157247K->17472K(157248K)
[0.284s][info   ][gc,heap      ] GC(1) CMS: 39163K->86527K(349568K)
[0.284s][info   ][gc,metaspace ] GC(1) Metaspace: 3908K->3908K(1056768K)
[0.284s][info   ][gc           ] GC(1) Pause Young (Allocation Failure) 191M->101M(494M) 36.821ms
[0.284s][info   ][gc,cpu       ] GC(1) User=0.04s Sys=0.05s Real=0.04s
[0.302s][info   ][gc,start     ] GC(2) Pause Young (Allocation Failure)
[0.302s][info   ][gc,task      ] GC(2) Using 4 workers of 4 for evacuation
[0.347s][info   ][gc,heap      ] GC(2) ParNew: 157178K->17472K(157248K)
[0.347s][info   ][gc,heap      ] GC(2) CMS: 86527K->138923K(349568K)
[0.347s][info   ][gc,metaspace ] GC(2) Metaspace: 3908K->3908K(1056768K)
[0.347s][info   ][gc           ] GC(2) Pause Young (Allocation Failure) 237M->152M(494M) 45.335ms
[0.347s][info   ][gc,cpu       ] GC(2) User=0.12s Sys=0.02s Real=0.05s
[0.374s][info   ][gc,start     ] GC(3) Pause Young (Allocation Failure)
[0.374s][info   ][gc,task      ] GC(3) Using 4 workers of 4 for evacuation
[0.417s][info   ][gc,heap      ] GC(3) ParNew: 157248K->17470K(157248K)
[0.417s][info   ][gc,heap      ] GC(3) CMS: 138923K->191092K(349568K)
[0.417s][info   ][gc,metaspace ] GC(3) Metaspace: 3908K->3908K(1056768K)
[0.417s][info   ][gc           ] GC(3) Pause Young (Allocation Failure) 289M->203M(494M) 43.829ms
[0.417s][info   ][gc,cpu       ] GC(3) User=0.13s Sys=0.02s Real=0.04s
[0.417s][info   ][gc,start     ] GC(4) Pause Initial Mark
[0.418s][info   ][gc           ] GC(4) Pause Initial Mark 206M->206M(494M) 0.145ms
[0.418s][info   ][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.00s
[0.418s][info   ][gc           ] GC(4) Concurrent Mark
[0.423s][info   ][gc           ] GC(4) Concurrent Mark 4.963ms
[0.423s][info   ][gc,cpu       ] GC(4) User=0.01s Sys=0.01s Real=0.00s
[0.423s][info   ][gc           ] GC(4) Concurrent Preclean
[0.424s][info   ][gc           ] GC(4) Concurrent Preclean 0.846ms
[0.424s][info   ][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.01s
[0.424s][info   ][gc           ] GC(4) Concurrent Abortable Preclean
[0.446s][info   ][gc,start     ] GC(5) Pause Young (Allocation Failure)
[0.446s][info   ][gc,task      ] GC(5) Using 4 workers of 4 for evacuation
[0.492s][info   ][gc,heap      ] GC(5) ParNew: 157246K->17471K(157248K)
[0.492s][info   ][gc,heap      ] GC(5) CMS: 191092K->241673K(349568K)
[0.492s][info   ][gc,metaspace ] GC(5) Metaspace: 3908K->3908K(1056768K)
[0.492s][info   ][gc           ] GC(5) Pause Young (Allocation Failure) 340M->253M(494M) 45.980ms
[0.492s][info   ][gc,cpu       ] GC(5) User=0.12s Sys=0.02s Real=0.04s
[0.511s][info   ][gc,start     ] GC(6) Pause Young (Allocation Failure)
[0.511s][info   ][gc,task      ] GC(6) Using 4 workers of 4 for evacuation
[0.551s][info   ][gc,heap      ] GC(6) ParNew: 157247K->17472K(157248K)
[0.551s][info   ][gc,heap      ] GC(6) CMS: 241673K->294075K(349568K)
[0.551s][info   ][gc,metaspace ] GC(6) Metaspace: 3908K->3908K(1056768K)
[0.551s][info   ][gc           ] GC(6) Pause Young (Allocation Failure) 389M->304M(494M) 40.229ms
[0.551s][info   ][gc,cpu       ] GC(6) User=0.13s Sys=0.02s Real=0.04s
[0.572s][info   ][gc,start     ] GC(7) Pause Young (Allocation Failure)
[0.572s][info   ][gc,task      ] GC(7) Using 4 workers of 4 for evacuation
[0.617s][info   ][gc,heap      ] GC(7) ParNew: 157248K->17472K(157248K)
[0.617s][info   ][gc,heap      ] GC(7) CMS: 294075K->342646K(349568K)
[0.617s][info   ][gc,metaspace ] GC(7) Metaspace: 3908K->3908K(1056768K)
[0.617s][info   ][gc           ] GC(7) Pause Young (Allocation Failure) 440M->351M(494M) 44.895ms
[0.617s][info   ][gc,cpu       ] GC(7) User=0.09s Sys=0.02s Real=0.05s
[0.617s][info   ][gc           ] GC(4) Concurrent Abortable Preclean 193.601ms
[0.617s][info   ][gc,cpu       ] GC(4) User=0.40s Sys=0.06s Real=0.19s
[0.617s][info   ][gc,start     ] GC(4) Pause Remark
[0.619s][info   ][gc           ] GC(4) Pause Remark 354M->354M(494M) 1.854ms
[0.619s][info   ][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.00s
[0.619s][info   ][gc           ] GC(4) Concurrent Sweep
[0.620s][info   ][gc           ] GC(4) Concurrent Sweep 0.773ms
[0.620s][info   ][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.00s
[0.620s][info   ][gc           ] GC(4) Concurrent Reset
[0.620s][info   ][gc           ] GC(4) Concurrent Reset 0.373ms
[0.620s][info   ][gc,cpu       ] GC(4) User=0.00s Sys=0.00s Real=0.00s
[0.620s][info   ][gc,heap      ] GC(4) Old: 191092K->305875K(349568K)
[0.646s][info   ][gc,start     ] GC(8) Pause Young (Allocation Failure)
[0.646s][info   ][gc,task      ] GC(8) Using 4 workers of 4 for evacuation
[0.646s][info   ][gc,start     ] GC(9) Pause Full (Allocation Failure)
[0.646s][info   ][gc,phases,start] GC(9) Phase 1: Mark live objects
[0.647s][info   ][gc,phases      ] GC(9) Phase 1: Mark live objects 1.334ms
[0.647s][info   ][gc,phases,start] GC(9) Phase 2: Compute new object addresses
[0.648s][info   ][gc,phases      ] GC(9) Phase 2: Compute new object addresses 0.864ms
[0.648s][info   ][gc,phases,start] GC(9) Phase 3: Adjust pointers
[0.649s][info   ][gc,phases      ] GC(9) Phase 3: Adjust pointers 0.845ms
[0.649s][info   ][gc,phases,start] GC(9) Phase 4: Move objects
[0.694s][info   ][gc,phases      ] GC(9) Phase 4: Move objects 44.976ms
[0.695s][info   ][gc             ] GC(9) Pause Full (Allocation Failure) 452M->252M(494M) 48.654ms
[0.695s][info   ][gc,heap        ] GC(8) ParNew: 157248K->0K(157248K)
[0.695s][info   ][gc,heap        ] GC(8) CMS: 305875K->259042K(349568K)
[0.695s][info   ][gc,metaspace   ] GC(8) Metaspace: 3908K->3908K(1056768K)
[0.695s][info   ][gc             ] GC(8) Pause Young (Allocation Failure) 452M->252M(494M) 48.784ms
[0.695s][info   ][gc,cpu         ] GC(8) User=0.05s Sys=0.00s Real=0.05s
[0.695s][info   ][gc,start       ] GC(10) Pause Initial Mark
[0.695s][info   ][gc             ] GC(10) Pause Initial Mark 253M->253M(494M) 0.118ms
[0.695s][info   ][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
[0.695s][info   ][gc             ] GC(10) Concurrent Mark
[0.699s][info   ][gc             ] GC(10) Concurrent Mark 3.799ms
[0.699s][info   ][gc,cpu         ] GC(10) User=0.01s Sys=0.00s Real=0.00s
[0.699s][info   ][gc             ] GC(10) Concurrent Preclean
[0.700s][info   ][gc             ] GC(10) Concurrent Preclean 0.846ms
[0.700s][info   ][gc,cpu         ] GC(10) User=0.00s Sys=0.00s Real=0.00s
[0.700s][info   ][gc             ] GC(10) Concurrent Abortable Preclean
[0.726s][info   ][gc,start       ] GC(11) Pause Young (Allocation Failure)
[0.726s][info   ][gc,task        ] GC(11) Using 4 workers of 4 for evacuation
[0.735s][info   ][gc,heap        ] GC(11) ParNew: 139776K->17472K(157248K)
[0.735s][info   ][gc,heap        ] GC(11) CMS: 259042K->295714K(349568K)
[0.735s][info   ][gc,metaspace   ] GC(11) Metaspace: 3908K->3908K(1056768K)
[0.735s][info   ][gc             ] GC(11) Pause Young (Allocation Failure) 389M->305M(494M) 9.178ms
[0.735s][info   ][gc,cpu         ] GC(11) User=0.02s Sys=0.00s Real=0.01s
[0.759s][info   ][gc,start       ] GC(12) Pause Young (Allocation Failure)
[0.759s][info   ][gc,task        ] GC(12) Using 4 workers of 4 for evacuation
[0.781s][info   ][gc,promotion   ] Promotion failed
[0.781s][info   ][gc,start       ] GC(13) Pause Full (Allocation Failure)
[0.781s][info   ][gc             ] GC(10) Concurrent Abortable Preclean 81.485ms
[0.781s][info   ][gc,cpu         ] GC(10) User=0.14s Sys=0.01s Real=0.08s
[0.781s][info   ][gc,phases,start] GC(13) Phase 1: Mark live objects
[0.783s][info   ][gc,phases      ] GC(13) Phase 1: Mark live objects 1.516ms
[0.783s][info   ][gc,phases,start] GC(13) Phase 2: Compute new object addresses
[0.784s][info   ][gc,phases      ] GC(13) Phase 2: Compute new object addresses 1.008ms
[0.784s][info   ][gc,phases,start] GC(13) Phase 3: Adjust pointers
[0.785s][info   ][gc,phases      ] GC(13) Phase 3: Adjust pointers 0.864ms
[0.785s][info   ][gc,phases,start] GC(13) Phase 4: Move objects
[0.827s][info   ][gc,phases      ] GC(13) Phase 4: Move objects 42.285ms
[0.828s][info   ][gc             ] GC(13) Pause Full (Allocation Failure) 494M->277M(494M) 46.296ms
[0.828s][info   ][gc,heap        ] GC(12) ParNew: 157248K->0K(157248K)
[0.828s][info   ][gc,heap        ] GC(12) CMS: 295714K->284422K(349568K)
[0.828s][info   ][gc,metaspace   ] GC(12) Metaspace: 3908K->3908K(1056768K)
[0.828s][info   ][gc             ] GC(12) Pause Young (Allocation Failure) 442M->277M(494M) 68.172ms
[0.828s][info   ][gc,cpu         ] GC(12) User=0.11s Sys=0.01s Real=0.07s
[0.855s][info   ][gc,start       ] GC(14) Pause Young (Allocation Failure)
[0.855s][info   ][gc,task        ] GC(14) Using 4 workers of 4 for evacuation
[0.865s][info   ][gc,heap        ] GC(14) ParNew: 139776K->17472K(157248K)
[0.865s][info   ][gc,heap        ] GC(14) CMS: 284422K->324969K(349568K)
[0.865s][info   ][gc,metaspace   ] GC(14) Metaspace: 3908K->3908K(1056768K)
[0.865s][info   ][gc             ] GC(14) Pause Young (Allocation Failure) 414M->334M(494M) 9.416ms
[0.865s][info   ][gc,cpu         ] GC(14) User=0.03s Sys=0.00s Real=0.01s
[0.865s][info   ][gc,start       ] GC(15) Pause Initial Mark
[0.865s][info   ][gc             ] GC(15) Pause Initial Mark 335M->335M(494M) 0.091ms
[0.865s][info   ][gc,cpu         ] GC(15) User=0.00s Sys=0.00s Real=0.00s
[0.865s][info   ][gc             ] GC(15) Concurrent Mark
[0.868s][info   ][gc             ] GC(15) Concurrent Mark 3.030ms
[0.868s][info   ][gc,cpu         ] GC(15) User=0.00s Sys=0.00s Real=0.00s
[0.868s][info   ][gc             ] GC(15) Concurrent Preclean
[0.868s][info   ][gc             ] GC(15) Concurrent Preclean 0.471ms
[0.868s][info   ][gc,cpu         ] GC(15) User=0.00s Sys=0.00s Real=0.00s
[0.868s][info   ][gc             ] GC(15) Concurrent Abortable Preclean
[0.868s][info   ][gc             ] GC(15) Concurrent Abortable Preclean 0.007ms
[0.868s][info   ][gc,cpu         ] GC(15) User=0.00s Sys=0.00s Real=0.00s
[0.869s][info   ][gc,start       ] GC(15) Pause Remark
[0.870s][info   ][gc             ] GC(15) Pause Remark 348M->348M(494M) 1.867ms
[0.870s][info   ][gc,cpu         ] GC(15) User=0.01s Sys=0.00s Real=0.00s
[0.870s][info   ][gc             ] GC(15) Concurrent Sweep
[0.871s][info   ][gc             ] GC(15) Concurrent Sweep 0.738ms
[0.871s][info   ][gc,cpu         ] GC(15) User=0.00s Sys=0.00s Real=0.00s
[0.871s][info   ][gc             ] GC(15) Concurrent Reset
[0.887s][info   ][gc             ] GC(15) Concurrent Reset 15.674ms
[0.887s][info   ][gc,cpu         ] GC(15) User=0.02s Sys=0.00s Real=0.02s
[0.887s][info   ][gc,heap        ] GC(15) Old: 324969K->279928K(349568K)
[0.890s][info   ][gc,start       ] GC(16) Pause Young (Allocation Failure)
[0.890s][info   ][gc,task        ] GC(16) Using 4 workers of 4 for evacuation
[0.909s][info   ][gc,heap        ] GC(16) ParNew: 157248K->17472K(157248K)
[0.909s][info   ][gc,heap        ] GC(16) CMS: 279928K->332078K(349568K)
[0.909s][info   ][gc,metaspace   ] GC(16) Metaspace: 3908K->3908K(1056768K)
[0.909s][info   ][gc             ] GC(16) Pause Young (Allocation Failure) 426M->341M(494M) 19.308ms
[0.909s][info   ][gc,cpu         ] GC(16) User=0.04s Sys=0.00s Real=0.02s
[0.910s][info   ][gc,start       ] GC(17) Pause Initial Mark
[0.910s][info   ][gc             ] GC(17) Pause Initial Mark 341M->341M(494M) 0.168ms
[0.910s][info   ][gc,cpu         ] GC(17) User=0.00s Sys=0.00s Real=0.00s
[0.910s][info   ][gc             ] GC(17) Concurrent Mark
[0.914s][info   ][gc             ] GC(17) Concurrent Mark 3.980ms
[0.914s][info   ][gc,cpu         ] GC(17) User=0.01s Sys=0.00s Real=0.01s
[0.914s][info   ][gc             ] GC(17) Concurrent Preclean
[0.915s][info   ][gc             ] GC(17) Concurrent Preclean 0.752ms
[0.915s][info   ][gc,cpu         ] GC(17) User=0.00s Sys=0.00s Real=0.00s
[0.915s][info   ][gc             ] GC(17) Concurrent Abortable Preclean
[0.915s][info   ][gc             ] GC(17) Concurrent Abortable Preclean 0.012ms
[0.915s][info   ][gc,cpu         ] GC(17) User=0.00s Sys=0.00s Real=0.00s
[0.915s][info   ][gc,start       ] GC(17) Pause Remark
[0.917s][info   ][gc             ] GC(17) Pause Remark 360M->360M(494M) 2.379ms
[0.917s][info   ][gc,cpu         ] GC(17) User=0.00s Sys=0.00s Real=0.00s
[0.918s][info   ][gc             ] GC(17) Concurrent Sweep
[0.919s][info   ][gc             ] GC(17) Concurrent Sweep 1.248ms
[0.919s][info   ][gc,cpu         ] GC(17) User=0.01s Sys=0.00s Real=0.00s
[0.919s][info   ][gc             ] GC(17) Concurrent Reset
[0.925s][info   ][gc             ] GC(17) Concurrent Reset 6.130ms
[0.925s][info   ][gc,cpu         ] GC(17) User=0.00s Sys=0.00s Real=0.01s
[0.925s][info   ][gc,heap        ] GC(17) Old: 332078K->289284K(349568K)
[0.944s][info   ][gc,start       ] GC(18) Pause Young (Allocation Failure)
[0.944s][info   ][gc,task        ] GC(18) Using 4 workers of 4 for evacuation
[0.944s][info   ][gc,start       ] GC(19) Pause Full (Allocation Failure)
[0.944s][info   ][gc,phases,start] GC(19) Phase 1: Mark live objects
[0.946s][info   ][gc,phases      ] GC(19) Phase 1: Mark live objects 1.561ms
[0.946s][info   ][gc,phases,start] GC(19) Phase 2: Compute new object addresses
[0.947s][info   ][gc,phases      ] GC(19) Phase 2: Compute new object addresses 0.901ms
[0.947s][info   ][gc,phases,start] GC(19) Phase 3: Adjust pointers
[0.948s][info   ][gc,phases      ] GC(19) Phase 3: Adjust pointers 0.871ms
[0.948s][info   ][gc,phases,start] GC(19) Phase 4: Move objects
[0.994s][info   ][gc,phases      ] GC(19) Phase 4: Move objects 45.868ms
[0.994s][info   ][gc             ] GC(19) Pause Full (Allocation Failure) 436M->298M(494M) 49.861ms
[0.994s][info   ][gc,heap        ] GC(18) ParNew: 157248K->0K(157248K)
[0.994s][info   ][gc,heap        ] GC(18) CMS: 289284K->305237K(349568K)
[0.994s][info   ][gc,metaspace   ] GC(18) Metaspace: 3908K->3908K(1056768K)
[0.994s][info   ][gc             ] GC(18) Pause Young (Allocation Failure) 436M->298M(494M) 49.981ms
[0.994s][info   ][gc,cpu         ] GC(18) User=0.05s Sys=0.00s Real=0.05s
[0.994s][info   ][gc,start       ] GC(20) Pause Initial Mark
[0.994s][info   ][gc             ] GC(20) Pause Initial Mark 298M->298M(494M) 0.114ms
[0.994s][info   ][gc,cpu         ] GC(20) User=0.00s Sys=0.00s Real=0.00s
[0.995s][info   ][gc             ] GC(20) Concurrent Mark
[0.999s][info   ][gc             ] GC(20) Concurrent Mark 4.831ms
[0.999s][info   ][gc,cpu         ] GC(20) User=0.01s Sys=0.00s Real=0.00s
[0.999s][info   ][gc             ] GC(20) Concurrent Preclean
[1.000s][info   ][gc             ] GC(20) Concurrent Preclean 0.897ms
[1.000s][info   ][gc,cpu         ] GC(20) User=0.00s Sys=0.00s Real=0.00s
[1.000s][info   ][gc             ] GC(20) Concurrent Abortable Preclean
[1.024s][info   ][gc,start       ] GC(21) Pause Young (Allocation Failure)
[1.024s][info   ][gc,task        ] GC(21) Using 4 workers of 4 for evacuation
[1.033s][info   ][gc,heap        ] GC(21) ParNew: 139776K->17472K(157248K)
[1.033s][info   ][gc,heap        ] GC(21) CMS: 305237K->341073K(349568K)
[1.033s][info   ][gc,metaspace   ] GC(21) Metaspace: 3908K->3908K(1056768K)
[1.033s][info   ][gc             ] GC(21) Pause Young (Allocation Failure) 434M->350M(494M) 8.460ms
[1.033s][info   ][gc,cpu         ] GC(21) User=0.02s Sys=0.00s Real=0.00s
[1.033s][info   ][gc             ] GC(20) Concurrent Abortable Preclean 32.324ms
[1.033s][info   ][gc,cpu         ] GC(20) User=0.04s Sys=0.00s Real=0.03s
[1.033s][info   ][gc,start       ] GC(20) Pause Remark
[1.035s][info   ][gc             ] GC(20) Pause Remark 353M->353M(494M) 1.917ms
[1.035s][info   ][gc,cpu         ] GC(20) User=0.01s Sys=0.00s Real=0.01s
[1.035s][info   ][gc             ] GC(20) Concurrent Sweep
[1.036s][info   ][gc             ] GC(20) Concurrent Sweep 0.811ms
[1.036s][info   ][gc,cpu         ] GC(20) User=0.00s Sys=0.00s Real=0.00s
[1.036s][info   ][gc             ] GC(20) Concurrent Reset
[1.036s][info   ][gc             ] GC(20) Concurrent Reset 0.587ms
[1.036s][info   ][gc,cpu         ] GC(20) User=0.00s Sys=0.00s Real=0.00s
[1.036s][info   ][gc,heap        ] GC(20) Old: 305237K->340313K(349568K)
[1.061s][info   ][gc,start       ] GC(22) Pause Young (Allocation Failure)
[1.061s][info   ][gc,task        ] GC(22) Using 4 workers of 4 for evacuation
[1.061s][info   ][gc,start       ] GC(23) Pause Full (Allocation Failure)
[1.061s][info   ][gc,phases,start] GC(23) Phase 1: Mark live objects
[1.063s][info   ][gc,phases      ] GC(23) Phase 1: Mark live objects 2.410ms
[1.063s][info   ][gc,phases,start] GC(23) Phase 2: Compute new object addresses
[1.065s][info   ][gc,phases      ] GC(23) Phase 2: Compute new object addresses 1.850ms
[1.065s][info   ][gc,phases,start] GC(23) Phase 3: Adjust pointers
[1.067s][info   ][gc,phases      ] GC(23) Phase 3: Adjust pointers 1.470ms
[1.067s][info   ][gc,phases,start] GC(23) Phase 4: Move objects
[1.122s][info   ][gc,phases      ] GC(23) Phase 4: Move objects 55.136ms
[1.122s][info   ][gc             ] GC(23) Pause Full (Allocation Failure) 485M->315M(494M) 61.724ms
[1.122s][info   ][gc,heap        ] GC(22) ParNew: 157248K->0K(157248K)
[1.122s][info   ][gc,heap        ] GC(22) CMS: 340313K->322582K(349568K)
[1.122s][info   ][gc,metaspace   ] GC(22) Metaspace: 3908K->3908K(1056768K)
[1.123s][info   ][gc             ] GC(22) Pause Young (Allocation Failure) 485M->315M(494M) 61.931ms
[1.123s][info   ][gc,cpu         ] GC(22) User=0.06s Sys=0.01s Real=0.06s
[1.123s][info   ][gc,start       ] GC(24) Pause Initial Mark
[1.123s][info   ][gc             ] GC(24) Pause Initial Mark 317M->317M(494M) 0.210ms
[1.123s][info   ][gc,cpu         ] GC(24) User=0.00s Sys=0.00s Real=0.00s
[1.123s][info   ][gc             ] GC(24) Concurrent Mark
执行结束!共生成对象次数:9792
[1.126s][info   ][gc             ] GC(24) Concurrent Mark 2.981ms
[1.126s][info   ][gc,cpu         ] GC(24) User=0.01s Sys=0.00s Real=0.01s
[1.126s][info   ][gc             ] GC(24) Concurrent Preclean
[1.127s][info   ][gc             ] GC(24) Concurrent Preclean 0.479ms
[1.127s][info   ][gc,cpu         ] GC(24) User=0.00s Sys=0.00s Real=0.00s
[1.127s][info   ][gc             ] GC(24) Concurrent Abortable Preclean
[1.127s][info   ][gc             ] GC(24) Concurrent Abortable Preclean 0.006ms
[1.127s][info   ][gc,cpu         ] GC(24) User=0.00s Sys=0.00s Real=0.00s
[1.127s][info   ][gc,start       ] GC(24) Pause Remark
[1.128s][info   ][gc             ] GC(24) Pause Remark 320M->320M(494M) 1.109ms
[1.128s][info   ][gc,cpu         ] GC(24) User=0.00s Sys=0.00s Real=0.00s
[1.128s][info   ][gc             ] GC(24) Concurrent Sweep
[1.128s][info   ][gc             ] GC(24) Concurrent Sweep 0.552ms
[1.128s][info   ][gc,cpu         ] GC(24) User=0.00s Sys=0.00s Real=0.00s
[1.128s][info   ][gc             ] GC(24) Concurrent Reset
[1.129s][info   ][gc             ] GC(24) Concurrent Reset 0.306ms
[1.129s][info   ][gc,cpu         ] GC(24) User=0.00s Sys=0.00s Real=0.00s
[1.129s][info   ][gc,heap        ] GC(24) Old: 322582K->322582K(349568K)
[1.129s][info   ][gc,heap,exit   ] Heap
[1.129s][info   ][gc,heap,exit   ]  par new generation   total 157248K, used 5658K [0x00000007e0000000, 0x00000007eaaa0000, 0x00000007eaaa0000)
[1.129s][info   ][gc,heap,exit   ]   eden space 139776K,   4% used [0x00000007e0000000, 0x00000007e0586be0, 0x00000007e8880000)
[1.129s][info   ][gc,heap,exit   ]   from space 17472K,   0% used [0x00000007e8880000, 0x00000007e8880000, 0x00000007e9990000)
[1.129s][info   ][gc,heap,exit   ]   to   space 17472K,   0% used [0x00000007e9990000, 0x00000007e9990000, 0x00000007eaaa0000)
[1.129s][info   ][gc,heap,exit   ]  concurrent mark-sweep generation total 349568K, used 322582K [0x00000007eaaa0000, 0x0000000800000000, 0x0000000800000000)
[1.129s][info   ][gc,heap,exit   ]  Metaspace       used 3918K, capacity 4490K, committed 4864K, reserved 1056768K
[1.129s][info   ][gc,heap,exit   ]   class space    used 334K, capacity 386K, committed 512K, reserved 1048576K
```

```bash
➜  src git:(main) ✗ java -XX:+UseParallelGC -Xms512m -Xmx512m -XX:+PrintGC com.loodeer.Main
[0.002s][warning][gc] -XX:+PrintGC is deprecated. Will use -Xlog:gc instead.
[0.010s][info   ][gc] Using Parallel
正在执行...
[0.214s][info   ][gc] GC(0) Pause Young (Allocation Failure) 128M->52M(491M) 25.470ms
[0.278s][info   ][gc] GC(1) Pause Young (Allocation Failure) 180M->99M(491M) 30.884ms
[0.327s][info   ][gc] GC(2) Pause Young (Allocation Failure) 228M->145M(491M) 20.084ms
[0.381s][info   ][gc] GC(3) Pause Young (Allocation Failure) 273M->194M(491M) 26.878ms
[0.436s][info   ][gc] GC(4) Pause Young (Allocation Failure) 323M->237M(491M) 30.888ms
[0.497s][info   ][gc] GC(5) Pause Young (Allocation Failure) 366M->285M(420M) 26.804ms
[0.513s][info   ][gc] GC(6) Pause Young (Allocation Failure) 342M->304M(455M) 8.026ms
[0.532s][info   ][gc] GC(7) Pause Young (Allocation Failure) 361M->323M(455M) 9.169ms
[0.563s][info   ][gc] GC(8) Pause Young (Allocation Failure) 381M->344M(455M) 22.956ms
[0.611s][info   ][gc] GC(9) Pause Full (Ergonomics) 344M->234M(455M) 47.295ms
[0.625s][info   ][gc] GC(10) Pause Young (Allocation Failure) 292M->255M(455M) 4.181ms
[0.640s][info   ][gc] GC(11) Pause Young (Allocation Failure) 313M->277M(455M) 6.593ms
[0.665s][info   ][gc] GC(12) Pause Young (Allocation Failure) 334M->302M(455M) 9.240ms
[0.682s][info   ][gc] GC(13) Pause Young (Allocation Failure) 360M->324M(455M) 8.587ms
[0.724s][info   ][gc] GC(14) Pause Full (Ergonomics) 324M->266M(455M) 41.715ms
[0.737s][info   ][gc] GC(15) Pause Young (Allocation Failure) 323M->290M(455M) 4.209ms
[0.763s][info   ][gc] GC(16) Pause Young (Allocation Failure) 347M->312M(455M) 9.449ms
[0.780s][info   ][gc] GC(17) Pause Young (Allocation Failure) 370M->335M(455M) 8.196ms
[0.823s][info   ][gc] GC(18) Pause Full (Ergonomics) 335M->280M(455M) 42.435ms
[0.835s][info   ][gc] GC(19) Pause Young (Allocation Failure) 337M->302M(455M) 3.989ms
[0.860s][info   ][gc] GC(20) Pause Young (Allocation Failure) 359M->320M(455M) 11.495ms
[0.892s][info   ][gc] GC(21) Pause Full (Ergonomics) 320M->282M(455M) 32.338ms
[0.911s][info   ][gc] GC(22) Pause Young (Allocation Failure) 339M->304M(455M) 2.780ms
[0.927s][info   ][gc] GC(23) Pause Young (Allocation Failure) 361M->322M(455M) 7.479ms
[0.965s][info   ][gc] GC(24) Pause Full (Ergonomics) 322M->288M(455M) 37.546ms
[0.977s][info   ][gc] GC(25) Pause Young (Allocation Failure) 346M->312M(455M) 4.181ms
[0.995s][info   ][gc] GC(26) Pause Young (Allocation Failure) 369M->335M(455M) 8.260ms
[1.036s][info   ][gc] GC(27) Pause Full (Ergonomics) 335M->303M(455M) 41.804ms
[1.049s][info   ][gc] GC(28) Pause Young (Allocation Failure) 360M->322M(455M) 2.604ms
[1.075s][info   ][gc] GC(29) Pause Young (Allocation Failure) 379M->345M(455M) 17.007ms
[1.109s][info   ][gc] GC(30) Pause Full (Ergonomics) 345M->307M(455M) 34.243ms
执行结束!共生成对象次数:8558
```

