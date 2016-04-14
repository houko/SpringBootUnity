#1. 个人博客
1. core 核心模块 所有的公用部分都放在此模块
2. web 前台显示模块 启动器：WebMain
3. admin 后台管理模块 启动器：AdminMain
4. images 用户上传的头像都会保存在这里
5. 项目文档 包含接口文档 模块界面参考 和返回码对照表

###本项目采用springMVC，前后分离的架构来完成，只提供数据接口。

#2. gitignore不生效的原因和解决办法
无效的原因是：对应的目录或者文件已经被git跟踪，此时再加入.gitignore后就无效了，
解决办法： 执行 git rm -r --cached .idea 删掉git己经管理的idea文件