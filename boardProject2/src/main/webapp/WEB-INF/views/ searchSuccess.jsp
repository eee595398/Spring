<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>성공</title>

</head>
<body>
      
    <h2>회원정보</h2>
    <c:forEach var="user" items="${userList}">
    <table style="border: 1px solid black;">
        <tr>

        <th>회원번호</th> <th>회원아이디</th> <th>회원이름</th> <th>회원나이</th>
        
        </tr>
        <c:forEach var="user" items="${userList}">
        
        <tr>
                
                <th>${user.userNo}</th>
                
                <th>${user.userId}</th>
                
                <th>${user.userName}</th>
                
                <th>${user.userAge}</th>
                
                </tr>

        </c:forEach>
    </table>
</body>
</html>