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
                        <li>Monitor orders</li>
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
                        <h2>Monitor orders</h2>


                        <div class="row">

                          <div class="col-md-3">
                            <div class="form-group">

                              <label>Status</label>
                                <select id="tableFilter" class="form-control">
                                  <option id="0">Select</option>
                                  <option id="beingprepared">Being Prepared</option>
                                  <option id="received">Received</option>
                                  <option id="cancelled">Cancelled</option>
                                  <option id="onhold">On hold</option>
                                </select>

                           </div>
                          </div>
                                                
                          <div class="col-md-3">
                          
                            <div class="form-group">
                              <label>From</label>
                                <div class="input-group input-append date" id="datePickerFrom">
                                  <input type="text" class="form-control" name="dateFrom" placeholder="DD/MM/YYYY" / >
                                  <span class="input-group-addon add-on"><span class="fa fa-calendar"></span></span>
                                </div>
                            </div>                                                                             

                          </div>

                        </div>
                        <!-- /.row -->

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
                                    <tr>
                                        <th># 1735</th>
                                        <td>07/04/2018</td>
                                        <td>$ 150.00</td>
                                        <td><span class="label label-info">Being prepared</span>
                                        </td>
                                        <td><a href="admin-order-view.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th># 1735</th>
                                        <td>05/04/2018</td>
                                        <td>$ 150.00</td>
                                        <td><span class="label label-info">Being prepared</span>
                                        </td>
                                        <td><a href="admin-order-view.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th># 1735</th>
                                        <td>07/04/2018</td>
                                        <td>$ 150.00</td>
                                        <td><span class="label label-success">Received</span>
                                        </td>
                                        <td><a href="admin-order-view.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th># 1735</th>
                                        <td>06/04/2018</td>
                                        <td>$ 150.00</td>
                                        <td><span class="label label-danger">Cancelled</span>
                                        </td>
                                        <td><a href="admin-order-view.jsp" class="btn btn-primary btn-sm">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th># 1735</th>
                                        <td>07/04/2018</td>
                                        <td>$ 150.00</td>
                                        <td><span class="label label-warning">On hold</span>
                                        </td>
                                        <td><a href="admin-order-view.jsp" class="btn btn-primary btn-sm">View</a>
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
