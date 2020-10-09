[![Build Status](https://travis-ci.org/houko/SpringBootUnity.svg?branch=master)](https://travis-ci.org/houko/SpringBootUnity)
[![Backers on Open Collective](https://opencollective.com/SpringBootUnity/backers/badge.svg)](#backers) [![Sponsors on Open Collective](https://opencollective.com/SpringBootUnity/sponsors/badge.svg)](#sponsors) [![GitHub issues](https://img.shields.io/github/issues/houko/SpringBootUnity.svg)](https://github.com/houko/SpringBootUnity/issues)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/houko/SpringBootUnity/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg)]()

###  项目简介
![mark](screenshot/SpringBootUnity.png)

### 环境
- `maven` latest   
- `jdk11`   
- `spring boot 2.x release`
- 个人推荐`idea`来代替eclipse
- git: 版本管理
- nginx: 反向代理服务器


### 注意事项
- 本项目代码托管在[github](https://github.com/houko/SpringBootUnity)和[码云](http://git.oschina.net/hupeng_admin/SpringBootUnity)两个地方,最新代码会先推送在github上,码云上会在github上更新完之后进行同步。
- 本项目多数数据库都用到了`hibernate`，如果没有提供`sql`文件。则启动时会根据代码映射自动生成数据库表，请在启动前修改`application.properties`中的数据库连接信息


### 启动方式

- 本地运行
本repo是一个多模块组成，每一个模块都可以单独打包运行。如果想运行哪个模块可以找到对应的Main文件右键运行。
![run](screenshot/run.png)


- 在linux服务器运行
 `spring boot`内置了tomcat做为web容器，默认打成jar包直接放在服务器上执行就可以了
> `java -Xms64m -Xmx2048m -jar project.jar 5 >> ./project.log &`



### 打包
- 如果需要定制化打成war包，那么也很简单。在`maven`中做下设置就ok了,然后把war包扔到tomcat下面就可以运行了

```
    <modelVersion>4.0.0</modelVersion>
    <artifactId>api</artifactId>
    <packaging>war</packaging>
```


### 更新日志
- 2017-09-02 api模块: 添加swagger-bootstrap-ui,和原有ui并行存在。       
http://localhost:8080 默认UI           
http://localhost:808/doc.html bootstrap-ui  

- 2017-09-02 spring boot版本从1.4.3更新到1.5.8   
- 2017-09-02 修复不配置数据库信息无法启动的bug   
- 2017-09-02 版本号更新到2017.1   
- 2017-09-02 api模块(swagger)添加开源库swagger-bootstrap-ui，和swagger默认UI同时存在。  
- 2017-09-02 web模块添加数据库sql文件,导入后一键启动可直接访问到web界面。  
- 2017-09-06 mybatis模块:添加USER.sql,启动后访问:http://localhost:8080 即可看到接口数据
- 2017-09-06 所有模块： 添加 characterEncoding=utf8&useSSL=true 解决高版本mysql的sll警告
- 2017-09-06 添加代码贡献者列表和支持者，赞助商链接。
- 2017-09-08 crawler模块(网络爬虫):修复本地文件目录不存在会报错的bug。处理方式为：不存在则自动创建
- 2017-11-02 开源协议从apache更换到MIT
- 2017-11-02 添加本地运行方式的说明
- 2017-11-02 版本更新到2020.1
- 2017-11-02 spring boot版本更新到1.5.8
- 2017-11-03 添加kotlin环境配置
- 2017-11-03 按照阿里巴巴编程规范插件P3C优化代码
- 2017-11-03 合并`api`和`website`模块,访问`localhost:8080`会显示网站主页,访问`localhost:8080/api`会显示api管理界面
- 2018-04-09 将整个项目升级到spring boot2.0 release版本,api有较大变动
- 2019-08-06 travis-ci指定jdk版本
- 2020-10-09 升级版本到2020.2
- 2020-10-09 升级jdk版本到11
- 2020-10-09 升级mysql connector到8
- 2020-10-09 升级spring boot到2.3.0
- 2020-10-09 修复了升级后API的破坏性变动，修复了一些了编辑器警告




## 贡献者

感谢所有为本项目做出贡献的开发者们.
<a href="graphs/contributors"><img src="https://opencollective.com/SpringBootUnity/contributors.svg?width=890" /></a>


## 支持者

感谢您的支持! 🙏  [[成为支持者](https://opencollective.com/SpringBootUnity#backer)]

<a href="https://opencollective.com/SpringBootUnity#backers" target="_blank"><img src="https://opencollective.com/SpringBootUnity/backers.svg?width=890"></a>


## 赞助商

[[成为赞助商](https://opencollective.com/SpringBootUnity#sponsor)]支持本项目并成为赞助商. 您的LOGO和网站链接将会被展示在这里. 

<a href="https://opencollective.com/SpringBootUnity/sponsor/0/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/0/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/1/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/1/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/2/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/2/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/3/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/3/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/4/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/4/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/5/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/5/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/6/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/6/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/7/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/7/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/8/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/8/avatar.svg"></a>
<a href="https://opencollective.com/SpringBootUnity/sponsor/9/website" target="_blank"><img src="https://opencollective.com/SpringBootUnity/sponsor/9/avatar.svg"></a>

###  项目说明
需求是多变的，本项目是以spring boot为基础，在使用spring boot的过程中对应不同的需求选用不同的技术和spring boot进行搭配，因此本项目是个偏于使用示例的定位。同时如果您在使用spring boot的过程中有什么好用的技术期待您对本项目的PR。

### 关于我
 @[小莫](https://xiaomo.info)：本人是一个热爱开源精神、追求新潮的开发者，技术过得去，还算勤勉！习惯以github的issue驱动方式来组织我的项目，也希望感兴趣的朋友和我联系，一起进步，共同开发感兴趣的开源项目。目前任rpg服务端主程，熟悉游戏开发和web开发。同时也是个喜欢二次元的死宅，爱动漫，略懂日语。

### 在线小工具

- [在线Cron表达式生成器](http://cron.qqe2.com/ "在线Cron表达式生成器")

- [在线工具 - 程序员的工具箱](http://tool.lu/ "在线工具 - 程序员的工具箱")
- [spring boot官方脚手架](https://start.spring.io/ "spring boot官方脚手架")


###  问题反馈
1. 欢迎提[issue](https://github.com/houko/SpringBootUnity/issues)一起完善这个项目。
2. QQ: 83387856
4. 个人主站: https://xiaomo.info


## [License](LICENSE "MIT")

    MIT License
    
    Copyright (c) 2020 Peng Hu
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

