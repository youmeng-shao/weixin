
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单添加</title>
    <link href="${ctx}/static/css/home.css" rel="stylesheet">
    <link href="${ctx}/static/css/menu/addMenu.css" rel="stylesheet">
    <script>
        function addMenu() {
            var menuForm = document.getElementById("menuAdd");
            menuForm.action="${ctx}/menu/add";
            menuForm.submit();
        }
        function backMenu() {
            var menuForm = document.getElementById("menuAdd");
            menuForm.action="${ctx}/menu/currentPageMenus";
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
                <h2>菜单管理</h2>
            </div>

            <div class="add-menu">
                <form method="post" id="menuAdd" enctype="multipart/form-data">
                    <input type="hidden" name="menuId" value="1">
                    <b>菜名:</b>
                    <input type="text" name="menuName" id="menu-name"/>

                    <b>图片:</b>
                    <input type="file" name="file" id="menu-file"/>
                    <input type="hidden" name="image" value="caipin">

                    <b>价格:</b>
                    <input type="text" name="price" id="menu-price"/>

                    <b>上架情况:</b>
                    <select id="select-statue" name="statue">
                    <option value="0">未上架</option>
                    <option value="1">已上架</option>
                    </select>

                    <button id="addMenu-button" onclick="addMenu()">保&nbsp;&nbsp;存</button>
                    <button id="back-button" onclick="backMenu()">返&nbsp;&nbsp;回</button>
                </form>

            </div>

        </div>
    </div>
</body>
</html>
