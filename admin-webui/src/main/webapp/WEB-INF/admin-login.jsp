<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <style>
    </style>
    <title>LEAVE</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" href="admin/to/main/page.html" style="font-size: 32px;">LEAVE</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">

    <form action="security/do/login.html" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading">
            <i class="glyphicon glyphicon-log-in"></i> 管理员登录
        </h2>
        <%--  回显错误信息  --%>
        <p><font color="red">${requestScope.exception.message }</font></p>
        <p><font color="red">${SPRING_SECURITY_LAST_EXCEPTION.message }</font></p>
        <div class="form-group has-success has-feedback">
            <input type="text" name="userName" class="form-control" id="inputUserName"
                   placeholder="请输入登录账号" autofocus> <span
                class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" name="userPswd" class="form-control" id="inputUserPass"
                   placeholder="请输入登录密码" style="margin-top: 10px;"> <span
                class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <button type="submit" class="btn btn-lg btn-success btn-block">登录</button>
    </form>
</div>
</body>
</html>
