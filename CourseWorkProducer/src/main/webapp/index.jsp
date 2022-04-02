<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<strong>Book Producer to put a new book title into the Queue channel</strong>
<br/>
<br/>

	<form method = "GET">
         Select Start Date: <input type = "Date" name = "StartDate">
          Select End Date: <input type = "Date" name = "EndDate">
         <br />
         <input type = "submit" value = "Add" />
	</form>

	    <% if (request.getParameter("StartDate") != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet?action=RequestHoliday");
			request.setAttribute("action","RequestHoliday");
			request.setAttribute("StartDate", request.getParameter("StartDate"));
			request.setAttribute("EndDate", request.getParameter("EndDate"));
      	
			rd.forward(request, response);
		}
		%>

</body>
</html>