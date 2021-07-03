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


- GC 前老年代为 0MB，GC 后为 37218K = 36MB，说明 36 MB 的对象晋升到了老年代（**这里说 36 MB 晋升是错误的**）

  - **为啥第一次 GC 就会有对象晋升老年代？**

    - eden 区满了之后，对象直接在老年代分配。
    - 大对象直接在老年代分配。

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

