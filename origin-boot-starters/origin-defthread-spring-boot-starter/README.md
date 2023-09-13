# Thread Operation

## 说明

Spring Async 默认简单封装,如果用到@Async 异步处理可以引入此配置，内部使用了 ThredPoolTaskExcutor

## 使用方式

### 依赖管理

```xml

<dependency>
    <groupId>com.jyusun.origin</groupId>
    <artifactId>origin-defthread-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

```

### 配置文件

参数：

|参数|说明|
|---|---|
|thread-name-prefix|线程前缀名|
|core-pool-size|核心线程数|
|max-pool-size|最大线程数|

- 线程池配置

```yml
origin-system:
  thread-pool:
    enabled: true
    thread-name-prefix: Thread-Async-
    core-pool-size: 10
    max-pool-size: 50
```

