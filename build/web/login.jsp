<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Watch shop | eCommers</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">


        <!-- CSS here -->
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
            <!-- Hero Area Start-->
            <div class="slider-area ">
                <div class="single-slider slider-height2 d-flex align-items-center">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="hero-cap text-center">
                                    <h2>Login</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Hero Area End-->

            <!--================login_part Area =================-->
            <section class="login_part section_padding">
                <div class="container">
                    <div class="row align-items-center">
                        <!-- Left text section -->
                        <div class="col-lg-6 col-md-6">
                            <div class="login_part_text text-center">
                                <div class="login_part_text_iner">
                                    <h2>New to our Shop?</h2>
                                    <p>There are advances being made in science and technology
                                        everyday, and a good example of this is the</p>
                                    <a href="register.jsp" class="btn_3">Create an Account</a>
                                </div>
                            </div>
                        </div>

                        <!-- Login form section -->
                        <div class="col-lg-6 col-md-6">
                            <div class="login_part_form">
                                <div class="login_part_form_iner">
                                    <h3>Welcome Back! <br>Please Sign in now</h3>
                                    <form class="row contact_form" action="MainController" method="post" novalidate="novalidate">
                                        <!-- Add action hidden input -->
                                        <input type="hidden" name="action" value="login" />

                                        <!-- Username -->
                                        <div class="col-md-12 form-group p_star">
                                            <input type="text" class="form-control" id="name" name="strUserName"
                                                   placeholder="Username" required>
                                        </div>

                                        <!-- Password -->
                                        <div class="col-md-12 form-group p_star">
                                            <input type="password" class="form-control" id="password" name="strPassword"
                                                   placeholder="Password" required>
                                        </div>

                                        <!-- Remember me -->
                                        <div class="col-md-12 form-group">
                                            <div class="creat_account d-flex align-items-center">
                                                <input type="checkbox" id="f-option" name="selector">
                                                <label for="f-option">Remember me</label>
                                            </div>

                                            <!-- Submit -->
                                            <button type="submit" class="btn_3">Log in</button>
                                            <a class="lost_pass" href="#">Forget password?</a>
                                        </div>
                                    </form>

                                    <!-- Message display -->
                                    <c:if test="${not empty message}">
                                        <div style="color:red;">${message}</div>
                                    </c:if>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--================login_part end =================-->
        </main>
        <jsp:include page="footer.jsp" />

        <!--? Search model Begin -->
        <div class="search-model-box">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-btn">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Searching key.....">
                </form>
            </div>
        </div>
        <!-- Search model end -->

        <!-- JS here -->

        <script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>
        <!-- Jquery, Popper, Bootstrap -->
        <script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="./assets/js/popper.min.js"></script>
        <script src="./assets/js/bootstrap.min.js"></script>
        <!-- Jquery Mobile Menu -->
        <script src="./assets/js/jquery.slicknav.min.js"></script>

        <!-- Jquery Slick , Owl-Carousel Plugins -->
        <script src="./assets/js/owl.carousel.min.js"></script>
        <script src="./assets/js/slick.min.js"></script>

        <!-- One Page, Animated-HeadLin -->
        <script src="./assets/js/wow.min.js"></script>
        <script src="./assets/js/animated.headline.js"></script>

        <!-- Scroll up, nice-select, sticky -->
        <script src="./assets/js/jquery.scrollUp.min.js"></script>
        <script src="./assets/js/jquery.nice-select.min.js"></script>
        <script src="./assets/js/jquery.sticky.js"></script>
        <script src="./assets/js/jquery.magnific-popup.js"></script>

        <!-- contact js -->
        <script src="./assets/js/contact.js"></script>
        <script src="./assets/js/jquery.form.js"></script>
        <script src="./assets/js/jquery.validate.min.js"></script>
        <script src="./assets/js/mail-script.js"></script>
        <script src="./assets/js/jquery.ajaxchimp.min.js"></script>

        <!-- Jquery Plugins, main Jquery -->	
        <script src="./assets/js/plugins.js"></script>
        <script src="./assets/js/main.js"></script>

    </body>

</html>