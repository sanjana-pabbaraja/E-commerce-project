<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />




<div id="all">

	<div id="content">
		<div class="container">

			<div class="col-md-12">

				<ul class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/index">Home</a></li>
					<li>Register</li>
				</ul>

			</div>

			<div class="col-md-12">
				<div class="box" style="padding: 3% 30%;">
					<h1 style="text-align: center;">New account</h1>

					
					<hr>


<c:if test="${not empty message}">

		<div class="alert alert-info">${message}</div>
	</c:if>


					<form:form modelAttribute="registrationForm" method="POST" action="${pageContext.request.contextPath}/register">
						<div class="form-group">
							<label for="name">Name*</label> 
							<form:input  path="uname" class="form-control" id="uname" required="true"/>							
							<form:errors path="uname" class="error-message" />
						</div>
						<div class="form-group">
							<label for="email">Email*</label> 
							<form:input type="email" path="email"  class="form-control" id="email" required="true"/>
								<form:errors path="email" class="error-message" />
						</div>
					
						<div class="form-group" >
							<label for="mobileno">Mobile Number*</label> 
							<form:input path="mobileno" class="form-control" id="mobileno" required="true" pattern="[7-9]{1}[0-9]{9}" title="Phone number with 7-9 and remaing 9 digit with 0-9"/>
								<form:errors path="mobileno" class="error-message" />
						</div>
						
						<div class="form-group">
							<label for="password">Password*</label> 
							<form:input type="password" path="password" id="password" class="form-control" required="true"/>
							<form:errors path="password" class="error-message" />
							
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-primary">
								<i class="fa fa-user-md"></i> Register
							</button>
						</div>
					</form:form>
				</div>
			</div>

			


		</div>
		<!-- /.container -->
	</div>
	<!-- /#content -->


	<jsp:include page="footer.jsp" />


</div>
<!-- /#all -->






</body>

</html>
