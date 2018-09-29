<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=basePath%>css/jquery-ui/jquery-ui-1.9.2.custom.css" rel="stylesheet">
    <link href="<%=basePath%>css/jquery-ui/jquery.ui.all.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-ui/jquery-ui-1.9.2.custom.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/public.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/sample/sample.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/public.css">
    <script>
        $(function () {
            $("#collectDate").datepicker({
                "dateFormat": "yy-mm-dd",
                changeMonth: true,
                changeYear: true,
                showOtherMonths: true,
                selectOtherMonths: true
            });
        });
    </script>
    <title>样品管理</title>
</head>
<body>
    <div class="panel">
        <div class="panel-title">用户管理</div>
        <div class="panel-searchForm">
            <form id="myForm" method="post" action="sample/add">
                <input type="hidden" value="${sample.sampleId}" id="sampleId" name="sampleId"/>
                <table>
                    <tr>
                        <td>名称:</td>
                        <td><input id="name" name="name" value="${sample.name}"/></td>
                    </tr>
                    <tr>
                        <td>类型:</td>
                        <td>
                            <select id="type" name="type">
                                <option value="">请选择</option>
                                <c:forEach var="type" items="${types}">
                                    <option value="${type.code}" ${sample.type==type.code?"selected":""}>${type.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>采集日期:</td>
                        <td><input id="collectDate" name="collectDate"
                                   value="<fmt:formatDate value="${sample.collectDate}" pattern= "yyyy-MM-dd"/>"></td>
                    </tr>
                    <tr>
                        <td>地区:</td>
                        <td>
                            <select id="province" name="province" onchange="onDataChange('province','city')">
                                <option value="">请选择</option>
                                <c:forEach var="province" items="${provinces}">
                                    <option value="${province.code}" ${sample.province==province.code?"selected":""}>${province.value}</option>
                                </c:forEach>
                            </select>
                            <select id="city" name="city" onchange="onDataChange('city','county')">
                                <option value="">请选择</option>
                                <c:forEach var="city" items="${citys}">
                                    <option value="${city.code}" ${sample.city==city.code?"selected":""}>${city.value}</option>
                                </c:forEach>
                            </select>
                            <select id="county" name="county">
                                <option value="">请选择</option>
                                <c:forEach var="county" items="${countys}">
                                    <option value="${county.code}" ${sample.county==county.code?"selected":""}>${county.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>分类:</td>
                        <td>
                            <select id="category" name="category">
                                <option value="">请选择</option>
                                <c:forEach var="category" items="${categorys}">
                                    <option value="${category.code}" ${sample.category==category.code?"selected":""}>${category.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>数量:</td>
                        <td>
                            <input id="quantity" name="quantity">
                        </td>
                    </tr>
                    <tr>
                        <td>位置:</td>
                        <td>
                            <select id="location" name="location">
                                <option value="">请选择</option>
                                <c:forEach var="location" items="${locations}">
                                    <option value="${location.code}" ${sample.location==location.code?"selected":""}>${location.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>备注:</td>
                        <td>
                            <input id="comment" name="comment">
                        </td>
                    </tr>
                    <tr>
                        <td>录入人:</td>
                        <td>
                            <input id="createPerson" name="createPerson" value="${empty sample.createPerson ? sessionScope.user.name : sample.createPerson}">
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