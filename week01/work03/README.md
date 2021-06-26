> 3.**（必做）**画一张图，展示 Xmx、Xms、Xmn、MetaSpace、DirectMemory、Xss 这些内存参数的关系。



要画这张图，先得搞清楚这几个内存参数的含义。

**Xmx**: 指定最大堆内存

**Xms**: 指定堆内存空间的初始大小。

**Xmn**: 新生代堆内存大小，等价于 -XX:NewSize，官方建议为 -Xmx 的 1/2 ~ 1/4。

**MetaSpace**: 元数据空间，大小受限于 Java 进程可用的本地内存。

**DirectMemory**：系统能够使用的堆外内存。

**Xss**: 设置每个线程栈的字节数，影响栈的深度。等价于 -XX:ThreadStackSize。



![image-20210627005401372](https://tva1.sinaimg.cn/large/008i3skNly1grw5ma2cxjj30qi0pkmyf.jpg)
