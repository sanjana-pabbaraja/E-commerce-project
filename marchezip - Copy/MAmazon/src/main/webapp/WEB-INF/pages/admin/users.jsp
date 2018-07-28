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
                        <li>Users</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** CUSTOMER MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                                           <jsp:include page="options.jsp" />
                     
                    </div>
                    <!-- /.col-md-3 -->

                    <!-- *** CUSTOMER MENU END *** -->
                </div>

                <div class="col-md-9" id="customer-orders">
                    <div class="box">
                        <h2>Users List</h2>

                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover dataTable" id="dataTables">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Phone</th>
                                        <th>Email</th>
                                        <th>Products Count</th>
                                        <th>Rating</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Henry</th>
                                        <td>9989796959</td>
                                        <td>henry@gmail.com</td>
                                        <td>10</td>
                                        <td><i id="star-icon" class="fa fa-star" style=""></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star-half-o"></i> <i id="star-icon" class="fa fa-star-o"> </i> </td>
                                        <td><a href="../customer/seller.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Jhon</th>
                                        <td>9989796959</td>
                                        <td>john@yahoo.in</td>
                                        <td>20</td>
                                        <td><i id="star-icon" class="fa fa-star" style=""></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star-half-o"></i> </td>
                                        <td><a href="../customer/seller.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Watson</th>
                                        <td>9989796959</td>
                                        <td>watson14@gmail.com</td>
                                        <td>8</td>
                                        <td><i id="star-icon" class="fa fa-star" style=""></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star-half-o"></i> <i id="star-icon" class="fa fa-star-o"></i> <i id="star-icon" class="fa fa-star-o"></i> </td>
                                        <td><a href="../customer/seller.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Sachin</th>
                                        <td>9989796959</td>
                                        <td>gsachin@gmail.com</td>
                                        <td>5</td>
                                        <td><i id="star-icon" class="fa fa-star" style=""></i> <i id="star-icon" class="fa fa-star"></i> <i id="star-icon" class="fa fa-star-o"> <i id="star-icon" class="fa fa-star-o"> <i id="star-icon" class="fa fa-star-o">  </td>
                                        <td><a href="../customer/seller.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Antony</th>
                                        <td>9989796959</td>
                                        <td>antony@gmail.com</td>
                                        <td>1</td>
                                        <td><i id="star-icon" class="fa fa-star" style=""></i> <i id="star-icon" class="fa fa-star-o"> <i id="star-icon" class="fa fa-star-o"> <i id="star-icon" class="fa fa-star-o"> <i id="star-icon" class="fa fa-star-o"> </td>
                                        <td><a href="../customer/seller.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
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

</html>
