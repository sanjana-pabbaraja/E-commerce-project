<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />


<div id="all">

	<div id="content">
		<div class="container">

			<div class="col-md-12">

				<ul class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/index">Home</a></li>
					<li>Login in</li>
				</ul>

			</div>


			<div class="col-md-12">
				<div class="box" style="padding: 3% 30%;">
					<h1 style="text-align: center;">Login</h1>
					<!-- /login?error=true -->
					<c:if test="${param.error == 'true'}">
					<div class="alert alert-danger">
  <strong>Login Failed!!!</strong> Reason :
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</div>
					
					</c:if>
					<c:if test="${not empty message}">
						<div class="alert alert-info">
   ${message}.
</div>
						
						
					</c:if>

					

					<p style="text-align: center;" class="lead">Already our
						customer?</p>

					<hr>

					<form method="POST"
						action="${pageContext.request.contextPath}/j_spring_security_check">
						<div class="form-group">
							<label for="email">Email</label> <input type="text"
								class="form-control" name="email">
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" name="password">
						</div>
						<div class="text-center">
							<div class="col-md-4">
								<span class="loginanc"> <a
									href="${pageContext.request.contextPath}/register"
									class="loginanc">Register</a>
								</span>
							</div>
							<div class="col-md-4">

								<button type="submit" class="btn btn-primary">
									<i class="fa fa-sign-in"></i> Log in
								</button>
							</div>
							<div class="col-md-4">
								<span class="loginanc"> <a href="forgotpassword"
									class="loginanc">Forgot Password</a>
								</span>
							</div>
						</div>
						<br />
						<br />
					</form>
				</div>
			</div>


		</div>
		<!-- /.container -->
	</div>
	<!-- /#content -->


	<!-- *** FOOTER ***
       
 _________________________________________________________ -->
	<jsp:include page="footer.jsp" />


</div>

</html>
