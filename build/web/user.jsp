<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="css/user.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <section id="user">
                <div class="bg">
                    <img src="images/backgrounds/checkout_bg.jpg" alt=""/>
                </div>
                <div class="user--info">
                    <div class="user--info__title">User Information</div>
                    <form action="main" method="POST">
                        <div class="info--left">
                            <label class="info--lable" for="userid">User ID </label>
                            <label class="info--lable" for="username">Username</label>
                            <label class="info--lable" for="password">Password</label>
                            <label class="info--lable" for="role">Role ID</label>
                            <label class="info--lable" for="email">Email</label>
                            <label class="info--lable" for="phone">Phone</label>
                            <label class="info--lable" for="address">Address</label>
                        </div>
                        <div class="info--right">
                            <input class="info--input__text" type="text" id="userid" name="userID" value="${sessionScope.LOGIN_USER.id}" readonly=""/>
                        <input class="info--input__text" type="text" id="username" name="username" value="${sessionScope.LOGIN_USER.username}"/>
                        <input class="info--input__text" type="text" id="password" value="*********" readonly=""/>
                        <input class="info--input__text" type="text" id="role" name="roleID" value="${sessionScope.LOGIN_USER.roleID}" readonly=""/>
                        <input class="info--input__text" type="text" id="email" name="email" value="${sessionScope.LOGIN_USER.email}" readonly=""/>
                        <input class="info--input__text" type="text" id="phone" name="phone" value="${sessionScope.LOGIN_USER.phone}" />
                        <input class="info--input__text" type="text" id="address" name="address" value="${sessionScope.LOGIN_USER.address}" />
                    </div>
                    <div class="form--button">
                        <input type="text" name="action" value="" hidden=""/>
                        <div class="form--submit__btn" onclick="submit('UpdateUser')">Update Infomation</div>
                    </div>
                </form>
            </div>
        </section>
        <c:import url="footer.html"></c:import>
        <script>
            const submit = (value) => {
                const input = document.querySelector("input[name='action']");
                input.value = value;
                const form = document.querySelector("form");
                form.submit();
            };
        </script>
    </body>
</html>
