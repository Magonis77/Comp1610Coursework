<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.UserDTO"%>
<%@ page import="model.RolesDTO"%>
<%@ page import="model.DepartmentsDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit User</title>
   </head>
   <body>
   <%
	@SuppressWarnings("unchecked")
	List<RolesDTO> rolelist = (List<RolesDTO>) session.getAttribute("rolelist");
   @SuppressWarnings("unchecked")
	List<UserDTO> userlist = (List<UserDTO>) session.getAttribute("userlist");
	@SuppressWarnings("unchecked")
	List<DepartmentsDTO> departmentlist = (List<DepartmentsDTO>) session.getAttribute("departmentlist");
	
	%>
       <jsp:include page="_menu.jsp"></jsp:include>
      
      <h3>Edit User</h3>
      
      <p style="color: red;">${errorString}</p>
      
       <c:if test="${not empty user}">
         <form method="POST" action="edituser">
            <input type="hidden" name="ID" value="${user.id}" />
         <table border="0">
            <tr>
               <td>username: </td>
               <td><input type="text" name="username" value="${user.username}"/></td>
            </tr>
            <tr>
               <td>password: </td>
               <td><input type="password" name="password" value="${user.password}"/></td>
            </tr>

        <td> First Name:</td><td> <input type = "text" name = "FirstName" value="${user.firstname}"/></td>
		</tr>
            <tr>
            <tr>
               <td>Last Name:</td>
               <td><input type = "text" name = "LastName" value="${user.lastname}"/></td>
            </tr>
             </tr>
       <td> Date Joined:</td><td> <input type = "text" name = "JoinDate" value="${user.joinDate}" readonly/></td>
            <tr>
            <tr>
               <td>Gender:</td>
               <td><select name="Gender" id="Gender">
  <option value="Male">Male</option>
  <option value="Female">Female</option>
</select></td>
            </tr>
            <tr>
            <tr>
               <td>Select a Role:&nbsp;</td>
               <td> <select name="cxRole" style="width: 300px">
		<c:forEach items="${rolelist}" var="role">
		  <option value="${role.id}">${role.role}</option>
			</c:forEach>
		</select></td>
            </tr>
            <tr>
            <tr>
               <td>Select a Department:&nbsp;</td>
               <td><select name="cxDepartment" style="width: 200px">
		<c:forEach items="${departmentlist}" var="department">
		  <option value="${department.id}">${department.department}</option>
			</c:forEach>
		</select></td>
            </tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="LRLServlet?action=showAllUsers">Cancel</a>
               </td>
            </tr>
         </table>
         
      </form> 
         </c:if>  
   </body>
</html>