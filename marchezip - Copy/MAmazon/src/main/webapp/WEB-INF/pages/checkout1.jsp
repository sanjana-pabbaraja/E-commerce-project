<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <li>Checkout - Address</li>
                    </ul>
                </div>

                <div class="col-md-9" id="checkout">

                    <div class="box">
                        <form:form method="POST" modelAttribute="useraddressForm"
                        	 action="${pageContext.request.contextPath}/shoppingCartCustomer">
                            <h1>Checkout</h1>
                            <ul class="nav nav-pills nav-justified">
                                <li class="active"><a href="#"><i class="fa fa-map-marker"></i><br>Address</a>
                                </li>
                                
                                <li class="disabled"><a href="#"><i class="fa fa-money"></i><br>Payment Method</a>
                                </li>
                                <li class="disabled"><a href="#"><i class="fa fa-eye"></i><br>Order Review</a>
                                </li>
                            </ul>

                     
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="firstname">First Name</label>
                                        <form:input type="text" class="form-control" id="firstname" path="firstname" />
                                        <form:errors path="firstname" class="error-message" />
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="lastname">Last Name</label>
                                        <form:input type="text" class="form-control" id="lastname" path="lastname" />
                                        <form:errors path="lastname" class="error-message" />
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="doorno">Door No</label>
                                        <form:input path="doorno" type="text" class="form-control" id="doorno" />
                                        <form:errors path="doorno" class="error-message" />
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="street">Street</label>
                                        <form:input path="street" type="text" class="form-control" id="street" />
                                        <form:errors path="street" class="error-message" />
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="row">
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="city">City</label>
                                        <form:input path="city" type="text" class="form-control" id="city" />
                                        <form:errors path="city" class="error-message" />
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="pincode">ZIP</label>
                                        <form:input  path="pincode" type="text" class="form-control" id="zip" />
                                        <form:errors path="pincode" class="error-message" />
                                    </div>
                                </div>
                             
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="state">State</label>
                                        <form:select path="state" class="form-control" id="state">
                                       <option value="Telangana" selected>Telangana</option>
										</form:select>
                                        <form:errors path="state" class="error-message" />
                                    </div>
                                </div>
                                   <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="country">Country</label>
                                        <select  class="form-control" id="country">                                        
                                         <option value="India">India</option>
                                        <select>
                                    </div>
                                </div>

                               
                            </div>
                    
                            <div class="box-footer">
                                <div class="pull-left">
                                    <a href="${pageContext.request.contextPath}/shoppingCart" class="btn btn-default"><i class="fa fa-chevron-left"></i>Back to basket</a>
                                </div>
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary">Continue to Delivery Method<i class="fa fa-chevron-right"></i>
                                    </button>
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
                                        <th><fmt:formatNumber value="${cartInfo.amountTotal}" type="currency"/></th>
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
                                        <th><fmt:formatNumber value="${cartInfo.amountTotal}" type="currency"/></th>
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
   


</body>

</html>