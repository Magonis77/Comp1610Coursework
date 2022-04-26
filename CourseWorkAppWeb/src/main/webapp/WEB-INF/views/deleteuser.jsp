<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Employee Delete</title>
 </head>

 <body>
      <jsp:include page="/WEB-INF/views/_menunologin.jsp"></jsp:include>
      
   
    <h3>Employee Deleted</h3>

    <a href="LRLServlet?action=showAllUsers">Employee List</a>
   
   
 </body>
</html>