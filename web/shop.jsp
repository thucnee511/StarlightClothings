<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/loading.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="css/shop.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <section id="shop">
                <div class="container shop">
                    <div class="shop--top">
                        <div class="errorMsg">
                        ${errorMsg}
                    </div>
                    <div class="successMsg">
                        ${successMsg}
                    </div>
                    <div class="category">
                        <ul class="category--list">
                            <li class="category--list__item">
                                <a href="main?action=Shop&cateID=0" class="category--list__link ${cate != 0 ? "" : "actived"}">All</a>
                            </li>
                            <c:forEach items="${CATEGORY_LIST}" var="o">
                                <li class="category--list__item">
                                    <a href="main?action=Shop&cateID=${o.cateID}" class="category--list__link ${cate == o.cateID ? "actived" : ""}">${o.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="shop--bottom">
                    <div class="products">
                        <form id="search--form" action="main" method="POST">
                            <div class="search--bar">
                                <input class="search--input" type="text" name="search" value="${SEARCH_VALUE}" required=""/>
                                <input type="text" name="action" value="ShopSearch" hidden=""/>
                                <input type="text" name="cateID" value="${cate}" hidden/>
                                <div class="search--icon" onclick="submit()">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                    </svg>
                                </div>
                            </div>
                        </form>
                        <ul class="product--list">
                            <c:forEach items="${PRODUCT_LIST}" var="o">
                                <li class="product--list__item" cate-id="${o.cateID}">
                                    <div class="product--card">
                                        <div class="product--card__image">
                                            <img src="${o.image}" alt=""/>
                                        </div>
                                        <div class="product--card__info">
                                            <p class="product--card__name">${o.name}</p>
                                            <p class="product--card__price">${o.price} $</p>
                                        </div>
                                        <form action="main" method="POST">
                                            <input type="text" name="cateID" value="${cate}" hidden/>
                                            <input type="text" name="productID" value="${o.productID}" hidden/>
                                            <div class="product--cart__add">
                                                <div class="product--cart__quantity">
                                                    <p class="product--add__text">Quantity: </p>
                                                    <div id="dec" class="product--card__button" onClick="decreaseQuantity(${o.productID})">-</div>
                                                    <input data-id="${o.productID}" id="" type="number" name="quantity" value="0" min="0" max="${o.quantity}" readonly/>
                                                    <div id="inc" class="product--card__button" onClick="increaseQuantity(${o.productID})">+</div>
                                                </div>
                                                <input type="submit" name="action" value="Add to Cart"/>
                                            </div>
                                        </form>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="footer.html"></c:import>
        <script src="js/shop.js" type="text/javascript"></script>
    </body>
</html>
