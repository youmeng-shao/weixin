
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
</head>
<body>
.....${ctx}<br>
    <div class="container-login">

        <form action="${ctx}/admin/login" method="post">
            <h2>请登录</h2>
            <input type="text" name="name" placeholder="管理员昵称"/><br>
            <input type="password" name="password" placeholder="密码"/><br>
            <button type="submit">登录</button>
        </form>

    </div>
</body>
</html>
