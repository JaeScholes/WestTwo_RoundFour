<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>目录页面</title>
    <script src="${pageContext.request.contextPath}/pages/user/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/user/htmlpage.css" />

</head>
<body>
<div id="container">
    <div id="header">
        <h1>十度网盘</h1>

        <a class="deleteAll"href="${pageContext.request.contextPath}/login.html">用户注销</a>
        <p>用户:{{username}}</p>
        <div class="bar">
            <input placeholder="新建文件夹" type="text" id="input" v-model="newthing" maxlength="10"/>
            <a class="add" v-on:click="addNewthing(newthing)"><img src="${pageContext.request.contextPath}/pages/user/加号.png"/></a>
            <a class="list-icon" v-bind:class="{'active':layout=='list'}" v-on:click="layout_change()"></a>
            <a class="grid-icon" v-bind:class="{'active':layout=='grid'}" v-on:click="layout_change()"></a>
        </div>
    </div>
    <div id="main">
        <div class="menu">
            <ul v-if="layout == 'grid'" class="grid">
                <!--大图模式-->
                <li v-for="(a,index) in artiles">
                    <a v-bind:href="a.url" target="_blank"><img class="${pageContext.request.contextPath}/pages/user/img_grid"src="${pageContext.request.contextPath}/pages/user/文件夹.png" /></a>
                    <p>{{a.title}}</p>
                    <img class="reset_grid" src="${pageContext.request.contextPath}/pages/user/修改.png" @click="a.showPad=!a.showPad"/>
                    <img class="delete_grid" src="${pageContext.request.contextPath}/pages/user/垃圾桶.png" @click="deleteNewthing(index)"/>
                    <input type="text" v-model="a.title" maxlength="10" v-if="a.showPad"/>
                </li>
            </ul>
            <ul v-if="layout == 'list'" class="list">
                <!-- 小图模式 -->
                <li v-for="(a,index) in artiles">
                    <a v-bind:href="a.url" target="_blank"><img class="img_list"src="${pageContext.request.contextPath}/pages/user/文件夹.png" /></a>
                    <p>{{a.title}}</p>
                    <input type="text"v-model="a.title"maxlength="10"v-if="a.showPad"/>
                    <img class="reset_list" src="${pageContext.request.contextPath}/pages/user/修改.png" @click="a.showPad=!a.showPad"/>
                    <img class="delete_list" src="${pageContext.request.contextPath}/pages/user/垃圾桶.png" @click="deleteNewthing(index)" />
                </li>
            </ul>
        </div>

    </div>

    <div id="footer">
        {{num}}项 &nbsp&nbsp&nbsp&nbsp @欢迎来到十度网盘
    </div>
</div>
<script src="${pageContext.request.contextPath}/pages/user/JavaScript.js"></script>
</body>
</html>
