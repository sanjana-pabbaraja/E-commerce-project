<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />
<style>
img#OffAmazonPaymentsWidgets0 {
    border-radius: 5px;
}
</style>

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>Checkout - Order review</li>
                    </ul>
                </div>

                <div class="col-md-9" id="checkout">
					<div>${exceptionMsg}</div>
					  <fmt:setLocale value="en_US" scope="session"/>
					  
					  <c:if test="${ paymentMethod == 'amazonpay' }">
					  <!--  amazon credentials start -->
					  
					  <input type="hidden" id="mws_access_key" value="AKIAIT3SWX5AWCIOAUXA">
				      <input type="hidden" id="mws_secret_key" value="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">
				      <input type="hidden" id="merchant_id" value="A3GTZ3FSK3A6SJ">
				      <input type="hidden" id="client_id" value="amzn1.application-oa2-client.ca4388812db647f2af05dde48195bbc5">
      
					   <!--  amazon credentials stop -->
					  </c:if>
					  
					  
					  
					  
                    <div class="box">
                        <form method="post" action="${pageContext.request.contextPath}/shoppingCartConfirmation"> 
                        
                            <h1>Checkout - Order review</h1>
                             
                            <ul class="nav nav-pills nav-justified">
                                <li><a href="${pageContext.request.contextPath}/shoppingCartCustomer"><i class="fa fa-map-marker"></i><br>Address</a>
                                </li>
                               
                                <li><a href="${pageContext.request.contextPath}/shoppingCartCustomer"><i class="fa fa-money"></i><br>Payment Method</a>
                                </li>
                                <li class="active"><a href="#"><i class="fa fa-eye"></i><br>Order Review</a>
                                </li>
                            </ul>

                            <div class="content">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th colspan="2">Product</th>
                                                <th>Qty</th>
                                                <th>Price</th>
                                               <!--  <th>Discount</th> -->
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${empty cartForm or empty cartForm.cartLines}">
	                                    <tr><td colspan="7">There are no items in Cart</td></tr>
	                                    </c:if>
	                                    <c:if test="${not empty cartForm and not empty cartForm.cartLines   }">
                                    
                                    	<c:forEach items="${cartForm.cartLines}" var="cartLineInfo"   varStatus="varStatus">
                                            <tr>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/detail?id=${cartLineInfo.productInfo.id}">
                                                    <img src="${pageContext.request.contextPath}/productImage?id=${cartLineInfo.productInfo.id}" alt=" ${cartLineInfo.productInfo.name}">
                                                </a>
                                            </td>
                                            <td><a href="${pageContext.request.contextPath}/detail?id=${cartLineInfo.productInfo.id}"> ${cartLineInfo.productInfo.name}</a>
                                            </td>
                                            <td>
                                            ${cartLineInfo.quantity}
                                            </td>
                                            <td><fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/></td>
                                           <!--  <td>$0.00</td> -->
                                            <td><fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/></td>
                                            <td><a href="${pageContext.request.contextPath}/removefromCart?id=${cartLineInfo.productInfo.id}"><i class="fa fa-trash-o"></i></a>
                                            </td>
                                        </tr>
                                            </c:forEach>   
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th colspan="5">Total</th>
                                            <th colspan="2"><fmt:formatNumber value="${myCart.amountTotal}" type="currency"/></th>
                                        </tr>
                                    	</tfoot>
                                         </c:if>
                                    </table>

                                </div>
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.content -->

                            <div class="box-footer">
                                <div class="pull-left">
                                    <a href="${pageContext.request.contextPath}/shoppingCartCustomer" class="btn btn-default"><i class="fa fa-chevron-left"></i>Back to Payment method</a>
                                </div>
                                <div class="pull-right">
                                	<c:if test="${ paymentMethod == 'cod' }">
                                    	<button type="submit" class="btn btn-primary">Place an order<i class="fa fa-chevron-right"></i>
                                    </button>
                                    </c:if>
                                    <c:if test="${ paymentMethod == 'amazonpay' }">
                                    	<div class="AmazonPayButton"  id="AmazonPayButton"></div>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.box -->


                </div>
                <!-- /.col-md-9 -->

                <div class="col-md-3">

                    <div class="box" id="order-summary">
                        <div class="box-header">
                            <h3>Order summary</h3>
                        </div>
                        <p class="text-muted">Shipping and additional costs are calculated based on the values you have entered.</p>

                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>Order subtotal</td>
                                        <th><fmt:formatNumber value="${myCart.amountTotal}" type="currency"/></th>
                                    </tr>
                                  <!--   <tr>
                                        <td>Shipping and handling</td>
                                        <th>$10.00</th>
                                    </tr> -->
                                    <tr>
                                        <td>Tax</td>
                                        <th>$0.00</th>
                                    </tr>
                                    <tr class="total">
                                        <td>Total</td>
                                        <th><fmt:formatNumber value="${myCart.amountTotal}" type="currency"/></th>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>
                <!-- /.col-md-3 -->

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
  

  <!--  if payment is amazon pay then include below code -->
  <c:if test="${ paymentMethod == 'amazonpay' }">
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
 <script type="text/javascript">
            window.onAmazonLoginReady = function() {
                amazon.Login.setClientId('amzn1.application-oa2-client.ca4388812db647f2af05dde48195bbc5');
            };
            window.onAmazonPaymentsReady = function() {
                showButton();
            };
            function showButton() {
                var authRequest;
                OffAmazonPayments.Button("AmazonPayButton", "A3GTZ3FSK3A6SJ", {
                    type: "PwA",
                    color: "Gold",
                    size: "medium",
                    authorization: function() {
                        loginOptions = { scope: "profile payments:widget payments:shipping_address", popup: true };
                        authRequest = amazon.Login.authorize (loginOptions, "Widgets");
                    },
                    onError: function(error) {
                        alert(error.getErrorMessage());
                    }
                });
            };
        </script>
        <script async='async' src='https://static-na.payments-amazon.com/OffAmazonPayments/us/sandbox/js/Widgets.js'></script>
         
 </c:if>
<!--  end of amazon payment -->


</body>

</html>
 
 
 
 
 
 
 


