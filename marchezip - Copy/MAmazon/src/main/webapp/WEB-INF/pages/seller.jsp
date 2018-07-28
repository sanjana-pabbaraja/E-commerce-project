<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>Men</li>
                    </ul>

                    <div class="box">
                        <h1 style="text-transform: capitalize;">${saccount.userName} </b>
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
								<c:if test="${ followFlag == true }">
                                    	<a  style="float: right;

margin-right: 8%;" href="${pageContext.request.contextPath}/follow?id=<%= request.getParameter("id") %>" class="btn btn-primary"><i class="fa fa-heart"></i> Follow</a>
                             
                                </c:if>
								<c:if test="${ followFlag == false }">
                                    	<a  style="float: right;

margin-right: 8%;" href="${pageContext.request.contextPath}/unfollow?id=<%= request.getParameter("id") %>" class="btn btn-primary"><i class="fa fa-heart"></i> Unfollow</a>
                                 
                                 </c:if>
                                 
								
								</h1>
                        
                    </div>

                    <div class="box info-bar">
                        <div class="row">
                           

                            <div class="col-sm-12 col-md-12  products-number-sort">
                                <div class="row">
                                    <form class="form-inline">
                                        <div class="col-md-9 col-sm-6">
                                                                        Showing <strong>${sellerProducts.size()}</strong> products
                                        
                                            <!-- <div class="products-number">
                                                <strong>Show</strong>  <a href="#" class="btn btn-default btn-sm btn-primary">12</a>  <a href="#" class="btn btn-default btn-sm">24</a>  <a href="#" class="btn btn-default btn-sm">All</a> products
                                            </div> -->
                                        </div>
                                        <div class="col-md-3 col-sm-6">
                                            <div class="products-sort-by">
                                                <strong>Sort by</strong>
                                                <select name="sort-by" class="form-control" id="sort">
                                                      <option value="">Relevance</option>
                                                    <option value="2">Price</option>
                                                    <option value="1">Name</option>
                                                    <option value="3">Sales first</option>
                                                </select>
                                                   
                                                
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
 <div class="row products">
						<c:forEach items="${sellerProducts}" var="info">
                        <div class="col-md-3 col-sm-6 prod">
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
                                    <p class="views" style="display:none;"> ${info.id} </p>
                                   
                                    <p class="buttons">
                                        <a href="detail?id=${info.id}" class="btn btn-default">View detail</a>
                                        <a href="addtoCart?id=${info.id}" class="btn btn-primary"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                    </p>
                                </div><!-- /.text -->
                                
                            </div><!-- /.product -->
                            
                        </div><!-- /.col-md-4 -->                        
                        
                        
                        </c:forEach>
                    </div>

                    <!-- /.products -->

               <!--      <div class="pages">

                        <p class="loadMore">
                            <a href="#" class="btn btn-primary btn-lg"><i class="fa fa-chevron-down"></i> Load more</a>
                        </p>

                        <ul class="pagination">
                            <li><a href="#">&laquo;</a>
                            </li>
                            <li class="active"><a href="#">1</a>
                            </li>
                            <li><a href="#">2</a>
                            </li>
                            <li><a href="#">3</a>
                            </li>
                            <li><a href="#">4</a>
                            </li>
                            <li><a href="#">5</a>
                            </li>
                            <li><a href="#">&raquo;</a>
                            </li>
                        </ul>
                    </div> -->


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
    <!-- /#all -->


    

    <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
 
</body>

</html>