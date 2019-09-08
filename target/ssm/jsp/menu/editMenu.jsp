<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/4/29
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新菜单</title>
    <link href="${ctx}/static/css/home.css" rel="stylesheet">
    <link href="${ctx}/static/css/menu/editMenu.css" rel="stylesheet">
    <script>
        function backMenu() {
            var menuForm = document.getElementById("menuEdit");
            menuForm.action="${ctx}/menu/currentPageMenus";
            menuForm.submit();
        }
        function editMenu() {
            var menuForm = document.getElementById("menuEdit");
            menuForm.action="${ctx}/menu/update";
            menuForm.submit();
        }
    </script>

</head>
<body>
    <%@include file="/jsp/header.jsp"%>
    <div class="home-main">
        <%@include file="/jsp/left.jsp"%>
        <div class="home-right">
            <div>
                <h2>餐桌管理</h2>
            </div>
            <div class="edit-menu">
                <form method="post" id="menuEdit" enctype="multipart/form-data">
                    <input type="hidden" name="menuId" value="${findMenu.menuId}">
                    <b>菜名:</b>
                    <input type="text" name="menuName" id="menu-name" value="${findMenu.menuName}"/>

                    <b>图片:</b>
                    <input type="file" name="file" id="menu-file"/>
                    <input type="hidden" name="image" value="${findMenu.image}">

                    <b>价格:</b>
                    <input type="text" name="price" id="menu-price" value="${findMenu.price}"/>

                    <b>上架情况:</b>
                    <select id="select-statue" name="statue">
                        <option value="0">未上架</option>
                        <option value="1">已上架</option>
                    </select>

                    <button id="editMenu-button" onclick="editMenu()">保&nbsp;&nbsp;存</button>
                    <button id="back-button" onclick="backMenu()">返&nbsp;&nbsp;回</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
