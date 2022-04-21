<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Holiday List by user </title>
 </head>
 <body>

<a href='HolidaysServlet?action=getallholidays'>Back</a><br/>

    <h3>Holiday List by user</h3>


     <table border="1" cellpadding="5" cellspacing="1" >
         <tr>
      	  <th>Holiday ID</th>
      	  <th>Request Date</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Lenght</th>
          <th>Status</th>
          <th>Decision made on</th>
          <tr>
       </tr>
          <tr>
       <c:forEach items="${holidaylist}" var="holiday" >
             <td>${holiday.id}</td>
			 <td>${holiday.request_Made_Date}</td>
             <td>${holiday.start_Date}</td>
             <td>${holiday.end_Date}</td>
             <td>${holiday.lenght}</td>
             <td>${holiday.status}</td>
             <td>${holiday.date_Decision_Made}</td>
       
          </tr>
       </c:forEach>
    </table>



 </body>
</html>
