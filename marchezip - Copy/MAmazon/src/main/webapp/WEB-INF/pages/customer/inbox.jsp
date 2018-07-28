<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
                        <li>Inbox</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** CUSTOMER MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">User Actions</h3>
                        </div>

                          
                       

                    </div>
                    <!-- /.col-md-3 -->

                    <!-- *** CUSTOMER MENU END *** -->
                </div>

                <div class="col-md-9" id="orders">
                    <div class="box">
                        <h2>Inbox</h2>

                      
                        <hr>

                        <div class="table-responsive">
                            <table class="table table-hover dataTable" id="dataTables">
                                <thead>
                                    <tr>
                                    <th>Date</th>
                                    <th>From</th>
                                    <th>Message</th>
                                    <th>Product</th>
                                    <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${inbox}" var = "message">
                                    <tr>
                                     <td><c:out value = "${message.updateTime}" /></td>
                                       <td><c:out value = "${message.sender.userName}" /></td>
                                        <td><c:out value = "${message.message}" /></td>
                                        <td><c:out value = "${message.product.productName}" /></td>
                                        
                                        <td><a href="${pageContext.request.contextPath}/customer/sendmsg?senderId=${message.sender.id}&productId=${message.product.id}" 
                                        class="btn btn-primary btn-sm">Reply</a> 
                                        </td>
                                        
                                        
                                        
                                        
                                        
                                    </tr>
                                    </c:forEach>
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
