<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../header.jsp" />


    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>My wishlist</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** CUSTOMER MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">User Actions</h3>
                        </div>

                          <jsp:include page="options.jsp" />
                       

                    </div>
                    <!-- /.col-md-3 -->

                    <!-- *** CUSTOMER MENU END *** -->
                </div>

                <div class="col-md-9" id="wishlist">

                   
                    <div class="box">
                        <h1>My wishlist</h1>
                       
                    </div>

                    <div class="row products">
									<c:if test="${empty productInfos}">
                                    <div>No products available</div>
                                    </c:if>
                        <c:forEach items="${productInfos}" var="info">
                        <div class="col-md-3 col-sm-4">
                            <div class="product">
                                <div class="flip-container">
                                    <div class="flipper">
                                        <div class="front">
                                            <a href="<c:url value="../detail?id=${info.id}"/>">
                                                <img src="${pageContext.request.contextPath}/productImage?id=${info.id}" alt="${info.name}" class="img-responsive">
                                            </a>
                                        </div>
                                       
                                    </div>
                                </div>
                               
                                <div class="text">
                                    <h3><a href="../detail.jsp">${info.name}</a></h3>
                                    <p class="price"><fmt:formatNumber value="${info.price}" type="currency"/></p>
                                    <p class="buttons">
                                        <a href="../detail?id=${info.id}" class="btn btn-default">View detail</a>
                                        <a href="../addtoCart?id=${info.id}" class="btn btn-primary"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                    </p>
                                </div>
                                <!-- /.text -->
                            </div>
                            <!-- /.product -->
                        </div>
 						</c:forEach>
                    </div>
                    <!-- /.products -->

                </div>
            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
    <jsp:include page="../footer.jsp" />
    
    </div>
    <!-- /#all -->


    

    <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
   

</body>

</html>
