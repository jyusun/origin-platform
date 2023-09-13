## 接口管理

- 接口管理包，使用了开源的 [knife4j](https://doc.xiaominfo.com/knife4j/) 进行了扫描配置，使其能够方便的集成使用

## 使用方式

- 在pom.xml中引入

```xml
<!-- 基础工具-接口管理 -->
<dependency>
    <groupId>com.jyusun.origin</groupId>
    <artifactId>origin-base-swagger</artifactId>
    <version>${project.version}</version>
</dependency>
```
- 配置YML（不配置默认启用，扫描com.jyusun.origin下所有Web接口）

```yaml
origin-system:
  swagger:
    # 是否启用：true-启用，false-禁用 （默认为true,生产环境下建议禁用）
    enabled: true
    title: 这是一个文档主题
    description: 这是一个文档描述
    # 默认扫描 com.jyusun.origin 路径下所有Web接口
    base-package: com.jyusun.origin
    contact:
      name: jyusun
      url: www.baidu.com
      email: test@test.com
```
- 其他用法与Swagger一致配置完成后即可

- 启动应用程序并访问

```text
http://ip:port/doc.html
```