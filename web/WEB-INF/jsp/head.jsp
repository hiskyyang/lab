<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="header">
    <div class="header-bannaer"></div>
    <div class="header-menu">
        <a href="sample/list">样品管理</a>
        &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="code/list">字典管理</a>
        &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="user/list">用户管理</a>
        &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    <div class="header-user">
        ${sessionScope.user.name}
        &nbsp;&nbsp;
        <a href="auth/logout">退出</a>
    </div>
</div>
