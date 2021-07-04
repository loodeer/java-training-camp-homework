> **2.**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。



```bash
➜  work02 git:(main) ✗ java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar
```

#### 不知道允许的服务路径端口号咋办？

```bash
➜  work02 git:(main) ✗ jps -l
25780 sun.tools.jps.Jps
24022 gateway-server-0.0.1-SNAPSHOT.jar
25113 arthas-boot.jar
➜  work02 git:(main) ✗ lsof -nP -p 24022 | grep LISTEN
java    24022  lzh   19u     IPv6 0xb36240b8263ff27d      0t0                 TCP *:8088 (LISTEN)
java    24022  lzh   96u     IPv6 0xb36240b82ff0ff3d      0t0                 TCP 127.0.0.1:3658 (LISTEN)
java    24022  lzh  121u     IPv6 0xb36240b82ff1059d      0t0                 TCP 127.0.0.1:8563 (LISTEN)
```

得知端口号为 8088。

```bash
[arthas@24022]$ sc *gateway*
io.github.kimmking.gateway.server.ApiServerApplication
io.github.kimmking.gateway.server.ApiServerApplication$$EnhancerBySpringCGLIB$$cf5435ad
io.github.kimmking.gateway.server.HelloController
Affect(row-cnt:3) cost in 20 ms.
[arthas@24022]$ jad io.github.kimmking.gateway.server.HelloController

ClassLoader:
+-org.springframework.boot.loader.LaunchedURLClassLoader@20ad9418
  +-sun.misc.Launcher$AppClassLoader@55f96302
    +-sun.misc.Launcher$ExtClassLoader@1a86f2f1

Location:
file:/Users/lzh/workspace/time.geekbang/java-training-camp/java-training-camp-homework/week02/work02/gateway-server-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/

       /*
        * Decompiled with CFR.
        *
        * Could not load the following classes:
        *  javax.servlet.http.HttpServletRequest
        *  org.springframework.web.bind.annotation.GetMapping
        *  org.springframework.web.bind.annotation.RestController
        */
       package io.github.kimmking.gateway.server;

       import javax.servlet.http.HttpServletRequest;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;

       @RestController
       public class HelloController {
           @GetMapping(value={"/api/hello"})
           public String sayHello(HttpServletRequest request) {
/*16*/         return "hello world";
           }
       }

Affect(row-cnt:1) cost in 140 ms.
```

使用 arthas 得知路径。

```bash
➜  work02 git:(main) ✗ curl localhost:8088/api/hello
hello world%
```

```bash
➜  work02 git:(main) ✗ wrk --help
Usage: wrk <options> <url>
  Options:
    -c, --connections <N>  Connections to keep open
    -d, --duration    <T>  Duration of test
    -t, --threads     <N>  Number of threads to use

    -s, --script      <S>  Load Lua script file
    -H, --header      <H>  Add header to request
        --latency          Print latency statistics
        --timeout     <T>  Socket/request timeout
    -v, --version          Print version details

  Numeric arguments may include a SI unit (1k, 1M, 1G)
  Time arguments may include a time unit (2s, 2m, 2h)

➜  work02 git:(main) ✗ wrk -c 40 -d30s http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    14.42ms   39.84ms 531.99ms   91.93%
    Req/Sec     6.78k     3.28k   14.68k    57.36%
  402981 requests in 30.09s, 48.11MB read
Requests/sec:  13394.10
Transfer/sec:      1.60MB
```



```bash
➜  work02 git:(main) ✗ java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  work02 git:(main) ✗ wrk -c 40 -d30s http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    13.27ms   36.38ms 509.22ms   92.35%
    Req/Sec     6.51k     3.52k   15.66k    62.33%
  386674 requests in 30.10s, 46.16MB read
Requests/sec:  12846.38
Transfer/sec:      1.53MB
```

