<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>用户登录页面</title>
    <script src="${pageContext.request.contextPath}/pages/login/vue.min.js"></script>
    <link rel="stylesheet"href="${pageContext.request.contextPath}/pages/login/htmlpage.css"/>
</head>
<body>
<div id="container">
    <h2>用户登录</h2>
    <form action="/login/doLogin" method="get">
        <table>
            <tr>
                <td>帐号：</td>
                <td><input type="text" name="account" id="name" placeholder="请输入帐号" v-model="username"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="text" name="password" maxlength="16" id="secret" placeholder="请输入8~16位的密码" v-show="showPwd" v-model="pwd" />
                    <input type="password"  maxlength="16" id="secret" placeholder="请输入8~16位的密码" v-show="!showPwd" v-model="pwd" />
                    <div v-on:click="showPwd=!showPwd">
                        <span v-show="showPwd"><img id="first" src="${pageContext.request.contextPath}/pages/login/睁眼.png" /></span>
                        <span v-show="!showPwd"><img id="first" src="${pageContext.request.contextPath}/pages/login/闭眼.png" /></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td><button id="btn" class="button blue" type="submit" v-on:click.stop="Finish">登录</button></td>
            </tr>
            <tr class="skip">
                <td class="left"><a href="http://localhost:8080/login/goRegister">用户注册</a></td>
            </tr>
        </table>
    </form>
</div>
<script src="${pageContext.request.contextPath}/pages/login/JavaScript.js"></script>
</body>
</html>
