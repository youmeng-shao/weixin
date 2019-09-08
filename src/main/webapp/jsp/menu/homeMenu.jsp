    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
    <link href="${ctx}/static/css/home.css" rel="stylesheet">
    <link href="${ctx}/static/css/menu/homeMenu.css" rel="stylesheet">
    <script>
<%--        跳转到添加页面--%>
        function addMenuJsp() {
            window.location.href="${ctx}/jsp/menu/addMenu.jsp"
        }
        // 根据名字进行查询
        function menuNameForm() {
            var menuForm = document.getElementById("menu-form");
            menuForm.action="${ctx}/menu/findByName";
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
            <div class="menu-table-header">

                <form method="post" id="menu-form">
                    <b>菜名：</b>
                    <input name="likeMenuName" id="menu-name"/>
                    <button id="menu-search" class="menu-botton" onclick="menuNameForm()">搜&nbsp;&nbsp;索</button>
                </form>
                <button id="add-menu" class="menu-botton" onclick="addMenuJsp()">新&nbsp;&nbsp;增</button>
            </div>
            <div id="menu-container">
                <table id="restu-table">
                    <tr>
                        <th>ID</th>
                        <th>菜名</th>
                        <th>图片</th>
                        <th>价格</th>
                        <th>上架情况</th>
                        <th>操作</th>
                    </tr>

                        <c:forEach items="${menuList}" var="menu" varStatus="status">
                            <tr>
                                <td>${(currentPage-1)*pageCounts+status.count}</td>
                                <td>${menu.menuName}</td>
                                <td>
                                    <img src="${ctx}/images/${menu.image}" alt="您还没上传图片" class="menu-img">
                                </td>
                                <td>${menu.price}</td>
                                <td>${menu.statue==1?'已上架':'未上架'}</td>
                                <td>
                                    <div id="operator">
                                        <a href="${ctx}/menu/find?menuId=${menu.menuId}">
                                        编辑
                                        </a>
                                        <a href="${ctx}/menu/delete?menuId=${menu.menuId}">
                                            删除
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                </table>
                <div id="pages-container">
                <p>第${currentPage}页</p>
                <c:if test="${currentPage>1}">
                    <c:choose>
                        <c:when test="${isLikeName==1}">
                            <a href="${ctx}/menu/findByName?likeNameCurrentPage=${currentPage-1}">
                            <p>上一页</p>
                            </a>
                        </c:when>

                        <c:otherwise>
                            <a href="${ctx}/menu/currentPageMenus?currentPage=${currentPage-1}">
                            <p>上一页</p>
                            </a>
                        </c:otherwise>

                    </c:choose>
                </c:if>
                <c:if test="${totalPages>currentPage}">
                    <c:choose>
                        <c:when test="${isLikeName==1}">
                            <a href="${ctx}/menu/findByName?likeNameCurrentPage=${currentPage+1}">
                            <p>下一页</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${ctx}/menu/currentPageMenus?currentPage=${currentPage+1}">
                            <p>下一页</p>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <p>共${totalPages}页</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
