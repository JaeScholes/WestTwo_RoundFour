<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>管理员图片查看页面</title>
    <script src="${pageContext.request.contextPath}/pages/administrator/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="{pageContext.request.contextPath}/pages/administrator/htmlpage.css" />
</head>
<body>
<div id="container">
    <div id="header">
        <h1>十度网盘</h1>
        <a class="deleteAll" href="${pageContext.request.contextPath}/user.jsp">返回</a>
        <p>管理员:{{username}}</p>
        <div class="bar">
            <a class="list-icon" v-bind:class="{'active':layout=='list'}" v-on:click="layout_change()"></a>
            <a class="grid-icon" v-bind:class="{'active':layout=='grid'}" v-on:click="layout_change()"></a>
        </div>
    </div>
    <div id="main">
        <div class="menu">
            <ul v-if="layout == 'grid'" class="grid">
                <!--大图模式-->
                <li v-for="(a,index) in pages">
                    <a target="#" :href="a.url"><img class="img_grid" :src="a.url" /></a>
                    <p>{{a.title}}</p>
                    <img class="binggo_grid"src="${pageContext.request.contextPath}/pages/administrator/通过.png"/>
                    <img class="delete_grid" src="${pageContext.request.contextPath}/pages/administrator/垃圾桶.png" @click="deleteNewthing(index)" />
                </li>
            </ul>
            <ul v-if="layout == 'list'" class="list">
                <!-- 小图模式 -->
                <li v-for="(a,index) in pages">
                    <a target="#" :href="a.url"><img class="img_list":src="a.url" /></a>
                    <p>{{a.title/a.time/a.user}}</p>
                    <img class="binggo_list" src="${pageContext.request.contextPath}/pages/administrator/通过.png" />
                    <img class="delete_list" src="${pageContext.request.contextPath}/pages/administrator/垃圾桶.png" @click="deleteNewthing(index)" />
                </li>
            </ul>
        </div>
    </div>
    <div id="footer">
        第{{itempage}}页
        <div class="page">
            <a href="#"v-on:click="lastpage(),listShow()">上一页</a>
            <a href="#"v-on:click="itempage=1,listShow()">1</a>
            <a href="#"v-on:click="itempage=2,listShow()">2</a>
            <a href="#"v-on:click="itempage=3,listShow()">3</a>
            <a href="#"v-on:click="itempage=4,listShow()">4</a>
            <a href="#"v-on:click="itempage=5,listShow()">5</a>
            <a href="#"v-on:click="itempage=6,listShow()">6</a>
            <a href="#" v-on:click="nextpage(),listShow()">下一页</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/pages/administrator/JavaScript.js"></script>
</body>
</html>
