<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="css/admin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID != 2}">
            <c:redirect url="error.jsp"></c:redirect>
        </c:if>
        <c:import url="header.jsp"></c:import>
            <section id="user">
                <div class="container">
                    <div class="errorMsg">${errorMsg}</div>
                <p class="user--title">User info</p>
                <form id="search--form" action="main" method="POST">
                    <div class="search--bar">
                        <input class="search--input" type="text" name="search" value="${SEARCH_VALUE}" required=""/>
                        <input type="text" name="action" value="UserSearch" hidden=""/>
                        <div class="search--icon" onclick="submitSearch()">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                            </svg>
                        </div>
                    </div>
                </form>
                <ul class="user--list">
                    <li class="user--list__card title">
                        <p class="user--card__number">No.</p>
                        <p class="user--card__username">Username</p>
                        <p class="user--card__email">Email</p>
                        <p class="user--card__phone">Phone</p>
                        <p class="user--card__address">Address</p>
                        <p class="user--card__role">Role</p>
                        <p class="user--card__update">Update</p>
                        <p class="user--card__remove">Delete</p>
                    </li>
                    <li class="user--list__card">
                        <c:forEach items="${USER_LIST}" var="o" varStatus="counter">
                            <form data-id="${counter.count}" class="user--form" action="main" method="POST">
                                <input type="text" name="userID" value="${o.id}" hidden=""/>
                                <input type="text" class="user--card__id" value="${counter.count}" readonly=""/>
                                <input type="text" class="user--card__name" name="username" value="${o.username}" required=""/>
                                <input type="text" class="user--card__email" name="email" value="${o.email}" readonly=""/>
                                <input type="text" class="user--card__phone" name="phone" value="${o.phone}" required=""/>
                                <input type="text" class="user--card__address" name="address" value="${o.address}" required=""/>
                                <input type="text" class="user--card__role" name="roleID" value="${o.roleID}" required=""/>
                                <input action-id="${counter.count}" type="text" name="action" value="" hidden=""/>
                                <div class="user--card__update" onClick="submit('UpdateUser', ${counter.count})">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </div>
                                <div class="user--card__remove" onClick="submit('DeleteUser', ${counter.count})">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eraser" viewBox="0 0 16 16">
                                    <path d="M8.086 2.207a2 2 0 0 1 2.828 0l3.879 3.879a2 2 0 0 1 0 2.828l-5.5 5.5A2 2 0 0 1 7.879 15H5.12a2 2 0 0 1-1.414-.586l-2.5-2.5a2 2 0 0 1 0-2.828l6.879-6.879zm2.121.707a1 1 0 0 0-1.414 0L4.16 7.547l5.293 5.293 4.633-4.633a1 1 0 0 0 0-1.414l-3.879-3.879zM8.746 13.547 3.453 8.254 1.914 9.793a1 1 0 0 0 0 1.414l2.5 2.5a1 1 0 0 0 .707.293H7.88a1 1 0 0 0 .707-.293l.16-.16z"/>
                                    </svg>
                                </div>
                            </form>
                        </c:forEach>
                    </li>
                </ul>
            </div>
        </section>
        <c:import url="footer.html"></c:import>
        <script src="js/admin.js" type="text/javascript"></script>
    </body>
</html>
