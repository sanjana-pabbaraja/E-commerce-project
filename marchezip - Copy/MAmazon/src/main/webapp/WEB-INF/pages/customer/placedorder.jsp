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
        <li><a href="${pageContext.request.contextPath}/index">Home</a>
        </li>
        <li><a href="#">My orders</a>
        </li>
        <li>Order # ${orderInfo.id}</li>
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

        <div class="col-md-9" id="customer-order">
        <div class="box">
        <h3>Order # ${orderInfo.id}</h3>
        <hr>
        <div style="float:right">
        <a href="${pageContext.request.contextPath}/customer/sendmsg" class="btn btn-primary btn-sm">Message</a>
        </div>
        <div class="table-responsive">
        <table class="table">
        <thead>
        <tr>
        <th colspan="2">Product</th>
        <th>Quantity</th>
        <th>Unit price</th>
        <th>Total</th>
        <th style="text-align:center;width: 16%;">Status</th>
        <th>Track Order</th>
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
            <td style="text-align:center">
            
            ${orderDetailInfo.productStatus}
            <c:if test="${ orderDetailInfo.productStatus =='Order-Completed' }">
            <div class="stars">
  <form method="post" action="${pageContext.request.contextPath}/customer/rateSellerProduct">
 
   <div>
    <input class="star star-5" value="5"  id="star-5" type="radio" name="rating" required/>
    <label class="star star-5" for="star-5"></label>
    <input class="star star-4" value="4"  id="star-4" type="radio" name="rating" required/>
    <label class="star star-4" for="star-4"></label>
    <input class="star star-3" value="3" id="star-3" type="radio" name="rating" required/>
    <label class="star star-3" for="star-3"></label>
    <input class="star star-2" value="2" id="star-2" type="radio" name="rating" required/>
    <label class="star star-2" for="star-2"></label>
    <input class="star star-1" value="1" id="star-1" type="radio" name="rating" required />
    <label class="star star-1" for="star-1"></label>
    </div> 
  <!--  <select name="rating" class="form-control rated" required>
    <option value="0" selected="selected">Select</option>
    <option value="1">Poor</option>
    <option value="2">Below Average</option>
    <option value="3">Average</option>
    <option value="4">Good</option>
    <option value="5">Excellent</option>
</select> --> 
<input type="hidden" name="productid" value="${orderDetailInfo.productId }"/>
<input type="hidden" name="orderid" value="${orderInfo.id}"/>

    <input  class="btn btn-primary ratebtn" type="submit" name="Rate" value="Rate" disabled="disabled">
  </form>
</div>
</c:if>
            </td>
<td>        <a href="${pageContext.request.contextPath}/customer/orderstatus?orderId=${orderInfo.id}&productId=${orderDetailInfo.productId}" class="btn btn-primary btn-sm">Track Order</a>
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
