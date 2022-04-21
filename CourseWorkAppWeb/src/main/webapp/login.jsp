<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h1>
Login:
</h1> 
<hr/>  
    <jsp:include page="/WEB-INF/views/_menu.jsp"></jsp:include>
<h3>Login Form</h3>  
	<form method="GET">
		Username: <input type="text" name="username"> <br />
		Password: <input type="password" name="password"> <br /> 
		<input type="submit" value="Add" />
	</form>

	<%
		if (request.getParameter("username") != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("loginServlet");
			request.setAttribute("username", request.getParameter("username"));
			request.setAttribute("password", request.getParameter("password"));
			
			rd.forward(request, response);
		}
	%>
</form>  
</body>
</html>