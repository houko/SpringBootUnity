<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="icon" href="http://image.xiaomo.info/logo/logo.png">
    <title>小莫的个人网站</title>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="header">
    <nav class="bg-faded ">
        <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse"
                data-target="#exCollapsingNavbar2">
            &#9776;
        </button>
        <div class="collapse navbar-toggleable-xs" id="exCollapsingNavbar2" style="padding-left: 300px">
            <a class="navbar-brand" href="#" style="color: white">xiaomo.info</a>
            <ul class="nav navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link navbar-color active" href="#">首页 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link navbar-color" href="http://blog.xiaomo.info">博客</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link navbar-color" href="https://xiaomo.info">关于</a>
                </li>
            </ul>
        <#if currentUser??>
            <ul class="nav navbar-nav pull-right" style="padding-right:50px">
                <li class="nav-item active">
                    <a href="/user/logout">${currentUser.email!}</a>
                </li>
            </ul>
        <#else>
            <ul class="nav navbar-nav pull-right" style="padding-right:50px">
                <li class="nav-item active">
                    <a class="nav-link navbar-color" href="/user/toLogin">登录 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link navbar-color" href="user/toRegister">注册</a>
                </li>
            </ul>
        </#if>


        </div>
    </nav>
</div>
<!-- 头部到此结束 -->
<div class="skills">
    <div class="content">
        <h3 class="my">美好的未来，期待和你一起去实现!</h3>
        <div class="row">
            <div class="col-md-3">
                <h3><i class="icon-html5"> </i>页面布局</h3>
                <p>
                    能够使用html5+css3和bootstrap进行快速页面布局，所见即所得的效果能够大大提高自信心。每18个月互联网前端开发就会难上一倍,随着我们审美的提高.要求也更加苛刻,各种js框架层出不深,不学习就会落后.
                </p>
            </div>
            <div class="col-md-3">
                <h3><i class="icon-fighter-jet"> </i>javascript框架</h3>
                <p>
                    reactjs+redux、vuejs+vuex、angularjs、angular2各类框架层出不穷，但万变不变其中。用着学远比学着用掌握的更加迅速，在实战中提升自我吧。
                </p>
            </div>
            <div class="col-md-3">
                <h3><i class="icon-anchor"> </i>自动化构建</h3>
                <p>
                    作为新时代有梦想有追求的前端开发者，不使用模块化和自动化构建工具都不好意思和人家打招呼。而webpack和es6能让这一切变的更加顺利和自然。
                </p>
            </div>
            <div class="col-md-3">
                <h3><i class="icon-adn"> </i>JAVA</h3>
                <p>
                    Java 编程语言是个简单、面向对象、分布式、解释性、健壮、安全与系统无关、可移植、高性能、多线程和静态的语言。作为前端，我不会nodejs，但是java是我坚强的后盾。
                </p>
            </div>
        </div>
    </div>
</div>
<div class="useful">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <img src="/images/landing.png" alt="">
        </div>
        <div class="col-md-5">
            <div class="content">
                <h3 class="container">让我们一起涨姿势，结人脉！</h3>
                <br>
                <h6 class="container"> 在知识爆炸的年代，我们不愿成为知识的过客，拥抱开源文化，发挥社区的力量，让我们获得自我提升。</h6>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<footer>
    <div class="footer">
        <h5>Copyright © 2016-2017 xiaomo AllRight Reversed</h5>
        <h6><a href="https://xiaomo.info" target="_blank">浙ICP备15009606号</a></h6>
    </div>
</footer>

<script src="https://cdn.bootcss.com/jquery/2.2.1/jquery.js"></script>
</body>
</html>
