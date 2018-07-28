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
                        <li>Monitor delivery boys</li>
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
                        <h2>Add Delivery Boys</h2>

                        <div class="text-right" style="">
                           <a href="add-deliverboys.jsp" class="btn btn-primary btn-sm add">Add Delivery Boys</a>
                        </div>                            
                      
                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover datatable" id="dataTables">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Phone</th>
                                        <th>Email Id</th>
                                        <th>Zone</th>
                                        <th>Status</th>
                                        <th>Updated Date</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td>
                                    <a href="edit-deliverboys.jsp">
                                          Harry
                                                </a>
                                                </td>
										<td>9876543210</td>
										<td>harry123@gmail.com</td>
                                        
                                        <td>East-zone</td>
                                        <td><span class="label label-success">Available</span>
                                        </td>
                                        <td>22/03/2018</td>
                                       
                                        <td>
                                        <a href="edit-deliverboys.jsp" class="btn btn-primary btn-sm edit">Edit</a>
                                        </td>
                                    </tr>
                                     <tr>
                                         <td>
                                    <a href="edit-deliverboys.jsp">
                                    Robert
                                                </a>
                                                </td>
										<td>9876543211</td>
										<td>robert3@gmail.com</td>
                                        
                                        <td>West-zone</td>
                                        <td><span class="label label-success">Available</span>
                                        </td>
                                        <td>28/03/2018</td>
                                       
                                        <td>
                                        <a href="edit-deliverboys.jsp" class="btn btn-primary btn-sm edit">Edit</a>
                                        </td>
                                    </tr>
                                    
                                     <tr>
                                         <td>
                                    <a href="edit-deliverboys.jsp">
                                    Fedric Willson
                                                </a>
                                                </td>
										<td>9876543212</td>
										<td>fedricw123@gmail.com</td>
                                        
                                        <td>North-zone</td>
                                        <td><span class="label label-success">Available</span>
                                        </td>
                                        <td>26/03/2018</td>
                                       
                                        <td>
                                        <a href="edit-deliverboys.jsp" class="btn btn-primary btn-sm edit">Edit</a>
                                        </td>
                                    </tr>
                                    <tr>
                                          <td>
                                    <a href="edit-deliverboys.jsp">
                                    Sham Prasad
                                                </a>
                                                </td>
										<td>9876543213</td>
										<td>sprasad4@gmail.com</td>
                                        
                                        <td>South-zone</td>
                                        <td><span class="label label-danger">Not-Available</span>
                                        </td>
                                        <td>19/03/2018</td>
                                       
                                        <td>
                                        <a href="edit-deliverboys.jsp" class="btn btn-primary btn-sm edit">Edit</a>
                                        </td>
                                    </tr>

                                     <tr>
                                         <td>
                                    <a href="edit-deliverboys.jsp">
                                    J.S Thomas
                                                </a>
                                                </td>
										<td>9876543212</td>
										<td>jsthomas@gmail.com</td>
                                        
                                        <td>central-zone</td>
                                        <td><span class="label label-danger">Not Available</span>
                                        </td>
                                        <td>26/03/2018</td>
                                       
                                        <td>
                                        <a href="edit-deliverboys.jsp" class="btn btn-primary btn-sm edit">Edit</a>
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


    

    <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
   


</body>

</html>
