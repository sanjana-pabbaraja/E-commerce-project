<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="hy" %>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />


    <!-- *** NAVBAR END *** -->

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="index">Home</a>
                        </li>
                       
                       
                        
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** MENUS AND FILTERS ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Categories</h3>
                        </div>

						<div class="panel-body">
                            <ul class="nav nav-pills nav-stacked category-menu">
                                <c:forEach items="${categoryMap}" var="categoryinfo">
                                <li>
                                    <a href="category?id=${categoryinfo.key}"> ${categoryinfo.value} </a>
                                    <ul>
                                     <c:forEach items="${subcategoryList}" var="subcategorylist">
                                     <c:if test="${categoryinfo.key == subcategorylist.key }">
                                     <c:forEach items="${subcategorylist.value}" var="subcategoryinfo">
                                        <li><a href="subcategory?id=${subcategoryinfo.id}"> ${subcategoryinfo.categoryName} </a>
                                        </li>
                                     </c:forEach>  
                                     </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                               
 						</c:forEach>
                            </ul>

                        </div>
                        
                       </div>

             
                    <!-- *** MENUS AND FILTERS END *** -->

                    <div class="banner">
                        <a href="#">
                            <img src="img/banner.jpg" alt="sales 2014" class="img-responsive">
                        </a>
                    </div>
                </div>

                <div class="col-md-9">

                    <div class="row" id="productMain">
                        <div class="col-sm-6">
                            <div id="mainImage">
                                                <img src="${pageContext.request.contextPath}/productImage?id=${productInfo.id}" alt="" class="img-responsive">
                            </div>

                           

                        </div>
                        <div class="col-sm-6">
                            <div class="box">
                                <h1 class="text-center">${productInfo.name}</h1>
                                                                
                                <p class="goToDescription"><a href="#details" class="scroll-to">More Product details</a>
                                </p>
                               
                                           	          
                                
                                <p class="text-center">Sold By :<b> <a href="${pageContext.request.contextPath}/seller?id=${saccount.id}" style="text-transform: capitalize;">${saccount.userName}</a> 
                                </b>
                                <c:set var="rattingarr" value="${fn:split(sellerRating,'-')}"/>       
                               <c:if test="${ rattingarr[0] != 0 }">
                                <c:forEach var="i" begin="1" end="5">
                               
								
								<c:if test="${ rattingarr[0] >= i }">
								<span class="fa fa-star true"></span>
								</c:if>
								<c:if test="${ rattingarr[0] < i  and rattingarr[0]>0}">
								<span class="fa fa-star "></span>
								</c:if>
								
								</c:forEach>
								(${rattingarr[1]}) 
								</c:if>
								 <c:if test="${ rattingarr[0] == 0 }">
                               
<span style="color:gray">(No Review Found)</span>
								
								</c:if>
								
								
								</p> 
							
								
								<%-- <p>${sellerRating}</p> --%>
								<!-- <p class="text-center">Tags :<b> <a href="#">coat</a>&nbsp;,&nbsp;<a href="#">leather</a></b></p> -->
                               <!--  <div class="form-group text-center">
                                <input  id="tag" type="text">
                                 <button type="submit" class="btn btn-primary"><i class="fa fa-tags"></i> Add Tag</button>
                            </div> -->
                                
                                <p class="price"><fmt:formatNumber value="${productInfo.price}" type="currency"/></p>

                                <p class="text-center buttons">
                                    <a href="addtoCart?id=${productInfo.id}" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Add to cart</a> 
                                    <a href="addtoWishlist?id=${productInfo.id}" class="btn btn-default"><i class="fa fa-heart"></i> Add to wishlist</a>
                                </p>
                                 
                               <!--   <a href="customer/sendmsg?senderId=${accountInfo.Id }&productId=${productInfo.id}" class="btn btn-primary"><i class="fa fa-save"></i> send message</a>-->
                           
                            


                            </div>

                          <!--   <div class="row" id="thumbs">
                                <div class="col-xs-4">
                                    <a href="img/detailbig1.jpg" class="thumb">
                                        <img src="img/detailsquare.jpg" alt="" class="img-responsive">
                                    </a>
                                </div>
                                <div class="col-xs-4">
                                    <a href="img/detailbig2.jpg" class="thumb">
                                        <img src="img/detailsquare2.jpg" alt="" class="img-responsive">
                                    </a>
                                </div>
                                <div class="col-xs-4">
                                    <a href="img/detailbig3.jpg" class="thumb">
                                        <img src="img/detailsquare3.jpg" alt="" class="img-responsive">
                                    </a>
                                </div>
                            </div> -->
                        </div>

                    </div>


                    <div class="box" id="details">
                        <p>
                        <h4>Product details</h4>
                            <p>${productInfo.productDescription}</p>
                            <!-- <h4>Material & care</h4>
                            <ul>
                                <li>Polyester</li>
                                <li>Machine wash</li>
                            </ul>
                            <h4>Size & Fit</h4>
                            <ul>
                                <li>Regular fit</li>
                                <li>The model (height 5'8" and chest 33") is wearing a size S</li>
                            </ul>

                            <blockquote>
                                <p><em>Define style this season with Armani's new range of trendy tops, crafted with intricate details. Create a chic statement look by teaming this lace number with skinny jeans and pumps.</em>
                                </p>
                            </blockquote>
 -->
                            <hr>
                            <div class="social">
                                <h4>Show it to your friends</h4>
                                <p>
                                    <a href="#" class="external facebook" data-animate-hover="pulse"><i class="fa fa-facebook"></i></a>
                                    <a href="#" class="external gplus" data-animate-hover="pulse"><i class="fa fa-google-plus"></i></a>
                                    <a href="#" class="external twitter" data-animate-hover="pulse"><i class="fa fa-twitter"></i></a>
                                    <a href="#" class="email" data-animate-hover="pulse"><i class="fa fa-envelope"></i></a>
                                </p>
                            </div>
                    </div>

                 
                </div>
                <!-- /.col-md-9 -->
            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
  <jsp:include page="footer.jsp" />
  
    </div>
   



</body>

</html>