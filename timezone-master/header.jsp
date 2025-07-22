<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <!-- Header Start -->
    <div class="header-area">
        <div class="main-header header-sticky">
            <div class="container-fluid">
                <div class="menu-wrapper">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="home.jsp"><img src="assets/img/logo/logo.png" alt=""></a>
                    </div>

                    <!-- Main-menu -->
                    <div class="main-menu d-none d-lg-block">
                        <nav>
                            <ul id="navigation">
                                <li><a href="home.jsp">Home</a></li>
                                <li><a href="shop.jsp">Shop</a></li>
                                <li><a href="about.jsp">About</a></li>
                                <li class="hot"><a href="#">Latest</a>
                                    <ul class="submenu">
                                        <li><a href="shop.jsp">Product List</a></li>
                                        <li><a href="product_details.jsp">Product Details</a></li>
                                    </ul>
                                </li>
                                <li><a href="blog.jsp">Blog</a>
                                    <ul class="submenu">
                                        <li><a href="blog.jsp">Blog</a></li>
                                        <li><a href="blog-details.jsp">Blog Details</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">Pages</a>
                                    <ul class="submenu">
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="cart.jsp">Cart</a></li>
                                        <li><a href="elements.jsp">Element</a></li>
                                        <li><a href="confirmation.jsp">Confirmation</a></li>
                                        <li><a href="checkout.jsp">Product Checkout</a></li>
                                    </ul>
                                </li>
                                <li><a href="contact.jsp">Contact</a></li>
                            </ul>
                        </nav>
                    </div>

                    <!-- Header Right -->
                    <div class="header-right">
                        <ul>
                            <!-- Search -->
                            <li>
                                <form action="products" method="get">
                                    <input type="hidden" name="action" value="search" />
                                    <input type="text" name="keyword" placeholder="Search..." style="padding: 5px;" />
                                    <button type="submit" style="background: none; border: none;">
                                        <span class="flaticon-search"></span>
                                    </button>
                                </form>
                            </li>

                            <!-- User Dropdown -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="flaticon-user"></span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="userDropdown">
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.user}">
                                            <span class="dropdown-item">Welcome, ${sessionScope.user.name}</span>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="logout.jsp">Logout</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="dropdown-item" href="login.jsp">Login</a>
                                            <a class="dropdown-item" href="register.jsp">Register</a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </li>

                            <!-- Cart -->
                            <li><a href="cart.jsp"><span class="flaticon-shopping-cart"></span></a></li>
                        </ul>
                    </div>
                </div>

                <!-- Mobile Menu -->
                <div class="col-12">
                    <div class="mobile_menu d-block d-lg-none"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->
</header>
