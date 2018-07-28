<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />


    <div id="all">

        <div id="content">

           

            <!-- *** ADVANTAGES HOMEPAGE ***
 _________________________________________________________ -->
            <div id="advantages">

                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="box same-height clickable">
                               <img src="img/c1.jpg" style="width:100%"/>
                                <h3><a href="category?id=1">Mobiles</a></h3>
                                <p>Get wide range of latest Mobiles in best price.</p>
                            </div>
                        </div>

                         <div class="col-sm-4">
                            <div class="box same-height clickable">
                               <img src="img/c2.jpg" style="width:100%"/>
                                <h3><a href="category?id=5">Womens Accessories</a></h3>
                                <p>Get wide range of latest Womens Accessories in best price.</p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="box same-height clickable">
                               <img src="img/c6.jpg" style="width:100%"/>
                                <h3><a href="category?id=6">Mens Accessories</a></h3>
                                <p>Get wide range of Mens Accessories in best price.</p>
                            </div>
                        </div>
                          </div>
                           <div class="same-height-row">
                         <div class="col-sm-4">
                            <div class="box same-height clickable">
                               <img src="img/c3.jpg" style="width:100%"/>
                                <h3><a href="category?id=2">Home Appliances</a></h3>
                                <p>Get wide range of latest Home Appliances in best price.</p>
                            </div>
                        </div>
                        
                         <div class="col-sm-4">
                            <div class="box same-height clickable">
                               <img src="img/c4.jpg" style="width:100%"/>
                                <h3><a href="category?id=8">Books</a></h3>
                                <p>Get wide range of latest Books in best price.</p>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="box same-height clickable">
                               <img src="img/c5.jpg" style="width:100%"/>
                                <h3><a href="category?id=7">Computer Accessories</a></h3>
                                <p>Get wide range of Computer Accessories in best price.</p>
                            </div>
                        </div>
                        
                       
                        
                    </div>
                    <!-- /.row -->
                    

                </div>
                <!-- /.container -->

            </div>
            <!-- /#advantages -->

            <!-- *** ADVANTAGES END *** -->
                       
        <!-- /#Relevant Products of a Customer-->
            
            
             <c:choose>
            <c:when test="${not empty pageContext.request.userPrincipal.name}">
             <div id="hot">

                <div class="box">
                    <div class="container">
                        <div class="col-md-12">
                            <h2>Products you may be interested in</h2>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="product-slider">
                    <c:set var="cp" value="0" scope="page" />
                        	<c:forEach items="${RecommandedProducts}" var="info">
                        <c:if test = "${cp <5 }">
                        <div class="item">
                            <div class="product">
                                <div class="flip-container">
                                    <div class="flipper">
                                        <div class="front">
                                            <a href="detail?id=${info.id}">
                                                <img src="${pageContext.request.contextPath}/productImage?id=${info.id}" alt="" class="img-responsive">
                                            </a>
                                        </div>
                                      
                                    </div>
                                </div>
                               
                                <div class="text">
                                    <h3><a href="detail?id=${info.id}" class="pname">${info.name}</a></h3>
                                    <p class="price"> <fmt:formatNumber value="${info.price}" type="currency"/> </p>
                                </div>
                                <!-- /.text -->
                            </div>
                            <!-- /.product -->
                        </div>

                                                <c:set var="cp" value="${cp + 1}" scope="page"/>
                        
                        </c:if>
                         </c:forEach>
               
                       

                    </div>
                    <!-- /.product-slider -->
                </div>
                <!-- /.container -->

            </div>
            
            </c:when>
            </c:choose>
            
                        <!-- /#Relevant Products -->
            

            <!-- *** HOT PRODUCT SLIDESHOW ***
 _________________________________________________________ -->
            <div id="hot">

                <div class="box">
                    <div class="container">
                        <div class="col-md-12">
                            <h2>Recently Added</h2>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="product-slider">
                        	<c:forEach items="${LatestProducts}" var="info">
                        
                        <div class="item">
                            <div class="product">
                                <div class="flip-container">
                                    <div class="flipper">
                                        <div class="front">
                                            <a href="detail?id=${info.id}">
                                                <img src="${pageContext.request.contextPath}/productImage?id=${info.id}" alt="" class="img-responsive">
                                            </a>
                                        </div>
                                      
                                    </div>
                                </div>
                               
                                <div class="text">
                                    <h3><a href="detail?id=${info.id}" class="pname">${info.name}</a></h3>
                                    <p class="price"> <fmt:formatNumber value="${info.price}" type="currency"/> </p>
                                </div>
                                <!-- /.text -->
                            </div>
                            <!-- /.product -->
                        </div>

                         </c:forEach>
               
                       

                    </div>
                    <!-- /.product-slider -->
                </div>
                <!-- /.container -->

            </div>
            <!-- /#hot -->

         


        </div>
        <!-- /#content -->

        <!-- *** FOOTER *** -->
 <jsp:include page="footer.jsp" />
 

    </div>
    <!-- /#all -->


    

   

</body>

</html>