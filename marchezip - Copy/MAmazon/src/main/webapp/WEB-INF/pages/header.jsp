<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="en_US" scope="session"/>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<head>

    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Obaju e-commerce template">
    <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
    <meta name="keywords" content="">

    <title>
        marché : peer to peer buy sell
    </title>

    <meta name="keywords" content="">

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/owl.carousel.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/owl.theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="${pageContext.request.contextPath}/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>

    <link rel="shortcut icon" href="favicon.png">

<!-- Include Bootstrap Datepicker -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<!-- Include Datatable plugin -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables/dataTables.bootstrap.min.css" type="text/css">

<!-- Toggle Link -->
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">


</head>

<body>
    <!-- *** TOPBAR ***
 _________________________________________________________ -->
    <div id="top">
    
        <div class="container">
            <div class="col-md-6 offer" data-animate="fadeInDown">
<!--                 <a href="#" class="btn btn-success btn-sm" data-animate-hover="shake">Offer of the day</a>  <a href="#">Get flat 35% off on orders over $50!</a>
 -->            </div>
            <div class="col-md-6" data-animate="fadeInDown">
            <c:choose>
            <c:when test="${empty pageContext.request.userPrincipal.name}">
                <ul class="menu">
               
                    <li><a href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/register">Register</a>
                    </li>
                	<%-- <li><a href="${pageContext.request.contextPath}/deliveryboy/deliverables">Delivery Boy</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/customer/orders-placed">Registered User</a>
                    </li>
                     <li><a href="${pageContext.request.contextPath}/admin/view-profile">Admin User</a>
                    </li> --%>
                    
                </ul>
             </c:when>
             
             <c:when test="${fn:containsIgnoreCase(pageContext.request.userPrincipal.name, 'delivery')}">
             <ul class="menu">
                   <li style="color:white"> Welcome!!</li>                       
                    <li><a href="${pageContext.request.contextPath}/deliveryboy/account">Profile</a>
                    </li>
                   <%--  <li><a href="${pageContext.request.contextPath}/customer/inbox">Messages</a>
                    </li> --%>
                     <li><a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                    
                </ul>
             </c:when>
             
             <c:when test="${not empty pageContext.request.userPrincipal.name}">
             <ul class="menu">

                    <li style="color:white"> Welcome!!</li>                      
                    <li><a href="${pageContext.request.contextPath}/customer/account">Profile</a>
                    </li>
                   <%--  <li><a href="${pageContext.request.contextPath}/customer/inbox">Messages</a>
                    </li> --%>
                     <li><a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                    
                </ul>
             </c:when>
             </c:choose>
             
            </div>
        </div>
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
            <div class="modal-dialog modal-sm">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="Login">Customer login</h4>
                    </div>
                    <div class="modal-body">
                        <form action="customer-orders" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" id="email-modal" placeholder="email">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password-modal" placeholder="password">
                            </div>

                            <p class="text-center">
                                <button class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
                            </p>

                        </form>

                        <p class="text-center text-muted">Not registered yet?</p>
                        <p class="text-center text-muted"><a href="register"><strong>Register now</strong></a>! It is easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p>

                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- *** TOP BAR END *** -->

    <!-- *** NAVBAR ***
 _________________________________________________________ -->

    <div class="navbar navbar-default yamm" role="navigation" id="navbar">
        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand home" href="${pageContext.request.contextPath}/index" data-animate-hover="bounce">
                    <img src="${pageContext.request.contextPath}/img/logo.png" alt="Obaju logo" class="hidden-xs">
                    <img src="img/logo-small.png"  alt="Marche logo " class="visible-xs"><span class="sr-only">Marche - go to homepage</span>
                </a>
                <div class="navbar-buttons">
                
                    <!-- 
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-align-justify"></i>
                    </button>
                     -->
                    
                    <a class="btn btn-default navbar-toggle" href="shoppingCart">
                        <i class="fa fa-shopping-cart"></i>  <span class="hidden-xs">3</span>
                    </a>
                </div>
            </div>
            <!--/.navbar-header -->
            
                      
            <div class="navbar-collapse" id="navigation"  style="clear:none; !important" >

   <form class="navbar-form" role="search" action="${pageContext.request.contextPath}/searchresults">                
<div class="autocomplete" >

    <input id="myInput" class="form-control" type="text" autocomplete="off" name="myCountry" placeholder="Location">

      <select class="form-control" name="category">
                        <option value="0" selected="selected">Select Category</option>
                        <option value="1">Mobiles</option>
                        <option value="5">Women Accessories</option>
                        <option value="6">Men Accessories</option>
                        <option value="2">Home Appliances</option>
                        <option value="8">Books</option>
                        <option value="7">Computer Accessories</option>
                        <option value="9">Others</option>
                     </select>   
  </div> 
                   
                                          
                

            </div>
            <!--/.nav-collapse -->

            <div class="navbar-collapse" id="search" style="clear:none; !important" >

             

<div class="autocompletesearch">
                                     
                    <div class="input-group" >
                        <input id="mySearch" autocomplete="off" type="text" name="name" class="form-control" placeholder="Search">
                        <span class="input-group-btn">
                			 <!-- <a class="btn btn-primary btn-default" href="searchresults"><i class="fa fa-search"></i></a>
             		   -->
             		   <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
             		     </span>
                    </div>
  </div> 
                     
                 </form>

            </div>
            <!--/.nav-collapse -->

            <div class="navbar-buttons">

                <div class="navbar-collapse collapse right" id="basket-overview">
                    <a href="${pageContext.request.contextPath}/shoppingCart" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm"><b> ${cartObj.cartLines.size()} </b></span></a>
                </div>
                <!--/.nav-collapse -->

            </div>

        </div>
        <!-- /.container -->
    </div>
    <!-- /#navbar -->

    <!-- *** NAVBAR END *** -->