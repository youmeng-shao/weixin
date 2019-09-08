<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <link href="${ctx}/static/css/home.css" rel="stylesheet">
    <link href="${ctx}/static/css/homeTable.css" rel="stylesheet">

    <script>
        function addTables() {
            window.location.href="${ctx}/jsp/addTables.jsp";
        }

    </script>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="home-main">
        <%@include file="left.jsp"%>
        <div class="home-right">
            <div>
                <h2>餐桌管理</h2>
            </div>
            <div class="add-container">
                <button id="addTables-btn" onclick="addTables()">新&nbsp;&nbsp;增</button>
            </div>
            <div class="table-container">
                <table id="restu-table">
                    <tr>
                        <th>ID</th>
                        <th>餐桌编号</th>
                        <th>餐桌类型</th>
                        <th>使用情况</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach items="${allTables}" var="table" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${table.innerTable}</td>
                            <td>
                                <c:if test="${table.categoryId==1}">
                                    1-2人
                                </c:if>
                                <c:if test="${table.categoryId==2}">
                                    3-4人
                                </c:if>
                                <c:if test="${table.categoryId==3}">
                                    5-10人
                                </c:if>
                                <c:if test="${table.categoryId==4}">
                                    10人以上
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${table.state==0}">未使用</c:if>
                                <c:if test="${table.state==1}">已使用</c:if>
                            </td>
                            <td>
                                <div id="operator">
                                    <a href="${ctx}/tables/find?tableId=${table.tableId}">
                                        编辑
                                    </a>
                                    <a href="${ctx}/tables/delete?tableId=${table.tableId}">
                                        删除
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>

        </div>
    </div>
</body>
</html>
