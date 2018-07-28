<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                        <li>Checkout - Order Placed</li>
                    </ul>
                </div>

                <div class="col-md-12" id="checkout">

                    <div class="box">
                        <form method="post" action="checkout4.jsp">
                            <h1>Thank you for shopping</h1>
                           
                            <div class="content">
                                <h4>Your Order Placed Successfully. <h4>
                                <h5>Your order number is: ${lastOrderedCart.orderNum}</h5>
                                <p>You will be pleased to know that it will be delivered to the confirmed address</p>
                                <br/>
       <a class="navi-item btn-primary btn" href="${pageContext.request.contextPath}/category?id=0">Continue Shopping</a>
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.content -->

                           
                        </form>
                    </div>
                    <!-- /.box -->


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