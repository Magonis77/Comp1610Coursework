<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<%@ page import="model.UserDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Holiday List</title>
 </head>
 <body>
 
<%
if (request.getParameter("cbxUser") != null) {
	
			response.sendRedirect("AdminServlet?action=HolidaysByUser" + "&UserID=" + 
									request.getParameter("cbxUser"));
											
											
}	%>
<%
	@SuppressWarnings("unchecked")
	List<UserDTO> userlist = (List<UserDTO>) session.getAttribute("userlist");
	
	%>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Holiday List</h3>


    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
      	  <th>Holiday ID</th>
      	  <th>Request Date</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Lenght</th>
          <th>Status</th>
          <th>Decision made on</th>
          <tr>
       </tr>
          <tr>
       <c:forEach items="${holidaylist}" var="holiday" >
             <td>${holiday.id}</td>
			 <td>${holiday.request_Made_Date}</td>
             <td>${holiday.start_Date}</td>
             <td>${holiday.end_Date}</td>
             <td>${holiday.lenght}</td>
             <td>${holiday.status}</td>
             <td>${holiday.date_Decision_Made}</td>
       
          </tr>
       </c:forEach>
    </table>
<form action="HolidayByUserA.jsp" method = "post">
         Select a User:&nbsp; <select name="cbxUser" style="width: 200px">
			<c:forEach items="${userlist}" var="user">
				<option value="${user.id}">${user.username}</option>
			</c:forEach>
			
		</select>
		 <input type = "submit" value = "Select" />
      </form>


 </body>
</html>
