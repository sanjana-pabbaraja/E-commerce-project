<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 
 <!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<fmt:setLocale value="en_US" scope="session"/>
    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>Shopping cart</li>
                    </ul>
                </div>

                <div class="col-md-9" id="basket">

                    <div class="box">

                        <form:form method="POST" modelAttribute="cartForm"  action="${pageContext.request.contextPath}/shoppingCart">

                            <h1>Shopping cart</h1>
                            <p class="text-muted">You currently have ${fn:length(cartForm.cartLines)}  item(s) in your cart.</p>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th colspan="2">Product</th>
                                            <th>Qty</th>
                                            <th>Price</th>
                                           <!--  <th>Discount</th> -->
                                            <th colspan="2">Total</th>
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
                                            <form:input  class="form-control"  path="cartLines[${varStatus.index}].quantity" readonly="true"  />
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

                            <div class="box-footer">
                                <div class="pull-left">
                                    <a href="${pageContext.request.contextPath}/category?id=0" class="btn btn-default"><i class="fa fa-chevron-left"></i> Continue shopping</a>
                                </div>
                                <div class="pull-right">
                                    <!-- <button type="submit" class="btn btn-default"><i class="fa fa-refresh"></i> Update basket</button> -->
                                    <a class="btn btn-primary"   href="${pageContext.request.contextPath}/shoppingCartCustomer">Proceed to checkout <i class="fa fa-chevron-right"></i></a>
                                    
                                </div>
                            </div>

                        </form:form>

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
                                   <!--  <tr>
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

        <!-- *** FOOTER ***-->
<jsp:include page="footer.jsp" />


    </div>
    <!-- /#all -->


    

    


</body>

</html>
 
 
 