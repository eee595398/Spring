<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<c:forEach items="${boardTypeList}" var="boardType">
    <c:if test="${boardType.BOARD_CODE == boardCode}" >
        <c:set var="boardName" value="${boardType.BOARD_NAME}"/>
    </c:if>
</c:forEach>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${boardName}</title>

    <link rel="stylesheet" href="/resources/css/board/boardDetail-style.css">
    <link rel="stylesheet" href="/resources/css/board/comment-style.css">

    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="board-detail">  
            <!-- 제목 -->
            <h1 class="board-title">제목  <span> - ${boardName}</span>    </h1>

            <!-- 프로필 + 닉네임 + 작성일 + 조회수 -->
            <div class="board-header">
                <div class="board-writer">

                    <!-- 프로필 이미지 -->
                    <img src="/resources/images/user.png">

                    <span>닉네임</span>

                    
                    <!-- 좋아요 하트 -->
                    <span class="like-area">
                        <i class="fa-regular fa-heart" id="boardLike"></i>
                        <%-- <i class="fa-solid fa-heart" id="boardLike"></i> --%>

                        <span>${board.likeCount}</span>
                    </span>

                </div>

                <div class="board-info">
                    <p> <span>작성일</span> 2023년 05월 09일 10:33:21 </p>     

                    <!-- 수정한 게시글인 경우 -->
                    <p> <span>마지막 수정일</span>   2023년 05월 09일 11:48:50 </p>   

                    <p> <span>조회수</span>  10 </p>                    
                </div>
            </div>

            <!-- 이미지가 있을 경우 -->
                

            <!-- 썸네일 영역(썸네일이 있을 경우) -->
            <h5>썸네일</h5>
            <div class="img-box">
                <div class="boardImg thumbnail">
                    <img src="https://via.placeholder.com/270x270">
                    <a href="#"">다운로드</a>         
                </div>
            </div>


            <!-- 업로드 이미지가 있는 경우 -->

            <!-- 업로드 이미지 영역 -->
            <h5>업로드 이미지</h5>
            <div class="img-box">
                
                <div class="boardImg">
                    <img src="https://via.placeholder.com/215x215">
                    <a href="#">다운로드</a>                
                </div>

                <div class="boardImg">
                    <img src="https://via.placeholder.com/215x215">
                    <a href="#">다운로드</a>                
                </div>

                <div class="boardImg">
                    <img src="https://via.placeholder.com/215x215">
                    <a href="#">다운로드</a>                
                </div>
                
                <div class="boardImg">
                    <img src="https://via.placeholder.com/215x215">
                    <a href="#">다운로드</a>                
                </div>

            </div>


            <!-- 내용 -->
            <div class="board-content">내용입니다
white-space: pre-wrap;
줄바꿈
띄    어   쓰   기
유지
됩니다
하하하
</div>


            <!-- 버튼 영역-->
            <div class="board-btn-area">

                <!-- 로그인한 회원과 게시글 작성자 번호가 같은 경우-->
                <button id="updateBtn">수정</button>
                <button id="deleteBtn">삭제</button>


                <button id="goToListBtn">목록으로</button>
            </div>


        </section>

        <!-- 댓글 include-->
        <jsp:include page="comment.jsp"/>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


</body>
</html>