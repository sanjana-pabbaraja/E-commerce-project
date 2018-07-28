        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">


   <jsp:include page="../header.jsp" />

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>Order Status</li>
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

                <div class="col-md-9">
                    <div class="box">
                        <h2>Status of Product # ${productId} (Order: ${orderId})</h2>
                      

                        <div class="row shop-tracking-status">
    
    <div class="col-md-12">
        <div class="well">
            <div class="order-status">

                <div class="order-status-timeline">
                    <!-- class names: c0 c1 c2 c3 and c4 -->
                    <c:set var="stage" value="${orderStatusId-1}"/>
                  
                    
                 <div class="order-status-timeline-completion c${stage}"></div>
                </div>

                <div class="image-order-status image-order-status-new active img-circle">
                    <span class="status">Order-Placed</span>
                    <div class="icon"></div>
                </div>
                <div class="image-order-status image-order-status-active active img-circle">
                    <span class="status">Order-Confirmed</span>
                    <div class="icon"></div>
                </div>
                <div class="image-order-status image-order-status-intransit active img-circle">
                    <span class="status">Pickedup from Seller</span>
                    <div class="icon"></div>
                </div>
                <div class="image-order-status image-order-status-delivered active img-circle">
                    <span class="status">Delivered to Buyer</span>
                    <div class="icon"></div>
                </div>
                <div class="image-order-status image-order-status-completed active img-circle">
                    <span class="status">Order-Completed</span>
                    <div class="icon"></div>
                </div>

            </div>
        </div>
    </div>

</div>

                        <hr>

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
