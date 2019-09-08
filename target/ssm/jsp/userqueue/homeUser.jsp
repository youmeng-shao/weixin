<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排队管理</title>
    <link href="${ctx}/static/css/home.css" rel="stylesheet">
    <link href="${ctx}/static/css/userQueue/queueHome.css" rel="stylesheet">
    <script>
        function deleteUserQueue(openId) {
            //函数会把传来的字符串看成变量，需要加上引号
            window.location.href="${ctx}/user/deleteUserQueue?openId="+openId
                +"&currentPage="+${currentPage};
        }
    </script>
</head>
<body>
    <%@include file="/jsp/header.jsp"%>
    <div class="home-main">
        <%@include file="/jsp/left.jsp"%>
        <div class="home-right">
            <div>
                <h2>排队管理</h2>
            </div>
            <div class="user-menus-container">
                <c:forEach items="${viewUsers}" var="user" varStatus="userStatus">
                    <div class="user-menus-item">
                        <div class="item-left">
                            <div class="left-top">
                                <div class="user-tableId">
                                    <p>餐桌号：${innerTable.get(userStatus.index)}号</p>

                                </div>
                                <div class="user-totalMoney">
                                    <p>￥${user.totalPrice}</p>
                                </div>
                            </div>
                            <div class="left-bottom">
                                <table id="restu-table">
                                <c:forEach begin="1" end="${user.menus.size()%4==0?user.menus.size()/4:user.menus.size()/4+1}"

                                        varStatus="status">
                                    <tr>
                                        <c:forEach begin="${(status.index-1)*4}" items="${user.menus}"
                                                   end="${status.index*4-1}" varStatus="tdStatus" var="menu">

                                            <td>${menu.menuName}
                                                ${user.count.get(tdStatus.index).num}
                                                份
                                            </td>

                                        </c:forEach>
                                    </tr>

                                </c:forEach>
                                </table>
                            </div>
                            
                        </div>
                        
                        <div class="item-right">
                            <button class="next-user" onclick="deleteUserQueue('${user.openId}')">
                                <p>下一位</p>
                            </button>
                        </div>
                    </div>
                </c:forEach>

                <div id="pages-container">
                    <p>第${currentPage}页</p>
                    <c:if test="${currentPage>1}">
                        <a href="${ctx}/user/findAllQueue?currentPage=${currentPage-1}">
                            <p>上一页</p>
                        </a>
                    </c:if>
                    <c:if test="${currentPage<totalPages}">
                        <a href="${ctx}/user/findAllQueue?currentPage=${currentPage+1}">
                            <p>下一页</p>
                        </a>
                    </c:if>

                    <p>共${totalPages}页</p>
                </div>
            </div>

        </div>


</body>
</html>
