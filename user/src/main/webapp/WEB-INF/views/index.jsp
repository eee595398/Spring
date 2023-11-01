<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="ko">

		<head>
		
		<meta charset="UTF-8">
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
			
			<title>체크리스트1</title>
		
		</head>
		
		<body>
		
			<h1>회원 정보 조회(아이디 검색)</h1>
			
			<form action="/selectUser">
			
			<!--버튼을 누르면 내용을 가지고 selectUser로 보냄  -->
	
			<input type="text" name="userId" placeholder="회원 아이디 입력">
			
			<button>조회</button>
			
			</form>
			
			
		
		</body>