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
                                <input type="password" class="form-control" id="password">
                            </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pcategory">Zone</label>
                                         <select class="form-control" >
                                          <option value="">Select Zone</option>
										  <option value="east">East</option>
										  <option value="west">West</option>
										  <option value="north">North</option>
										  <option value="south">South</option>
										</select> 
                                    </div>
                                </div>
                                </div>

                              <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pvisibility"> Status : </label>
 <input id="toggle-one" checked type="checkbox">
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

    	//Preview for image to display
        function readURL(input) {

            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $('#previewImage').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#pimage").change(function() {
            readURL(this);
        });
        //End Preview.
    });

</script>
   
</html>
