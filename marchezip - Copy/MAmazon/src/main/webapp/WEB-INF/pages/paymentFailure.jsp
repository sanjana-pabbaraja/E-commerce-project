<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Payment Failed</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 <jsp:include page="header.jsp" />
 
<div id="all">

	<div id="content">
		<div class="container" style="min-height:300px">
 
   <div class="page-title">Payment Failed</div>
  
   <h3 style="color:red;">Please check the details and try again!</h3>
   <a href="${pageContext.request.contextPath}/shoppingCart">
      Go to Cart
   </a>
  
  </div>
  </div>
  </div>
	<jsp:include page="footer.jsp" />

</body>
</html>