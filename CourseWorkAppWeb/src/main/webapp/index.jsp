<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome ${users.username}</h1>
  <jsp:include page="/WEB-INF/views/_menu.jsp"></jsp:include>
</body>
</html>