<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Employee List</title>
 </head>
 <body>
     <jsp:include page="/WEB-INF/views/_menunologin.jsp"></jsp:include>
      
    <h3>Employee List</h3>


    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Password</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Gender</th>
          <th>Join Date</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${userlist}" var="user" >
          <tr>
             <td>${user.id}</td>
             <td>${user.username}</td>
             <td>${user.password}</td>
             <td>${user.firstname}</td>
             <td>${user.lastname}</td>
             <td>${user.gender}</td>
             <td>${user.joinDate}</td>
             <td>
                <a href="edituser?code=${user.id}">Edit</a>
             </td>
             <td>
                <a href="deleteuser?code=${user.id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>

    <a href="LRLServlet?action=register" >Create Employee</a>


 </body>
</html>