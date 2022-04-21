<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%@ page import="model.UserDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee holidays</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/_menu.jsp"></jsp:include>
<%
if (request.getParameter("cbxUser") != null) {
	
			response.sendRedirect("AdminServlet?action=HolidaysByUser" + "&UserID=" + 
									request.getParameter("cbxUser"));
											
											
}	%>
<%
	@SuppressWarnings("unchecked")
	List<UserDTO> userlist = (List<UserDTO>) session.getAttribute("userlist");
	
	%>

	<form action="HolidayByUserA.jsp" method = "post">
	<a href='index.jsp'>Home</a><br/>
         Select a User:&nbsp; <select name="cbxUser" style="width: 200px">
			<c:forEach items="${userlist}" var="user">
				<option value="${user.id}">${user.username}</option>
			</c:forEach>
			
		</select>
		 <input type = "submit" value = "Select" />
      </form>
</body>
</html>