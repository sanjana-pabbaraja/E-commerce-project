<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
<style>
   table  {
       margin-top: 10px;
       border: solid black 1px;
   }
   table  td {
        padding: 5px;
   }
   .message  {
        font-size:90%;
        color:blue;
        font-style:italic;
        margin-top:30px;
   }
</style>
</head>
<body>
<table border="0">
<tr>



 <c:if test="${not empty pageContext.request.userPrincipal.name}">
  <td>Welcome, ${pageContext.request.userPrincipal.name}</td><td><a href="${pageContext.request.contextPath}/logout" >logout</a></td>
   
</c:if>
 <c:if test="${empty pageContext.request.userPrincipal.name}">
 <% String redirectURL = "/shopping/login"; response.sendRedirect(redirectURL); %>
  <td><a href="${pageContext.request.contextPath}/login" >login</a></td>
   <td><a href="${pageContext.request.contextPath}/register" >register</a></td>
</c:if>
 </tr>
</table>
 
<a href="${pageContext.request.contextPath}/createProduct">Create Product</a>

 
<br/>
 
 
<table border="1">
 <tr>
   <th>Category</th>
   <td>Image</td>
   <th>Product Name</th>
   <th>Description</th>
   <th>Price</th>   
   <th>Seller</th>
   <th>Delete</th><th>Edit</th>

   <th>Buy</th><th>Contact</th>
 </tr>
 
 <c:forEach items="${productInfos}" var="info">
 
 <tr>
   <td> ${info.categoryId}  </td>



              <td>  <img src="${pageContext.request.contextPath}/productImage?id=${info.id}" width="100"/></td>


   <td>${info.name} </td>
   <td> ${info.productDescription}</td>
   <td> ${info.price}</td>
   <td>  </td>
   <td> <a href="deleteProduct?id=${info.id}">Delete</a> </td>
   <td> <a href="editProduct?id=${info.id}">Edit</a> </td>
   <td> <a href="addtoCart?id=${info.id}">Add to cart</a> </td>
   <td> <a href="contactSeller?id=${info.id}&product=${info.id}">Contact Seller</a> </td>
 </tr>    
 
 </c:forEach>
</table>
<c:if test="${not empty message}">
  
   <div class="message">${message}</div>
</c:if>

 
</body>
</html>