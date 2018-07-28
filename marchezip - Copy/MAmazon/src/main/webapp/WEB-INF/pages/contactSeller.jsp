<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Account Info</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 
 

 
   
   <form method="POST" action="contactSeller">
   <label>From:</label><p>${sender.userName}</p>
   
    <input type="hidden" name="reciepient" value="${reciepient.id}">
   <label>To:</label><p>${reciepient.userName}</p>
   <label>Message</label><p></p>
   <textarea name="message"></textarea>
   <input type="submit" value="Send Message" />
   </form>
 <h3>Recent Messages</h3>
 <c:forEach items="${listMessages}" var="msgInfo">
 		<table border="1">
 <tr>
   <th>Sender</th>
   <th>Recipient</th>
   <th>Message</th>
 
 </tr>
 <tr>
   <td> ${msgInfo.sender}  </td>



              


   <td>${msgInfo.recipient} </td>
   <td> ${msgInfo.message}</td></tr></table>
 
      
 
   </c:forEach>
 
 <c:if test="${not empty message}">
  
   <div class="message">${message}</div>
</c:if>
 
</body>
</html>