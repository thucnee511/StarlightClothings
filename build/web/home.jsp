<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
              crossorigin="anonymous"
              referrerpolicy="no-referrer" />
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
        <title>Home Page</title>
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <section id="hero">
                <div class="hero--bg">
                    <img src="images/hero/hero.jpg" alt=""/>
                </div>
                <div class="container">
                    <div class="hero--content">
                        <p class="hero--content__minititle">NEW ARRIVAL COLLECTIONS</p>
                        <p class="hero--content__title">Autumn - Winter 2023 Collection</p>
                        <p class="hero--content__description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec et metus lectus. Etiam volutpat id est sit amet consequat. Suspendisse commodo metus odio, ac lobortis velit interdum ut.</p>
                        <div class="hero--content__button"><a href="main?action=Shop">SHOP NOW</a></div>
                    </div>
                </div>
            </section>
        <c:import url="footer.html"></c:import>
    </body>
</html>
