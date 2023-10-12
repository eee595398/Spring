<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>

    <link rel="stylesheet" href="/resources/css/myPage/myPage-style.css">

</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <section class="myPage-content">

            <jsp:include page="/WEB-INF/views/myPage/sideMenu.jsp" />

            <section class="myPage-main">

                <h1 class="myPage-title">내 정보</h1>
                <span class="myPage-subject">원하는 회원 정보를 수정할 수 있습니다.</span>

                <form action="info" method="POST" name="myPageFrm">

                    <div class="myPage-row">
                        <label>닉네임</label>
                        <input type="text" name="memberNickname"  maxlength="10">
                    </div>

                    <div class="myPage-row">
                        <label>전화번호</label>
                        <input type="text" name="memberTel"  maxlength="11">
                    </div>

                    <div class="myPage-row info-title">
                        <span>주소</span>
                    </div>



                    <div class="myPage-row info-address">
                        <input type="text" name="memberAddress" placeholder="우편번호">
                        <button type="button">검색</button>
                    </div>

                    <div class="myPage-row info-address">
                        <input type="text" name="memberAddress"  placeholder="도로명/지번 주소">                
                    </div>

                    <div class="myPage-row info-address">
                        <input type="text" name="memberAddress"  placeholder="상세 주소">                
                    </div>

                    <button class="myPage-submit">수정하기</button>
                </form>

            </section>

        </section>

    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <!-- 다음 주소 api 추가 -->
    
</body>
</html>