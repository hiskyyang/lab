<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/public.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/user/user.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/public.css">
    <title>用户管理</title>
</head>
<body>
    <div class="panel">
        <div class="panel-title">用户管理</div>
        <div class="panel-searchForm">
            <form id="myForm" method="post" action="user/update">
                <input id="userId" name="userId" value="${user.userId}" type="hidden"/>
                <table>
                    <tr>
                        <td>账号</td>
                        <td><input id="name" name="name" value="${user.name}"/></td>
                    </tr>
                    <tr>
                        <td>类型</td>
                        <td><input type="radio" name="type" value="1" ${user.type == 1 ? "checked=checked" : ""} />普通用户
                            <input type="radio" name="type" value="0" ${user.type == 0 ? "checked=checked" : ""} />管理员
                        </td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td>
                            <input id="password" name="password" type="password" value="${user.password}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="button" value="保存" onclick="save()"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
