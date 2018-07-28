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
                        <li>My Profile</li>
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
                        <h2>View Profile</h2>
                        <hr>
                        
                        <form id="formDetails">
            <div class="box-body box-profile">
            
            <a id="edit" style="float:right;text-decoration :underline;font-weight:bold;cursor:pointer">Edit</a>

              <h3 class="profile-username text-center">Jhon William</h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>First Name</b> <a ><input class="pull-right" type="text" value="Jhon" style="border:none;text-align:right;background-color:white;cursor:not-allowed;width:60%;" disabled></a>
                </li>
                <li class="list-group-item">
                  <b>Last Name</b> <a ><input class="pull-right" type="text" value="William" style="border:none;text-align:right;background-color:white;cursor:not-allowed;width:60%;" disabled></a>
                </li>
                <li class="list-group-item">
                  <b>Email</b> <a><input class="pull-right" type="email" value="jwilliams12@gmail.com" style="border:none;text-align:right;background-color:white;cursor:not-allowed;width:60%;" disabled></a>
                </li>
                <li class="list-group-item">
                  <b>Phone</b> <a><input class="pull-right" type="text" value="9876543210" style="border:none;text-align:right;background-color:white;cursor:not-allowed;height:28px;width:60%;" disabled></a>
                </li>
              </ul>

                            <div class="text-center" style="">
                                <button type="submit" class="btn btn-primary" style="padding:6px 30px;">Submit</button>
                                <button type="submit" class="btn btn-primary" style="padding:6px 30px;" onclick="windows.reload();"> Cancel</button>
                            </div>                            

              
            </div>
            <!-- /.box-body -->
          </form>


                    </div>
                </div>

            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#edit").click(function(){
    	$("#formDetails :input").prop('disabled', false);
    	$("input.pull-right").css('background-color',"lightgrey","!important	");
    	$("input.pull-right").css('color',"white","!important	");
    	$("input.pull-right").css('cursor',"text","!important	");
    });
});
</script>

<jsp:include page="../footer.jsp" />


    </div>
    <!-- /#all -->

</html>
