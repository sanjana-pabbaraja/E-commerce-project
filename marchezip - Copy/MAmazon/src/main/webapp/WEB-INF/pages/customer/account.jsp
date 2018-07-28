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
                        <li>View Profile</li>
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
                        <h2>View Profile </h2>
                        <hr>
                        
                        <form id="formDetails">
            <div class="box-body box-profile">
            
            <a id="edit" style="float:right;text-decoration :underline;font-weight:bold;cursor:pointer">Edit</a>

              <h3 class="profile-username text-center">${account.userName}</h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Name</b> <a ><input class="pull-right" type="text" value="${account.userName}" style="border:none;text-align:right;background-color:white;cursor:not-allowed;width:60%;" disabled></a>
                </li>
                
                <li class="list-group-item">
                  <b>Email</b> <a><input class="pull-right" type="email" value="${account.email}" style="border:none;text-align:right;background-color:white;cursor:not-allowed;width:60%;" disabled></a>
                </li>
                <li class="list-group-item">
                  <b>Phone</b> <a><input class="pull-right" type="text" value="${account.mobileno}" style="border:none;text-align:right;background-color:white;cursor:not-allowed;height:28px;width:60%;" disabled></a>
                </li>
              </ul>
              
              <!-- Privacy Settings -->
              
<div class="box box-default collapsed-box" id="privacy-box" style="padding:10px;">
              <h5 class="box-title" style="display:inline-block;">Privacy Settings</h5>
              <div class="box-tools pull-right">
                <button type="button" id="privacy-button" class="btn btn-box-tool" data-widget="collapse" style="background-color: transparent;" disabled><i class="fa fa-plus"></i>
                </button>
              </div>
              <!-- /.box-tools -->

            <div class="box-body" style="display:none;">

              <ul class="list-group list-group-unbordered">

                <li class="list-group-item">
                  <b>Email</b> <a><input id="toggle-one" class="pull-right" checked type="checkbox" ></a>
                </li>
                <li class="list-group-item">
                  <b>Phone</b> <a><input id="toggle-two" class="pull-right" checked type="checkbox" ></a>
                </li>
              </ul>

            </div>
            <!-- /.box-body -->
          </div>              
              
              <!-- End Of Privacy Settings -->
              
              

                            <div class="text-center" style="">
                                <button type="submit" class="btn btn-primary" style="padding:6px 30px;">Submit</button>
                                <button type="submit" class="btn btn-primary" style="padding:6px 30px;" onclick="windows.reload();"> Cancel</button>
                            </div>                            

              
            </div>
            <!-- /.box-body -->
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">

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

<!-- AdminLTE App -->
<script src="../js/app-min-js/app.min.js"></script>

<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>    
<script type="text/javascript">

    $(document).ready(function() {
    	    	
    	$('#toggle-one').bootstrapToggle();
    	$('#toggle-two').bootstrapToggle();


    });

</script>

</html>
