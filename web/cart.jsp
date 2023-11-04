<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="css/cart.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID != 1}">
            <c:redirect url="error.jsp"></c:redirect>
        </c:if>
        <jsp:include page="header.jsp"></jsp:include>
            <section id="cart">
                <div class="container">
                    <div class="cart--top">
                        <div class="errorMsg">
                        ${errorMsg}
                    </div>
                    <div class="successMsg">
                        ${successMsg}
                    </div>
                </div>

                <div class="cart--left">
                    <p class="card--left__title">Your cart</p>
                    <ul class="product--list">
                        <li class="product--list__card title">
                            <p class="product--card__number">No.</p>
                            <p class="product--card__info">Information</p>
                            <p class="product--card__quantity">Quantity</p>
                            <p class="product--card__price">Unit Price</p>
                            <p class="product--card__total">Total</p>
                            <p class="product--card__update">Update</p>
                            <p class="product--card__remove">Delete</p>
                        </li>
                        <c:forEach items="${CART_PRODUCT_LIST}" var="o">
                            <li class="product--list__card">
                                <form data-id="${o.productID}" class="product--form" action="main" method="POST">
                                    <p class="product--card__number">1</p>
                                    <div class="product--card__info">
                                        <div class="product--card__image">
                                            <img src="${o.image}" alt=""/>
                                        </div>
                                        <p class="product--card__name">${o.name}</p>
                                    </div>
                                    <div class="product--card__quantity">
                                        <div id="dec" class="product--card__button" onClick="decreaseQuantity(${o.productID})">-</div>
                                        <input data-id="${o.productID}" id="" type="number" name="quantity" value="${o.quantity}" min="1" max="50" readonly/>
                                        <div id="inc" class="product--card__button" onClick="increaseQuantity(${o.productID})">+</div>
                                    </div>
                                    <p class="product--card__price">${o.price}$</p>
                                    <p class="product--card__total">${o.price * o.quantity}$</p>
                                    <input action-id="${o.productID}" type="text" name="action" value="" hidden=""/>
                                    <div class="product--card__update" onClick="submit('UpdateCart', ${o.productID})">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </div>
                                    <div class="product--card__remove" onClick="submit('DeleteCart', ${o.productID})">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eraser" viewBox="0 0 16 16">
                                        <path d="M8.086 2.207a2 2 0 0 1 2.828 0l3.879 3.879a2 2 0 0 1 0 2.828l-5.5 5.5A2 2 0 0 1 7.879 15H5.12a2 2 0 0 1-1.414-.586l-2.5-2.5a2 2 0 0 1 0-2.828l6.879-6.879zm2.121.707a1 1 0 0 0-1.414 0L4.16 7.547l5.293 5.293 4.633-4.633a1 1 0 0 0 0-1.414l-3.879-3.879zM8.746 13.547 3.453 8.254 1.914 9.793a1 1 0 0 0 0 1.414l2.5 2.5a1 1 0 0 0 .707.293H7.88a1 1 0 0 0 .707-.293l.16-.16z"/>
                                        </svg>
                                    </div>
                                    <input type="text" name="productID" value="${o.productID}" hidden=""/>
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="cart--right">
                    <p class="cart--right__title">Billing information</p>
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
                                <p class="billing--card__item">${o.price * o.quantity}$</p>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="billing--total">
                        <p class="billing--total__text">Total</p>
                        <p class="billing--total__price">${CART_TOTAL}$</p>
                    </div>
                    <c:if test="${CART_TOTAL > 0}">
                        <form action="main" method="POST">
                            <input type="text" name="orderTotal" value="${CART_TOTAL}" hidden=""/>
                            <input type="submit" name="action" value="Checkout"/>
                        </form>
                    </c:if>
                </div>
            </div>
        </section>
        <jsp:include page="footer.html"></jsp:include>
        <script src="js/cart.js" type="text/javascript"></script>
    </body>
</html>
