<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Register | Watch Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/flaticon.css">
    <link rel="stylesheet" href="assets/css/slicknav.css">
    <link rel="stylesheet" href="assets/css/animate.min.css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/css/themify-icons.css">
    <link rel="stylesheet" href="assets/css/slick.css">
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<jsp:include page="header.jsp" />

<main>
    <!-- Hero Start -->
    <div class="slider-area">
        <div class="single-slider slider-height2 d-flex align-items-center">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="hero-cap text-center">
                            <h2>Register</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Register Section -->
    <section class="login_part section_padding">
        <div class="container">
            <div class="row align-items-center">
                <!-- Left text -->
                <div class="col-lg-6 col-md-6">
                    <div class="login_part_text text-center">
                        <div class="login_part_text_iner">
                            <h2>Already have an account?</h2>
                            <p>Sign in to shop faster and easier.</p>
                            <a href="login.jsp" class="btn_3">Login</a>
                        </div>
                    </div>
                </div>

                <!-- Register Form -->
                <div class="col-lg-6 col-md-6">
                    <div class="login_part_form">
                        <div class="login_part_form_iner">
                            <h3>Create Your Account</h3>
                            <form class="row contact_form" action="UserController" method="post" novalidate="novalidate">
                                <input type="hidden" name="action" value="register" />

                                <div class="col-md-12 form-group p_star">
                                    <input type="text" class="form-control" name="name" placeholder="Full Name" required>
                                </div>

                                <div class="col-md-12 form-group p_star">
                                    <input type="email" class="form-control" name="email" placeholder="Email Address" required>
                                </div>

                                <div class="col-md-12 form-group p_star">
                                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                                </div>

                                <div class="col-md-12 form-group p_star">
                                    <input type="text" class="form-control" name="address" placeholder="Address">
                                </div>

                                <div class="col-md-12 form-group p_star">
                                    <input type="text" class="form-control" name="phoneNumber" placeholder="Phone Number">
                                </div>

                                <div class="col-md-12 form-group">
                                    <button type="submit" class="btn_3">Register</button>
                                </div>

                                <c:if test="${not empty message}">
                                    <div class="col-md-12" style="color:red;">${message}</div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<jsp:include page="footer.jsp" />

<!-- JS Scripts -->
<script src="assets/js/vendor/jquery-1.12.4.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.slicknav.min.js"></script>
<script src="assets/js/owl.carousel.min.js"></script>
<script src="assets/js/slick.min.js"></script>
<script src="assets/js/wow.min.js"></script>
<script src="assets/js/jquery.nice-select.min.js"></script>
<script src="assets/js/jquery.scrollUp.min.js"></script>
<script src="assets/js/jquery.sticky.js"></script>
<script src="assets/js/jquery.magnific-popup.js"></script>
<script src="assets/js/plugins.js"></script>
<script src="assets/js/main.js"></script>

</body>
</html>

