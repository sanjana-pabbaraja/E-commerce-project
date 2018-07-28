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
                        <li>Monitor Product</li>
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
  
                <div class="col-md-9" id="basket">

                    <div class="box">

                        <form method="post" action="#">

                        <h2>Monitor products</h2>


                        <div class="row">

                          <div class="col-md-3">
                            <div class="form-group">

                              <label>Category</label>
                                <select id="tableFilter" class="form-control">
                                  <option id="0">Select</option>
                                  <option id="shirt">Shirts</option>
                                  <option id="pant">Pants</option>
                                  <option id="shoe">Shoes</option>
                                  <option id="computer accessories">Computer Accessories</option>
                                  <option id="mobile">Mobile</option>
                                </select>

                           </div>
                          </div>
                                                
                        </div>
                        <!-- /.row -->

                        <hr>


                            <div class="table-responsive">
                                <table class="table table-hover dataTable" id="dataTables"">
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Product Name</th>
                                            <th>Price</th>
                                            <th>Added By</th>
                                            <th>Category</th>
                                            <th>View</th>
                                                                                        
                                        </tr>
                                    </thead>
                                    <tbody>

                                    <tr>
                                        <td>
                                          <a href="#">
                                            <img src="../img/basketsquare.jpg" alt="Black Leather Jacket">
                                          </a>
                                        </td>                                    
                                        <td><a href="#">Black Leather Jacket</a></td>
                                        <td>$ 200.00</td>
                                        <td>Jhon</td>
                                        <td>Shirts</td>
                                        <td><a href="#" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                          <a href="#">
                                            <img src="../img/printer.jpg" alt="Black Leather Jacket">
                                          </a>
                                        </td>                                    
                                        <td><a href="#">printer</a></td>
                                        <td>$ 120.00</td>
                                        <td>Henry</td>
                                        <td>Computer Accessories</td>
                                        <td><a href="#" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                          <a href="#">
                                            <img src="../img/redtape.jpg" alt="Black Leather Jacket">
                                          </a>
                                        </td>                                    
                                        <td><a href="#">Red-tape Shoes</a></td>
                                        <td>$ 100.00</td>
                                        <td>Sachin</td>
                                        <td>Shoes</td>
                                        <td><a href="#" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                          <a href="#">
                                            <img src="../img/jeans.jpg" alt="Black Leather Jacket">
                                          </a>
                                        </td>                                    
                                        <td><a href="#">Denim Jeans</a></td>
                                        <td>$ 750.00</td>
                                        <td>williams</td>
                                        <td>Pants</td>
                                        <td><a href="#" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                          <a href="#">
                                            <img src="../img/mi.jpg" alt="Black Leather Jacket">
                                          </a>
                                        </td>                                    
                                        <td><a href="#">Xiomi Mi A1</a></td>
                                        <td>$ 90.00</td>
                                        <td>Ram</td>
                                        <td>Mobile</td>
                                        <td><a href="#" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    

                                    </tbody>
                                </table>

                            </div>
                            <!-- /.table-responsive -->

                        </form>

                    </div>
                    <!-- /.box -->
                 
                </div>
                <!-- /.col-md-9 -->
                
                <!-- 
                <div class="col-md-9" id="customer-orders">
                    <div class="box">
                        <h2>Monitor products</h2>

                        <p class="lead">Your products on one place.</p>

                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Product</th>                                    
                                        <th></th>
                                        <th>Price</th>
                                        <th>Added By</th>
                                        <th>Category</th>
                                        <th>View</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                          <a href="#">
                                            <img src="img/detailsquare.jpg" alt="Black Leather Jacket">
                                          </a>
                                        </td>                                    
                                        <th><a href="">Nokia 7</a></th>
                                        <td>$ 200.00</td>
                                        <td>Jhon</td>
                                        <td>Mobile</td>
                                        <td style="text-align:center"><a href="admin-category-view.jsp" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><a href="">printer</a></th>
                                        <td>$ 120.00</td>
                                        <td>Henry</td>
                                        <td>Computer Accessories</td>
                                        <td style="text-align:center"><a href="admin-category-view.jsp" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><a href="">Red-tape Shoes</a></th>
                                        <td>$ 100.00</td>
                                        <td>Sachin</td>
                                        <td>Shoes</td>
                                        <td style="text-align:center"><a href="admin-category-view.jsp" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><a href="">Denim Jeans</a></th>
                                        <td>$ 750.00</td>
                                        <td>williams</td>
                                        <td>Pants</td>
                                        <td style="text-align:center"><a href="admin-category-view.jsp" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><a href="">Xiomi Mi A1</a></th>
                                        <td>$ 90.00</td>
                                        <td>Ram</td>
                                        <td>Mobile</td>
                                        <td style="text-align:center"><a href="admin-category-view.jsp" class="btn btn-primary btn-sm" style="margin-right:10%">View</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
              
                 -->

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
