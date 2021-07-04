> 5. （选做）运行课上的例子，以及 Netty 的例子，分析相关现象。



### 单线程 socket 服务

运行 com.loodeer.HttpServer01。

```bash
➜  work05 git:(main) ✗ wrk -c 40 -d30s http://localhost:8801
Running 30s test @ http://localhost:8801
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.96ms    9.77ms 219.75ms   97.95%
    Req/Sec     0.93k   593.45     4.07k    74.42%
  53302 requests in 30.07s, 9.78MB read
  Socket errors: connect 0, read 354078, write 151, timeout 0
Requests/sec:   1772.51
Transfer/sec:    333.14KB
```

### 每个请求一个线程

运行 com.loodeer.HttpServer02。

```bash
➜  work05 git:(main) ✗ curl http://localhost:8802
hello,nio2%
➜  work05 git:(main) ✗ wrk -c 40 -d30s http://localhost:8802
Running 30s test @ http://localhost:8802
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    10.58ms   11.38ms 198.38ms   94.80%
    Req/Sec   661.08    232.23     1.71k    70.30%
  39441 requests in 30.09s, 6.11MB read
  Socket errors: connect 0, read 126842, write 238, timeout 0
Requests/sec:   1310.71
Transfer/sec:    208.06KB
```

### 创建了一个固定大小的线程池处理请求

运行 com.loodeer.HttpServer03。

```bash
➜  work05 git:(main) ✗ curl http://localhost:8803
hello,nio3%
➜  work05 git:(main) ✗ wrk -c 40 -d30s http://localhost:8803
Running 30s test @ http://localhost:8803
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     4.51ms   16.09ms 215.70ms   95.70%
    Req/Sec     2.95k     1.20k    5.56k    65.60%
  170042 requests in 30.06s, 16.63MB read
  Socket errors: connect 0, read 342605, write 24, timeout 0
Requests/sec:   5657.50
Transfer/sec:    566.62KB
```

### Netty 服务

运行 com.loodeer.netty.NettyHttpServer。

```bash
➜  work05 git:(main) ✗ curl http://localhost:8808
hello, others%
➜  work05 git:(main) ✗ wrk -c 40 -d30s http://localhost:8808
Running 30s test @ http://localhost:8808
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.67ms   28.92ms 627.23ms   94.97%
    Req/Sec    23.63k    10.47k   46.84k    64.31%
  1378237 requests in 30.08s, 141.95MB read
Requests/sec:  45822.31
Transfer/sec:      4.72MB
➜  work05 git:(main) ✗ wrk -c 40 -d30s -t 10  http://localhost:8808
Running 30s test @ http://localhost:8808
  10 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    51.12ms   90.38ms 750.10ms   85.16%
    Req/Sec     8.06k     8.56k   49.04k    83.32%
  1936074 requests in 30.09s, 199.41MB read
Requests/sec:  64351.04
Transfer/sec:      6.63MB
```

