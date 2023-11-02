<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보조회</title>

</head>
<body>
      <h2>회원정보 조회(회원번호검색)</h2>
   	<form action="/check">
        <input type="text" name="userNo" placeholder="회원 번호 입력">
        <button >조회</button>
    </form>

</body>
</html>