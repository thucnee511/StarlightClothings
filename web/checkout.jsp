<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successfully checkout</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/checkout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <section id="checkout">
                <div class="bg">
                    <img src="images/backgrounds/checkout_bg.jpg" alt=""/>
                </div>
                <div class="billing--info">
                    <p class="billing--info__title">Successfully Checkout</p>
                    <p class="billing--info__subtitle">Billing information</p>
                    <ul class="billing--list">
                        <li class="billing--list__card title">
                            <p class="billing--card__item title">Product</p>
                            <p class="billing--card__item title">Quantity</p>
                            <p class="billing--card__item title">Price</p>
                        </li>
                    <c:forEach items="${CART_PRODUCT_LIST}" var="o">
                        <li class="billing--list__card ">
                            <p class="billing--card__item">${o.name}</p>
                            <p class="billing--card__item">${o.quantity}</p>
                            <p class="billing--card__item">${o.price}$</p>
                        </li>
                    </c:forEach>
                </ul>
                <div class="billing--total">
                    <p class="billing--total__text">Total</p>
                    <p class="billing--total__price">${ORDER_TOTAL}$</p>
                </div>
                <form action="main">
                    <input type="submit" value="Back to Homepage"/>
                </form>
            </div>
        </section>
        <c:import url="footer.html"></c:import>
    </body>
</html>
