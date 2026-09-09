- 2017-09-02 api模块: 添加swagger-bootstrap-ui,和原有ui并行存在。       
http://localhost:8080 默认UI           
http://localhost:8080/doc.html bootstrap-ui   

```
1. spring boot版本从1.4.3更新到1.5.6   
2. 修复不配置数据库信息无法启动的bug   
3. 版本号更新到2017.1   
4. api模块(swagger)添加开源库swagger-bootstrap-ui，和swagger默认UI同时存在。  
5. web模块添加数据库sql文件,导入后一键启动可直接访问到web界面。  
```



![support](screenshot/support.png)

#### swagger            
![默认](screenshot/swagger-ui.png)


#### bootstrap-ui
![bootstrap](screenshot/bootstrap.png)

![bootstrap](screenshot/interface.png)

![bootstrap](screenshot/api.png)



- 2017-09-06 更新记录

```
1. mybatis模块:添加USER.sql,启动后访问:http://localhost:8080 即可看到接口数据
2. 所有模块： 添加 characterEncoding=utf8&useSSL=true 解决高版本mysql的sll警告
3. 添加代码贡献者列表和支持者，赞助商链接。
```

- 2017-09-08 更新记录

```
1. crawler模块(网络爬虫):修复本地文件目录不存在会报错的bug。处理方式为：不存在则自动创建
```

![crawler](screenshot/crawler.png)
![yys](screenshot/yys.png)
- 2026-09-09 更新记录

```
1. 升级到 spring boot 4.1.1, jdk 升级到 21, 全部依赖更新到最新版本
2. javax.* 迁移到 jakarta.*(persistence/servlet/mail/websocket)
3. api 文档从已停止维护的 springfox 迁移到 springdoc-openapi, 注解升级到 OpenAPI 3
4. 移除 swagger-bootstrap-ui / knife4j(最新版仍锁定 spring boot 3, 无法与 4 共存),
   上文提到的 /doc.html 界面不再提供, 改用 springdoc 自带的 /swagger-ui.html
5. security 模块改用 SecurityFilterChain 组件式配置, 替换已移除的 WebSecurityConfigurerAdapter
6. fastjson 迁移到 fastjson2, jackson 升级到 3.x, poi 升级到 5.x, dom4j 迁移到 org.dom4j
7. 新增 GitHub Actions 构建 CI(此前仓库没有任何会编译代码的 CI)
8. 修正包名拼写: info.xiaomo.anysc -> async, info.xiaomo.core.untils -> utils
9. 打开此前被全局关闭的测试执行(surefire skipTests)
10. 清理 oauth.properties 中提交进仓库的第三方登录密钥, 换成占位符
```
