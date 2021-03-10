<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>用户图片查看页面</title>
    <script src="vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/userPicture/htmlpage.css" />
    <style>

    </style>
</head>
<body>
<div id="container">
    <div id="header">
        <h1>十度网盘</h1>

        <a class="deleteAll" href="${pageContext.request.contextPath}/administrator.jsp">返回</a>
        <p>用户:{{username}}</p>
        <div class="bar">
            <form>
                <input type="file" name="image_submit" accept="image/JPEG,image/PNG" id="input"/>
                <input type="image" src="${pageContext.request.contextPath}/pages/userPicture/加号.png" alt="Submit" class="add"/>
            </form>


            <a class="list-icon" v-bind:class="{'active':layout=='list'}" v-on:click="layout_change()"></a>
            <a class="grid-icon" v-bind:class="{'active':layout=='grid'}" v-on:click="layout_change()"></a>
        </div>
    </div>
    <div id="main">
        <div class="menu">
            <ul v-if="layout == 'grid'" class="grid">
                <!--大图模式-->
                <li v-for="(a,index) in pages">
                    <a target="#" :href="a.url" ><img class="img_grid":src="a.url" /></a>
                    <p>{{a.title}}</p>
                    <img class="reset_grid" src="${pageContext.request.contextPath}/pages/userPicture/下载.png" />
                    <img class="delete_grid" src="${pageContext.request.contextPath}/pages/userPicture/垃圾桶.png" @click="deleteNewthing(index)" />
                    <input type="text" v-model="a.title" maxlength="10" v-if="a.showPad" />
                </li>
            </ul>
            <ul v-if="layout == 'list'" class="list">
                <!-- 小图模式 -->
                <li v-for="(a,index) in pages" >

                    <a target="#" :href="a.url"><img class="img_list" :src="a.url" /></a>
                    <p>{{a.title}}</p>
                    <input type="text" v-model="a.title" maxlength="10" v-if="a.showPad" />
                    <img class="reset_list" src="${pageContext.request.contextPath}/pages/userPicture/下载.png" />
                    <img class="delete_list" src="${pageContext.request.contextPath}/pages/userPicture/垃圾桶.png" @click="deleteNewthing(index)" />
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
            <a href="#"v-on:click="nextpage(),listShow()">下一页</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/pages/userPicture/JavaScript.js"></script>
</body>
</html>
