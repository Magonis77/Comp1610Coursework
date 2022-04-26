<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<%@ page import="model.UserDTO"%>
<%@ page import="model.RolesDTO"%>
<%@ page import="model.DepartmentsDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Employee</title>
</head>
<body>
     <jsp:include page="/WEB-INF/views/_menunologin.jsp"></jsp:include>
      
	<%
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			   LocalDateTime now = LocalDateTime.now(); 
		if (request.getParameter("username") != null) {
			
			response.sendRedirect("LRLServlet?action=registeruser" + "&username=" + 
							request.getParameter("username") + "&cxRole=" + 
							request.getParameter("cxRole")+ "&cxDepartment=" + 
							request.getParameter("cxDepartment")+ 
						    "&Gender=" + request.getParameter("Gender")+
							"&FirstName=" + request.getParameter("FirstName") +
							"&LastName=" + request.getParameter("LastName")
			+ "&password=" + request.getParameter("password") 
			
			+"&JoinDate=" + dtf.format(now));
			}
	%>

	<%
	@SuppressWarnings("unchecked")
			List<RolesDTO> rolelist = (List<RolesDTO>) session.getAttribute("rolelist");
			@SuppressWarnings("unchecked")
			List<DepartmentsDTO> departmentlist = (List<DepartmentsDTO>) session.getAttribute("departmentlist");
	%>

	<form action="register.jsp" method = "post">
		Username: <input type = "text" name = "username"/>
		</br>
		Password: <input type = "text" name = "password"/>
		</br>
		Gender: <select name="Gender" id="Gender">
  <option value="Male">Male</option>
  <option value="Female">Female</option>
</select></br>
		FirstName: <input type = "text" name = "FirstName"/>
		</br>
		LastName: <input type = "text" name = "LastName"/>
		</br>
		 Select a Role:&nbsp; <select name="cxRole" style="width: 300px">
		<c:forEach items="${rolelist}" var="role">
		  <option value="${role.id}">${role.role}</option>
			</c:forEach>
		</select>
		</br>
		 Select a Department:&nbsp; <select name="cxDepartment" style="width: 200px">
		<c:forEach items="${departmentlist}" var="department">
		  <option value="${department.id}">${department.department}</option>
			</c:forEach>
		</select>
         <br />
         <input type = "submit" value = "Add" />
      </form>

</body>
</html>