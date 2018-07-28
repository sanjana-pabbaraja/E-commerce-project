<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

                      
                    </div>
                    <!-- /.col-md-3 -->

                    <!-- *** CUSTOMER MENU END *** -->
                </div>

                <div class="col-md-9">
                    <div class="box">
                        <h3>Send Message</h3>
                      
                         <div class="table-responsive">
                            <table class="table table-hover dataTable" id="dataTables">
                                <thead>
                                    <tr>
                                    <th>Date</th>
                                    <th>From</th>
                                    <th>Message</th>
                             
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${messageForm}" var = "message">
                                    <tr>
                                     <td><c:out value = "${message.updateTime}" /></td>
                                       <td><c:out value = "${message.sender.userName}" /></td>
                                        <td><c:out value = "${message.message}" /></td>
                                      </tr>
                                    </c:forEach>
                                </tbody>
                                
                            </table>
                            
                         <form:form modelAttribute="newMessage" method="POST" action="${pageContext.request.contextPath}/customer/sendmsg?senderId=${senderId}&productId=${productId}">
						
                          <div class="form-group">
							<label for="message">message*</label> 
							<form:input type="message" path="message"  class="form-control" id="message" required="true"/>
						    <input type="submit" name="send">
						  </div>
                       </form:form>
                    
                            
                        
                     

                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                        </div>
                    

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
   

</body>

</html>
