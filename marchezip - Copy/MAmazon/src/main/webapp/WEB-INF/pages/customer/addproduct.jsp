<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.5.1/chosen.min.css">
</head>

<%@ include file="../header.jsp" %>

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">

                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>My account</li>
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
                        <h2>Add Product</h2>
                      

                        <form>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pname">Product Name</label>
                                        <input type="text" class="form-control" id="pname">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pcategory">Product Category</label>
                                         <select class="form-control" >
                                          <option value="">Select Category</option>
										  <option value="volvo">Men</option>
										  <option value="saab">Woman</option>
										  <option value="mercedes">Mobiles</option>
										  <option value="audi">Gadgets</option>
										  <option value="others">Others</option>
										</select> 
                                    </div>
                                </div>
                                </div>
                                 <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pcategory">Sub Category</label>
                                         <select class="form-control" >
                                          <option value="">Select Category</option>
										  <option value="volvo">Samsung</option>
										  <option value="saab">Nokia</option>
										  <option value="mercedes">Apple</option>
										  <option value="audi">Google</option>
										  <option value="others">Others</option>
										</select> 
                                    </div>
                                </div>
                                </div>
                                <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pcategory">Product Description</label>
<!--  HTML Editor-->

      <div class="row">

        <div class="col-md-12">
          <div class="box box-info">

            <div class="box-body pad">
              <form>
                    <textarea id="editor1" name="editor1" rows="10" cols="80">
                                            This is my textarea to be replaced with CKEditor.
                    </textarea>
              </form>
            </div>
          </div>
          <!-- /.box -->

        </div>
        <!-- /.col-->
      </div>
      <!-- ./row -->


<!-- End Of HTML Editor -->
                                    </div>
                                </div>
                                </div>
                                 <div class="row">
                                 <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pbrand">Brand</label>
                                      <input type="text" class="form-control" id="brand">
                                        
                                    </div>
                                </div>
                                 </div>
                                 
                                <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pprice">Price</label>
                                        <input type="text" class="form-control" id="pprice">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="pvisibility"> Visibility : </label>
 <input id="toggle-one" checked type="checkbox">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="pavailability"> Availability : </label>
 <input id="toggle-two" checked type="checkbox">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label for="pimage">Product Image</label>
                                        <input type="file" id="pimage" accept='image/*'>
                                    </div>
                                </div>
                                <div class="col-sm-8">    
                                    <img class="profile-user-img img-responsive img-circle" id="previewImage" src="data:image/png;base64,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" alt="Student profile picture" style="width: 130px;height:130px;">
                                </div>    

                                </div>
                            </div>
                             <div class="row">
                                 <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="plocation">Location</label>
   <form:select path="location"   class="form-control">
						                       <form:options items="${locationMap}" />
						                   </form:select>
						                   <form:errors path="location" class="error-message" />
                                        
                                    </div>
                                </div>
                                 </div>
                              
                           
                            <!-- /.row -->

                            <div class="col-sm-12 text-center">
                                <button type="submit" id="saveproduct" class="btn btn-primary"><i class="fa fa-save"></i> Add Product</button>
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
 
 <!-- Drop down Live Search -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.5.1/chosen.jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.5/waypoints.min.js"></script>

<script type="text/javascript">
      $("#livesearch").chosen();
</script>
 

<!-- CK Editor -->
<script src="../js/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function() {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace('editor1')
  })
</script>

<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>    
<script type="text/javascript">

    $(document).ready(function() {
    	    	
    	$('#toggle-one').bootstrapToggle();
    	$('#toggle-two').bootstrapToggle();

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