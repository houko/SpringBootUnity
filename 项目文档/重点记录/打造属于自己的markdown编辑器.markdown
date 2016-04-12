# 1.思路
### 1.要么前端做
a.前台使用markdown编辑，提交后台原样保存。(使用的编辑器:markdown-editor)
    <script type="text/javascript" src="http://static.xiaomo.info/markdown/admin/markdown.js"></script>
    <script type="text/javascript" src="http://static.xiaomo.info/markdown/admin/markDownEditor.js"></script>
    
    <script type="text/javascript">
        $(function () {
            $("#textarea-1").markDownEditor({});
        });
    </script>
    
b.前台显示的时候使用showdown.min.js把markdown转成html。
[showdown](https://github.com/showdownjs/showdown)
<!-- 显示 -->
<link rel="stylesheet" href="http://static.xiaomo.info/markdown/web/basic.css">
<link rel="stylesheet" href="http://static.xiaomo.info/markdown/web/atelier-dune-dark.css">
<script src="http://static.xiaomo.info/markdown/web/highlight.min.js"></script>
<!-- 将markdown转成html -->
<script src="http://static.xiaomo.info/markdown/web/showdown.min.js"></script>
<script src="http://static.xiaomo.info/markdown/web/markDownUtil.js"></script>
使用:
    MarkDownUtil.compile(content)
        
### 2.要么后端做
    a.前端提交markdown给后端，后端在入库之前将markdown转成html存储。
    具体做法：
    依赖maven:
    <dependency>
        <groupId>org.markdownj</groupId>
        <artifactId>markdownj-core</artifactId>
    </dependency>
    使用：MarkDownUtil.markDown(content);
    
    b.前端请求后台数据直接显示。
    缺点：编辑的时候还要借助 tomarkdown.js 将html转成markdown,修改完成之后提交再转成html,相当麻烦。
### 3.比较结果:明显将markdown内容入库，显示的时候转换成html更加明智。


    