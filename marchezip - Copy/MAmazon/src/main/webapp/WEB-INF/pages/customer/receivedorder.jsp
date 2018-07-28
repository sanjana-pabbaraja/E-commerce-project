    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <!DOCTYPE html>
        <html lang="en">
        <jsp:include page="../header.jsp" />

        <!-- *** NAVBAR END *** -->

        <div id="all">

        <div id="content">
        <div class="container">

        <div class="col-md-12">

        <ul class="breadcrumb">
        <li><a href="index.jsp">Home</a>
        </li>
        <li><a href="#">My Received Orders</a>
        </li>
        <li>Order # ${orderInfo.id}</li>
        </ul>
 						<c:if test="${not empty cmessage}">
						<div class="alert alert-info">
 						  ${cmessage}.
						</div>
						
						
					</c:if>
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

        <div class="col-md-9" id="customer-order">
        <div class="box">
        <h3>Order # ${orderInfo.id}</h3>
        <hr>
        <div style="float:right">
                     <%--    <c:set var="conf" value="0"/>
                
        <c:set var="cou" value="0"/>
         <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
          <c:if test="${cou!=0}">
          <c:set var="pids" value="${pids},${orderDetailInfo.productId}"/>
         </c:if>
          <c:if test="${cou==0}">
          <c:set var="pids" value="${orderDetailInfo.productId}"/>
         </c:if>
         <c:if test="${orderDetailInfo.productStatus=='Order-Placed'}">
                  <c:set var="conf" value="${conf+1}"/>
         </c:if>
         <c:set var="cou" value="${cou+1}"/>
         </c:forEach>
          <c:if test="${cou==conf}">
                <a href="${pageContext.request.contextPath}/customer/updateorderstatus?orderstatus=2&orderId=${orderInfo.id}&pids=${pids}" class="btn btn-primary btn-sm">Confirm Order</a>
                        <a href="${pageContext.request.contextPath}/customer/updateorderstatus?orderstatus=6&orderId=${orderInfo.id}&pids=${pids}" class="btn btn-primary btn-sm">Cancel Order</a>
        </c:if> --%>
<%--         <a href="${pageContext.request.contextPath}/customer/orderstatus?orderId=${orderInfo.id}" class="btn btn-primary btn-sm">Track Order</a>
 --%>       <%--  <a href="${pageContext.request.contextPath}/customer/sendmsg" class="btn btn-primary btn-sm">Message</a> --%>
        </div>
        <div class="table-responsive">
        <table class="table">
        <thead>
        <tr>
        <th colspan="2">Product</th>
        <th>Quantity</th>
        <th>Unit price</th>
        <th>Total</th>
        <th>Status</th>
        <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="total" value="${0}"/>
        <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
            <tr>
            <td>
            <a href="${pageContext.request.contextPath}/detail?id=${orderDetailInfo.productId}">
            <img src="${pageContext.request.contextPath}/productImage?id=${orderDetailInfo.productId}" alt="${orderDetailInfo.productName}">
            </a>
            </td>
            <td><a href="${pageContext.request.contextPath}/detail?id=${orderDetailInfo.productId}">${orderDetailInfo.productName}</a>
            </td>
            <td>${orderDetailInfo.quanity}</td>
            <td> <fmt:formatNumber value="${orderDetailInfo.price}" type="currency" /> </td>
            <td> <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency" /> </td>
            <c:set var="total" value="${total + orderDetailInfo.amount}" />
            <td><span class="label label-info">${orderDetailInfo.productStatus}</span></td>
<td>
<c:if test="${orderDetailInfo.productStatus=='Order-Placed'}">
  <a href="${pageContext.request.contextPath}/customer/updateorderstatus?orderstatus=2&orderId=${orderInfo.id}&pids=${orderDetailInfo.productId}" class="btn btn-primary btn-sm">Confirm</a>
                        <a href="${pageContext.request.contextPath}/customer/updateorderstatus?orderstatus=6&orderId=${orderInfo.id}&pids=${orderDetailInfo.productId}" class="btn btn-primary btn-sm">Cancel</a>
  </c:if>   
  <c:if test="${orderDetailInfo.productStatus!='Order-Placed'}">
   
 <a href="${pageContext.request.contextPath}/customer/orderstatus?orderId=${orderInfo.id}&productId=${orderDetailInfo.productId}" class="btn btn-primary btn-sm">Track Order</a>       
 </c:if>
</td>
            </tr>
        </c:forEach>

        </tbody>
        <tfoot>
        <tr>
        <th colspan="6" class="text-right">Order subtotal</th>
        <th> <fmt:formatNumber value="${total}" type="currency" /> </th>
        </tr>

        <tr>
        <th colspan="6" class="text-right">Total</th>
        <th> <fmt:formatNumber value="${total}" type="currency" /> </th>
        </tr>
        </tfoot>
        </table>

        </div>
        <!-- /.table-responsive -->
        <c:set var="dateParts" value="${fn:split(orderInfo.customerAddress, ',')}" />
        <div class="row addresses">
        <div class="col-md-6">
        <h2>Invoice address</h2>
        <p>${dateParts[0]} ${dateParts[1]}
        <br>${dateParts[2]}
        <br>${dateParts[3]}
        <br>${dateParts[4]}
        <br>${dateParts[5]}
        <br>${dateParts[6]}
        <br>${dateParts[7]}</p>
        </div>
        <div class="col-md-6">
        <h2>Shipping address</h2>

        <p>${dateParts[0]} ${dateParts[1]}
        <br>${dateParts[2]}
        <br>${dateParts[3]}
        <br>${dateParts[4]}
        <br>${dateParts[5]}
        <br>${dateParts[6]}
        <br>${dateParts[7]}</p>
        </div>
        </div>

        </div>
        </div>

        </div>
        <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***-->
        <jsp:include page="../footer.jsp" />


        </div>
        <!-- /#all -->







        </body>

        </html>
