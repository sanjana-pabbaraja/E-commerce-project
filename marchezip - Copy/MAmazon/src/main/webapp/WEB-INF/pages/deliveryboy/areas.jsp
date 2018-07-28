<!DOCTYPE html>
<html lang="en">

<jsp:include page="../header.jsp" />


    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="../index">Home</a>
                        </li>
                        <li>My areas</li>
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

                <div class="col-md-9" id="customer-orders">
                    <div class="box">
                        <h2>My Areas</h2>

                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover dataTable" id="dataTables">
                                <thead>
                                    <tr>
                                        <th>Location Name</th>
                                        <th>Zone</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Mallapur</th>
                                        <td>East Zone</td>
                                        
                                    </tr>
                                    <tr>
                                        <th> Nacharam</th>
                                        <td>East Zone</td>
                                        
                                    </tr>
                                    <tr>
                                        <th> Ramanthapur</th>
                                        <td>East Zone</td>
                                        
                                    </tr>
                                    <tr>
                                        <th> Uppal</th>
                                        <td>East Zone</td>
                                       
                                    </tr>
                                    <tr>
                                        <th>Nagole</th>
                                        <td>East Zone</td>
                                       
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
