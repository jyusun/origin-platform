# 日志处理

## 工具引用
```xml
<dependency>
    <groupId>${project.parent.groupId}</groupId>
    <artifactId>origin-core-logger</artifactId>
</dependency>
```
## API 接口日志 

- 记录用户的操作记录，比如请求与响应

### 使用方法

```java
    // 请求控制层 添加WebLogger注解
    @WebLogger("测试")
    public AbstractResult<Object> test() {
        return ResultFactory.success();
    }
```

## 统一全局异常拦截

- 避免因后端逻辑问题报错而返回给前端一堆异常明细，这不是客户想要看到的

> 拦截所有异常信息

