<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/4/27
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑餐桌</title>
    <link href="${ctx}/static/css/home.css" rel="stylesheet">
    <link href="${ctx}/static/css/editTable.css" rel="stylesheet">
    <script>
        function saveTable(){
            var tableForm = document.getElementById("tableUpdata");
            tableForm.action="${ctx}/tables/update";
            tableForm.submit();
        }
        function backTable() {
            window.location.href="${ctx}/tables/findAll";
            return false;
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
                <div class="edit-table">
                    <form method="post" id="tableUpdata">
                        <input type="hidden" name="tableId" value="${findTable.tableId}">
                        <b>餐桌编号:</b>
                        <input type="text" name="innerTable" value="${findTable.innerTable}"/>
                        <b>餐桌类型:</b>
                        <select id="select-category" name="categoryId">
                            <option value="1">1-2人</option>
                            <option value="2">3-4人</option>
                            <option value="3">5-10人</option>
                            <option value="4">10人以上</option>
                        </select>
                        <b>使用情况:</b>
                        <select id="select-state" name="state">
                            <option value="0">未使用</option>
                            <option value="1">已使用</option>
                        </select>
                        <button id="saveTable-button" onclick="saveTable()">保&nbsp;&nbsp;存</button>

                    </form>
                    <button id="back-button" onclick="backTable()">返&nbsp;&nbsp;回</button>
                </div>
            </div>
    </div>
</body>
</html>
