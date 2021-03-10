<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>欢迎页面</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/welcome/vue.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/welcome/StyleSheet.css" />
</head>
<body>
<div id="container">
    <h3>注册成功!!!</h3>
    <button class="button blue"v-on:click="show=true">确定</button>

    <div v-if="show" class="turn_up">
        <p>用户名：${username}</p>
        <p>账号：${account}</p>
        <p>密码：${password}</p>
        <a class="turn" href="http://localhost:8080/register/goLogin">登录页面</a>
    </div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/welcome/JavaScript.js"></script>
</body>
</html>
