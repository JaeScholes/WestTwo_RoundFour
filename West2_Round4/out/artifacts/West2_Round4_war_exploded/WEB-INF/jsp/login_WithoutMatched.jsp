<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>密码错误登录页面</title>
    <script src="${pageContext.request.contextPath}/pages/login_WithoutMatched/vue.min.js"></script>
    <link rel="stylesheet"href="${pageContext.request.contextPath}/pages/login_WithoutMatched/htmlpage.css"/>
</head>
<body>
<div id="container">
    <h2>用户登录</h2>
    <form action="/login/doLogin" method="get">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="name" name="account" placeholder="请输入用户名" v-model="username"@blur="checkName" @keyup.enter="$event.target.blur"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="text" name="password" maxlength="16" id="secret" placeholder="请输入8~16位的密码" v-show="showPwd" v-model="pwd" @blur="checkWord" @keyup.enter="$event.target.blur"/>
                    <input type="password" maxlength="16" id="secret" placeholder="请输入8~16位的密码" v-show="!showPwd" v-model="pwd" @blur="checkWord" @keyup.enter="$event.target.blur"/>
                    <div v-on:click="showPwd=!showPwd">
                        <span v-show="showPwd"><img id="first" src="${pageContext.request.contextPath}/pages/login_WithoutMatched/睁眼.png" /></span>
                        <span v-show="!showPwd"><img id="first" src="${pageContext.request.contextPath}/pages/login_WithoutMatched/闭眼.png" /></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td><button id="btn" class="button blue" type="submit" v-on:click.stop="Finish">登录</button></td>
            </tr>
            <tr class="skip">
                <td class="left"><a href="http://localhost:8080/login/goRegister">用户注册</a></td>

            </tr>
            <tr>
                <td><span class="wrong">{{tip}}</span></td>
            </tr>
        </table>
    </form>
</div>
<script src="${pageContext.request.contextPath}/pages/login_WithoutMatched/JavaScript.js"></script>
</body>
</html>
