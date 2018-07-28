<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../header.jsp" />


    <!-- *** NAVBAR END *** -->

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>My Products</li>
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

                <div class="col-md-9" id="customer-orders">
                    <div class="box">
                        <h2>My Products </h2>

                        <div class="text-right" style="">
                           <a href="addproduct" class="btn btn-primary btn-sm add">Add Product</a>
                        </div>                            
                      
                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover datatable" id="dataTables">
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>Product Name</th>
                                        <!-- <th>Added Date</th> -->
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th style="width:14%;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty productInfos}">
                                    <tr><td colspan="6">No products available</td></tr>
                                    </c:if>
                                <c:forEach items="${productInfos}" var="info">
                                    <tr>
                                    <td>
                                    <a href="${pageContext.request.contextPath}/detail?id=${info.id}">
                                                    <img src="${pageContext.request.contextPath}/productImage?id=${info.id}" style="width: 35px;">
                                                </a>
                                                </td>
										<td><a href="${pageContext.request.contextPath}/detail?id=${info.id}">${info.name}</a></td>
                                        
                                      <%--   <td>${info.addedTime}</td> --%>
                                        <td><fmt:formatNumber value="${info.price}" type="currency"/></td>
                                        
                                        <td>
                                        <c:if test="${info.availability==true}">
                                        <span class="label label-success">Visible</span>
                                        </c:if>
                                        <c:if test="${info.availability==false}">
                                        <span class="label label-warning">Invisible</span>
                                        </c:if>
                                        </td>
                                       
                                        <td style="width:14%;">
                                        <a href="${pageContext.request.contextPath}/customer/editProduct?id=${info.id}" class="btn btn-primary btn-sm edit"><i class="fa fa-edit" style="margin: 0 2px;font-size: 14px;"></i></a>
                                        <%-- <a href="${pageContext.request.contextPath}/detail?id=${info.id}" class="btn btn-primary btn-sm">View</a> --%>
                                        <a href="${pageContext.request.contextPath}/customer/deleteProduct?id=${info.id}"  class="btn btn-primary btn-sm" ><i class="fa fa-trash-o" style="margin: 0 2px;font-size: 14px;"></i></a> 
                                        </td>
                                    </tr>
                                    </c:forEach>
                                     
                                   
                                </tbody>
                            </table>
                        </div>
                    </div>
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
