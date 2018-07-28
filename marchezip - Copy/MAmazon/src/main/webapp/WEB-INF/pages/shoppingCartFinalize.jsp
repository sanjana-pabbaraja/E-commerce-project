<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart Finalize</title>
 
 
</head>
<body>
 <jsp:include page="header.jsp" />
 
<div id="all">

	<div id="content">
 
  
  
   <div class="container"  style="min-height:300px">
    <div class="page-title">Order Success</div>
    
       <h3>Thank you for Ordering on Market Amazon</h3>
       Your order number is: ${lastOrderedCart.orderNum}
       
       <br/>
       <a class="navi-item"
               href="${pageContext.request.contextPath}/accountInfo">Continue
               Shopping</a>
       
   </div>

  </div>
  </div>
	<jsp:include page="footer.jsp" />
</body>
</html>