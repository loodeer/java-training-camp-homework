> **1.（选做）**自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。



1. javac -g Hello.java 编译 Hello.java 得到字节码文件 Hello.class。
2. javap -c -verbose Hello 反编译 Hello 类的字节码文件，反解析出对应的code区（汇编指令）、本地变量表、异常表和代码行偏移量映射表、常量池等等信息。

```bash
➜  work01 git:(main) ✗ javap -c -verbose Hello
Classfile /Users/lzh/workspace/time.geekbang/java-training-camp/java-training-camp-homework/week01/work01/Hello.class
  Last modified 2021年6月27日; size 1346 bytes
  SHA-256 checksum ae3fd581fcf306676e81f7822ac285fc6fad3715eab327eaa54799dfc01a1e42
  Compiled from "Hello.java"
public class Hello
  minor version: 0
  major version: 58
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #43                         // Hello 当前类
  super_class: #2                         // java/lang/Object 父类
  interfaces: 0, fields: 0, methods: 2, attributes: 3
Constant pool: 常量池
   #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
   #2 = Class              #4             // java/lang/Object
   #3 = NameAndType        #5:#6          // "<init>":()V
   #4 = Utf8               java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Double             2.0d
   #9 = Long               3l
  #11 = String             #12            //
  #12 = Utf8
  #13 = Methodref          #14.#15        // java/lang/String.length:()I
  #14 = Class              #16            // java/lang/String
  #15 = NameAndType        #17:#18        // length:()I
  #16 = Utf8               java/lang/String
  #17 = Utf8               length
  #18 = Utf8               ()I
  #19 = Fieldref           #20.#21        // java/lang/System.out:Ljava/io/PrintStream;
  #20 = Class              #22            // java/lang/System
  #21 = NameAndType        #23:#24        // out:Ljava/io/PrintStream;
  #22 = Utf8               java/lang/System
  #23 = Utf8               out
  #24 = Utf8               Ljava/io/PrintStream;
  #25 = InvokeDynamic      #0:#26         // #0:makeConcatWithConstants:(DJ)Ljava/lang/String;
  #26 = NameAndType        #27:#28        // makeConcatWithConstants:(DJ)Ljava/lang/String;
  #27 = Utf8               makeConcatWithConstants
  #28 = Utf8               (DJ)Ljava/lang/String;
  #29 = Methodref          #30.#31        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #30 = Class              #32            // java/io/PrintStream
  #31 = NameAndType        #33:#34        // println:(Ljava/lang/String;)V
  #32 = Utf8               java/io/PrintStream
  #33 = Utf8               println
  #34 = Utf8               (Ljava/lang/String;)V
  #35 = String             #36            // 四则运算: num1 * num4 =
  #36 = Utf8               四则运算: num1 * num4 =
  #37 = Methodref          #30.#38        // java/io/PrintStream.print:(Ljava/lang/String;)V
  #38 = NameAndType        #39:#34        // print:(Ljava/lang/String;)V
  #39 = Utf8               print
  #40 = Methodref          #30.#41        // java/io/PrintStream.println:(I)V
  #41 = NameAndType        #33:#42        // println:(I)V
  #42 = Utf8               (I)V
  #43 = Class              #44            // Hello
  #44 = Utf8               Hello
  #45 = Utf8               Code
  #46 = Utf8               LineNumberTable
  #47 = Utf8               LocalVariableTable
  #48 = Utf8               this
  #49 = Utf8               LHello;
  #50 = Utf8               main
  #51 = Utf8               ([Ljava/lang/String;)V
  #52 = Utf8               i
  #53 = Utf8               I
  #54 = Utf8               args
  #55 = Utf8               [Ljava/lang/String;
  #56 = Utf8               num1
  #57 = Utf8               num2
  #58 = Utf8               D
  #59 = Utf8               num3
  #60 = Utf8               J
  #61 = Utf8               num4
  #62 = Utf8               B
  #63 = Utf8               StackMapTable
  #64 = Class              #55            // "[Ljava/lang/String;"
  #65 = Utf8               SourceFile
  #66 = Utf8               Hello.java
  #67 = Utf8               BootstrapMethods
  #68 = MethodHandle       6:#69          // REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #69 = Methodref          #70.#71        // java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #70 = Class              #72            // java/lang/invoke/StringConcatFactory
  #71 = NameAndType        #27:#73        // makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #72 = Utf8               java/lang/invoke/StringConcatFactory
  #73 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #74 = String             #75            // 错误用法: num2 + num3 = \u0001\u0001
  #75 = Utf8               错误用法: num2 + num3 = \u0001\u0001
  #76 = Utf8               InnerClasses
  #77 = Class              #78            // java/lang/invoke/MethodHandles$Lookup
  #78 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #79 = Class              #80            // java/lang/invoke/MethodHandles
  #80 = Utf8               java/lang/invoke/MethodHandles
  #81 = Utf8               Lookup
{
  public Hello();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHello;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=5, locals=8, args_size=1 
         0: iconst_1
         1: istore_1
         2: ldc2_w        #7                  // double 2.0d
         5: dstore_2
         6: ldc2_w        #9                  // long 3l
         9: lstore        4
        11: iconst_4
        12: istore        6
        14: ldc           #11                 // String
        16: invokevirtual #13                 // Method java/lang/String.length:()I
        19: bipush        10
        21: if_icmpge     38
        24: getstatic     #19                 // Field java/lang/System.out:Ljava/io/PrintStream;
        27: dload_2
        28: lload         4
        30: invokedynamic #25,  0             // InvokeDynamic #0:makeConcatWithConstants:(DJ)Ljava/lang/String;
        35: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        38: iconst_0
        39: istore        7
        41: iload         7 
        43: iload_1
        44: if_icmpge     71 if (i < num1)
        47: getstatic     #19                 // Field java/lang/System.out:Ljava/io/PrintStream; System.out.print
        50: ldc           #35                 // String 四则运算: num1 * num4 =
        52: invokevirtual #37                 // Method java/io/PrintStream.print:(Ljava/lang/String;)V
        55: getstatic     #19                 // Field java/lang/System.out:Ljava/io/PrintStream;
        58: iload_1
        59: iload         6
        61: imul
        62: invokevirtual #40                 // Method java/io/PrintStream.println:(I)V
        65: iinc          7, 1
        68: goto          41 继续 for 循环
        71: return
      LineNumberTable:
        line 3: 0
        line 4: 2
        line 5: 6
        line 6: 11
        line 7: 14
        line 8: 24
        line 10: 38
        line 11: 47
        line 12: 55
        line 10: 65
        line 13: 71
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
           41      30     7     i   I
            0      72     0  args   [Ljava/lang/String;
            2      70     1  num1   I
            6      66     2  num2   D
           11      61     4  num3   J
           14      58     6  num4   B
      StackMapTable: number_of_entries = 3
        frame_type = 255 /* full_frame */
          offset_delta = 38
          locals = [ class "[Ljava/lang/String;", int, double, long, int ]
          stack = []
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 29
}
SourceFile: "Hello.java"
BootstrapMethods:
  0: #68 REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #74 错误用法: num2 + num3 = \u0001\u0001
InnerClasses:
  public static final #81= #77 of #79;    // Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
```



Java 源代码为

```java
public class Hello {
	public static void main(String[] args) {
		int num1 = 1;
		double num2 = 2.0D;
		long num3 = 3L;
		byte num4 = 4;
		if ("".length() < 10) {
            System.out.println("错误用法: num2 + num3 = " + num2 + num3);
		}
		for (int i = 0; i < num1; i++) {
			System.out.print("四则运算: num1 * num4 = ");
            System.out.println(num1 * num4);		}
	}
}
```



解析一下上面字节码里一些关键的信息：

```bash
    0: aload_0
```



第一个 0 表示偏移码；

a 表示引用类型，load 的是对象；

load 是指从本地变量表(Local Variable)加载到栈(Stack)上；

第二个 0 表示加载的是本地变量表第 0 个位置的变量；

------

```bash
    1: invokespecial #1                  // Method java/lang/Object."<init>":()V
```

字节码偏移码为 1；

invokespecial 操作码就是字面意思调用特殊方法；

#1 指常量池里标号为 #1 的常量；

// Method java/lang/Object."<init>":()V 显示前面字节码的注释，表示调用了父类 Object 的 init 方法，为 void 方法。

-----

```bash
      stack=5, locals=8, args_size=1 
```

执行 main 这个方法，需要的栈深度为 5，本地变量表长度为 8，入参为 1 个。

JVM 运行时需要栈的深度，本地变量表的长度，在编译完成是就确定了。

----

```bash
     0: iconst_1
     1: istore_1
     2: ldc2_w        #7                  // double 2.0d
     5: dstore_2
     6: ldc2_w        #9                  // long 3l
     9: lstore        4
    11: iconst_4
    12: istore        6
```

0: 将常量 1 压入栈中

1: 将栈顶的元素（常量1）保存到本地变量表第 1 个位置，类型为 int

2: 将常量池 #7 元素（2.0D）压入栈中

5: 将栈顶的元素（2.0D）保存到本地变量表第 2 个位置，类型为 double

6: 将常量池 #9 元素（3L）压入栈中

9: 将栈顶的元素（3L）保存到本地变量表第 4 个位置，类型为 long

11: 将常量 4 压入栈中

12: 将栈顶的元素（常量4）保存到本地变量表第 6 个位置，类型为 long

上面这些字节码对应的源代码就是下面 4 句:

```java
		int num1 = 1;
		double num2 = 2.0D;
		long num3 = 3L;
		byte num4 = 4;
```

----

```bash
        14: ldc           #11                 // String
        16: invokevirtual #13                 // Method java/lang/String.length:()I
        19: bipush        10
        21: if_icmpge     38
        24: getstatic     #19                 // Field java/lang/System.out:Ljava/io/PrintStream;
        27: dload_2
        28: lload         4
        30: invokedynamic #25,  0             // InvokeDynamic #0:makeConcatWithConstants:(DJ)Ljava/lang/String;
        35: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
```

14: 将常量 #11（空字符串）压入栈中

16: 调用 java/lang/String.length:()I，等到 int 值长度

19: 将常量 10 压入栈中。 // 当 int 取值 -128~127 时，JVM 采用 bipush 指令将常量压入栈中。

21: if (栈顶 >= 次栈顶) 接着往下走，否则跳到偏移码 38。 if (10 >= "".length())

24: System.out.println

27: 将本地变量表第 2 个位置 Double 类型的变量（2.0D）加载到栈中

28: 将本地变量表第 4 个位置 Long 类型的变量（3L）加载到栈中

30: 字符串拼接

35: 调用 println 方法

```java
		if ("".length() < 10) {
            System.out.println("错误用法: num2 + num3 = " + num2 + num3);
		}
```



----

```bash
  minor version: 0
  major version: 58
```

大版本 58，小版本 0。

51 => JDK7

52 => JDK8

53 => JDK9

...

58 => JDK14









![image-20210627124932164](https://tva1.sinaimg.cn/large/008i3skNly1grwqarwyjqj31660k0qde.jpg)

![image-20210627125324991](https://tva1.sinaimg.cn/large/008i3skNly1grwqerwsipj31kl0u07wh.jpg)

![image-20210627202849432](https://tva1.sinaimg.cn/large/008i3skNly1grx3kma4ahj31ry0u0qv5.jpg)