![blog](https://cloud.githubusercontent.com/assets/12625278/17610106/4af94430-606d-11e6-8917-5ccbb661a928.png)

# 一、我的博客
虽然不知道为什么，但是还是要先给自己的博客打一波广告吧。上面记载着我在项目开发的过程中积累的点滴，如果有什么建议也可以在上面给我[留言](http://blog.xiaomo.info/about/)

# 二、开发过程
1. 这个是我学习ng2的练手项目，也打算把它打造成我的个人小站点。开发完成之后我发把放到`http://xiaomo.info`上面。
2. 一直以来都打算做一个自己的个人网站，但是无奈后端出身的我对于美感的把握真心不怎么样。只有借助bootstrap的威力才能将页面写的稍微好看一点。
3. 至于以后我做前端工作的时候肯定会好好把css这一块知识补充起来的。这个项目有两大部分，除了我的个人站点之外还会花费很大一部分时间做一个UI控件库，现在的项目计划[issue](https://github.com/xiaomo-info/xiaomo-info-web/issues?q=is%3Aopen+is%3Aissue+milestone%3A%E9%80%9A%E7%94%A8%E7%BB%84%E4%BB%B6%E5%BA%93)

![image1](https://cloud.githubusercontent.com/assets/12625278/17553768/50c6301e-5f3a-11e6-9e19-7754b085aec5.png)

# 三、本项目相关
这个项目既然定位是个人站点，那么内容肯定是偏向于个人信息展示，以及服务自我和他人为目的。所以平时遇到好的技术大牛的博客、好的技术、正在研究的东西
等等我都会收集起来。

# 四、其他说明
本人是一个热爱开源精神、追求新潮的开发者。习惯以github的issue驱动方式来组织我的项目，也希望感兴趣的朋友和我联系一起进步，共同开发感兴趣的开源项目。
当然我会将本项目的angular一直维护在最新的稳定状态。

# 五、鸣谢
1. @Alice([AlicePrincess](https://github.com/AlicePrincess))
一个很聪明伶俐、多才多艺的萌妹子，在项目过程中遇到的很多问题都是她帮我解决掉的。
2. @龙猫 [龙猫](http://zxcool.cn)
大长腿欧巴，P得一手好图，做得UI叼炸天。一个好的网站没有一个好的UI真是个灾难，没有他的帮忙我的网站就进行不下去。
3. @[AngularJS中文社区](http://angular.cn)(278252889)
很多技术大牛聚集的地方，成员友好。
4. @[stackoverflow](http://stackoverflow.com/)
最优秀的技术问答社区，没有之一！善用此工具，事半功倍。
5. @[angular2-webpack-starter](https://github.com/AngularClass/angular2-webpack-starter)
目前最好的项目seed，一个好的开发环境能够节省很多时间。多亏有了@angularClass，让我可以集中精力开发项目。
6. @[spring boot](http://docs.spring.io/spring-boot/docs/current/reference/html/)，微服务框架最好的选择。省去了诸多配置烦恼。
7. @其他所有对我有帮助的人和事情。
要感谢的人很多，一一列举相信各位看官也没什么兴趣。总之很感谢，能够在大家的帮助下不断进步。

# 六、关于我
  @[小莫](http://xiaomo.info)：一个普通的技术开发者，技术过得去，还算勤勉！人在杭州，希望进网易和阿里让自己能够更快的进步,为公司贡献自己的力量。

# 八、问题反馈
1. 欢迎提[issue](https://github.com/xiaomo-info/xiaomo-info-web/issues)
2. QQ: 83387856
3. 博客: http://blog.xiaomo.info
4. 个人主站: http://xiaomo.info

# 七. gitignore不生效的原因和解决办法
无效的原因是：对应的目录或者文件已经被git跟踪，此时再加入.gitignore后就无效了
解决办法： 执行 git rm -r --cached .idea 删掉git己经管理的idea文件
