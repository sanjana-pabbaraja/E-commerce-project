<!DOCTYPE html>
<html lang="en">
<jsp:include page="../header.jsp" />

    <!-- *** NAVBAR END *** -------->

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li><a href="#">My orders</a>
                        </li>
                        <li>Order # 1735</li>
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
                        <h3>Order #1735</h3>

                       
                        <hr>

                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th colspan="2">Product</th>
                                        <th>Quantity</th>
                                        <th>Unit price</th>
                                        <th>Discount</th>
                                        <th>Total</th>
                                        <th>Status</th>
                                        <th>Track Order</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <a href="#">
                                                <img src="../img/detailsquare.jpg" alt="Black Leather Jacket">
                                            </a>
                                        </td>
                                        <td><a href="#">Black Leather Jacket</a>
                                        </td>
                                        <td>2</td>
                                        <td>$123.00</td>
                                        <td>$0.00</td>
                                        <td>$246.00</td>
                                        <td>order-confirmed</td>
                                        <td><a href="orderstatus.jsp" class="btn btn-primary btn-sm">View</a>
                                                                                                                        <a href="customer/sendmsg" class="btn btn-primary btn-sm">Message</a>
                                        
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a href="#">
                                                <img src="../img/basketsquare.jpg" alt="Honor 7x">
                                            </a>
                                        </td>
                                        <td><a href="#">Honor 7x</a>
                                        </td>
                                        <td>1</td>
                                        <td>$200.00</td>
                                        <td>$0.00</td>
                                         <td>$200.00</td>
                                        <td>out for delivery</td>
                                        <td><a href="orderstatus.jsp" class="btn btn-primary btn-sm">View</a>
                                                                                                                        <a href="customer/sendmsg" class="btn btn-primary btn-sm">Message</a>
                                        
                                        </td>
                                        
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="7" class="text-right">Order subtotal</th>
                                        <th>$446.00</th>
                                    </tr>
                                
                                    <tr>
                                        <th colspan="7" class="text-right">Total</th>
                                        <th>$456.00</th>
                                    </tr>
                                </tfoot>
                            </table>

                        </div>
                        <!-- /.table-responsive -->

                        <div class="row addresses">
                            <div class="col-md-6">
                                <h2>Invoice address</h2>
                                <p>John Brown
                                    <br>13/25 New Avenue
                                    <br>New Heaven
                                    <br>45Y 73J
                                    <br>England
                                    <br>Great Britain</p>
                            </div>
                            <div class="col-md-6">
                                <h2>Shipping address</h2>
                                <p>John Brown
                                    <br>13/25 New Avenue
                                    <br>New Heaven
                                    <br>45Y 73J
                                    <br>England
                                    <br>Great Britain</p>
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
