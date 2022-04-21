<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Holiday List</title>
 </head>
 <body>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Holiday List</h3>


    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
      	  <th>Holiday ID</th>
      	   <th>Request Date</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Length</th>
          <th>Total days approved</th>
          <th>Status</th>
          <th>Peak Time</th>
          <th>Constraints</th>
          <th>Accept</th>
          <th>Reject</th>
          <tr>
       </tr>
          <tr>

       <c:forEach items="${holidaylist}" var="holiday" >
             <td>${holiday.id}</td>
             <td>${holiday.request_Made_Date}</td>
             <td>${holiday.end_Date}</td>
             <td>${holiday.start_Date}</td>
             <td>${holiday.lenght}</td>
             <td>${holiday.overall_lenght}</td>
             <td>${holiday.status}</td>
             <td>${holiday.peak_time}</td>
              <td>${holiday.constraints}</td>
             <td>
                <a href="holidayaccept?code=${holiday.id}">Accept</a>
             </td>
             <td>
                <a href="holidayreject?code=${holiday.id}">Reject</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 </body>
</html>
