<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Delete User</title>
 </head>

 <body>
      <jsp:include page="_menu.jsp"></jsp:include>
      
   
    <h3>User Deleted</h3>

    <a href="LRLServlet?action=showAllUsers">User List</a>
   
   
 </body>
</html>