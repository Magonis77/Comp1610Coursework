<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Holiday</title>
</head>
  <jsp:include page="/WEB-INF/views/_menu.jsp"></jsp:include>
      
	<%
		if (request.getParameter("id") != null) {
			
			response.sendRedirect("HolidaysServlet?action=Requestholiday" + "&id=" + 
							request.getParameter("id") + "&startdate=" + 
							request.getParameter("startdate")+ "&enddate=" + 
							request.getParameter("enddate"));
			}
	%>
<body>

	<form action="requestHoliday.jsp" method = "post">
		Your ID: <input type = "text" name = "id"/>
		</br>
		Start Date: <input type = "date" name = "startdate"/>
         <br />
		End Date: <input type = "date" name = "enddate"/>
         <br />
         <input type = "submit" value = "Request" />
      </form>

</body>
</html>