<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
 
 <!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.5.1/chosen.min.css">
    <style>
.error-message {
   color: red;
   font-size:90%;
   font-style: italic;
}
</style>
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
                        <h2>${formTitle}</h2>
                      

                       <form:form action="saveProduct" method="POST"
       modelAttribute="productForm" enctype="multipart/form-data">
       
        <form:hidden path="id" />
        
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pname">Product Name</label>
                                        <form:input path="name" required="true"  class="form-control" />
                                        <form:errors path="name" class="error-message" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pcategory">Product Category</label>
                                         <form:select path="categoryId"  class="form-control">
					                       <form:options items="${categoryMap}" />
					                   </form:select>
					                   <form:errors path="categoryId" class="error-message" />
                                    </div>
                                </div>
                                </div>
                                 <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="pcategory">Sub Category</label>
                                        <form:select path="subcategoryId"  class="form-control">
					                       <form:options items="${subcategoryMap}" />
					                   </form:select>
					                   <form:errors path="subcategoryId" class="error-message" /> 
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
              
              
              <form:textarea path="productDescription" required="true" class="form-control" rows="8" cols="80" />
              <form:errors path="productDescription"  class="error-message"   />
                       
                    <!-- <form><textarea id="editor1" name="editor1" rows="10" cols="80">
                                            This is my textarea to be replaced with CKEditor.
                    </textarea> </form>-->
              
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
                                        <label for="pprice">Price</label>
                                        <form:input path="price" class="form-control"  required="true" pattern="[0-9]+(\\.[0-9][0-9]?)?"  title="price should be decimal format. ex 100.50" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="pavailability"> Availability : </label>
 <!--  <input id="toggle-two" checked type="checkbox">  -->
										 <form:checkbox id="toggle-two"  path="availability" value="false"/>
										 <form:errors path="availability" class="error-message" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label for="pimage">Product Image (450 X 600)</label>
                                        <form:input type="file" path="image" accept='image/*'/>
                                    </div>
                                </div>
                                <div class="col-sm-8">    
                                    <img class="profile-user-img img-responsive" id="previewImage" src="${pageContext.request.contextPath}/productImage?id=${productForm.id}" alt="product picture" style="width: 130px;height:130px;">
                                    
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
                                <button type="submit" id="saveproduct" class="btn btn-primary"><i class="fa fa-save"></i> Save Product</button>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/customer/myproducts">Cancel</a>
                            </div>
                       

                        <hr>
 </form:form>
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
 
 
 