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
                        <li>Add delivery boy</li>
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

                <div class="col-md-9">
                    <div class="box">
                        <h2>Add Delivery Boy</h2>
                      

                        <form>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pname">Name</label>
                                        <input type="text" class="form-control" id="dname">
                                    </div>
                                </div>
                            </div>

                                <div class="row">
                                <div class="col-sm-12">
                            <div class="form-group">
                                <label for="mobileno">Mobile Number</label>
                                <input type="text" class="form-control" id="mobileno">
                            </div>
                                </div>
                            </div>
                                                            
                                <div class="row">
                                <div class="col-sm-12">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" id="email">
                            </div>
                                </div>
                            </div>

                                <div class="row">
                                <div class="col-sm-12">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" style="display:inline-block;width:93%;">
<button type="button" id="eye" style="background-color:white;border:none">
    <i class="fa fa-eye" id="star-icon"></i>
</button>
                            </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pzones">Zone</label>
                                         <select class="form-control" >
                                          <option value="">Select Zone</option>
										  <option value="east" selected>East-Zone</option>
										  <option value="west">West-Zone</option>
										  <option value="north">North-Zone</option>
										  <option value="south">South-Zone</option>
										  <option value="central">Central-Zone</option>
										</select> 
                                    </div>
                                </div>
                                </div>

                              <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pvisibility"> Status : </label>
 <input id="toggle-one" checked type="checkbox" data-on="Available" data-off="Not-Available">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->                          
                            

                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Add</button>
                            </div>
                        </form>

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

<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>    
<script type="text/javascript">

    $(document).ready(function() {
    	    	
    	$('#toggle-one').bootstrapToggle();

        function show() {
            var p = document.getElementById('password');
            p.setAttribute('type', 'text');
        }

        function hide() {
            var p = document.getElementById('password');
            p.setAttribute('type', 'password');
        }

        var pwShown = 0;

        document.getElementById("eye").addEventListener("click", function () {
            if (pwShown == 0) {
                pwShown = 1;
                show();
            } else {
                pwShown = 0;
                hide();
            }
        }, false);

    });

</script>
   
</html>
