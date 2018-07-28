<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <li>Orders Placed</li>
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
                        <h2>Orders Placed</h2>

                      
                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover dataTable" id="dataTables">
                                <thead>
                                    <tr>
                                        <th>Order</th>
                                        <th>Date</th>
                                        <th>Total</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${paginationResult}" var="orderInfo">
                                    <tr>
                                        <th> ${orderInfo.orderNum}</th>
                                        <td><fmt:formatDate value="${orderInfo.orderDate}" pattern="dd-MM-yyyy HH:mm"/></td>
                                        <td><fmt:formatNumber value="${orderInfo.amount}" type="currency"/></td>
                                        <td><span class="label label-info">${orderInfo.systemStatus}</span>
                                        </td>
                                        <td><a href="${pageContext.request.contextPath}/customer/placedorder?orderId=${orderInfo.id}" class="btn btn-primary btn-sm">View</a>
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
