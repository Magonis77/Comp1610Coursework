<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request a Holiday</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/_menu.jsp"></jsp:include>
<strong>Request a holiday below</strong>
<br/>
<br/>

	<form method = "GET">
        Your ID: <input type = "text" name = "id"/>
		</br>
		Start Date: <input type = "date" name = "startdate"/>
         <br />
		End Date: <input type = "date" name = "enddate"/>
         <br />
         <input type = "submit" value = "Request" />
	</form>

	    <% if (request.getParameter("id") != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("CourseWorkProducerServlet?action=requestholiday");
			request.setAttribute("action","requestholiday");
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("startdate", request.getParameter("startdate"));
			request.setAttribute("enddate", request.getParameter("enddate"));
			rd.forward(request, response);
		}
		%>

</body>
</html>