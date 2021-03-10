<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>用户注册页面</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/register/vue.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/register/htmlpage.css" />
</head>
<body>
<div id="container">
    <h2>用户注册</h2>
    <form action="/register/doRegister" method="get">
        <table>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" id="name" name="username" v-model="username" @blur="checkName" @keyup.enter="$event.target.blur" placeholder="请输入长度不大于4位的用户名" />
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="text" name="password" maxlength="16" id="secret" placeholder="请输入8~16位的密码" v-show="showPwd" v-model="pwd" @blur="checkWord" @keyup.enter="$event.target.blur"/>
                    <input type="password"  maxlength="16" id="secret" placeholder="请输入8~16位的密码" v-show="!showPwd" v-model="pwd" @blur="checkWord" @keyup.enter="$event.target.blur"/>
                    <div v-on:click="showPwd=!showPwd">
                        <span v-show="showPwd"><img id="first" src="${pageContext.request.contextPath}/pages/register/睁眼.png" /></span>
                        <span v-show="!showPwd"><img id="first" src="${pageContext.request.contextPath}/pages/register/闭眼.png" /></span>
                    </div>
                </td>

            </tr>
            <tr>
                <td>确认密码：</td>
                <td>
                    <input type="text" name="rpassword" maxlength="16" id="rsecret" placeholder="请确认你的密码" v-show="rshowPwd" v-model="rpwd"  @blur="checkrWord" @keyup.enter="$event.target.blur"/>
                    <input type="password" maxlength="16" id="rsecret" placeholder="请确认你的密码" v-show="!rshowPwd" v-model="rpwd" @blur="checkrWord" @keyup.enter="$event.target.blur" />
                    <div v-on:click="rshowPwd=!rshowPwd">
                        <span v-show="rshowPwd"><img id="confirm" src="${pageContext.request.contextPath}/pages/register/睁眼.png" /></span>
                        <span v-show="!rshowPwd"><img id="confirm" src="${pageContext.request.contextPath}/pages/register/闭眼.png" /></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td><button id="btn" class="button blue" v-on:click.stop="Finish" type="submit" >完成注册</button></td>
            </tr>
            <tr>
                <td><span class="wrong">{{tip}}</span></td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/register/JavaScript.js"></script>
</body>
</html>
