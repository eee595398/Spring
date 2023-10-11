<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/resources/css/main-style.css">


<header>

     <!-- 클릭 시 메인페이지로 이동하는 로고 -->
     <section>
         <a href="/">
             <img src="/resources/images/logo.jpg" id="homeLogo">
         </a>
     </section>

     <!-- 검색창 부분 -->
     <section>
         <section class="search-area">

             <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
             <form action="/search" method="GET" name="search-form">

                 <!-- fieldset : form 내부에서 input을 종류별로 묶는 용도로 자주 사용 -->
                 <fieldset>

                     <!-- search : 텍스트 타입과 기능적으로는 똑같으나, 
                         브라우저에 의해 다르게 표현될 수 있음 .-->
                     <!-- autocomplete : HTML 기본 자동완성 사용 X -->
                     <input type="search" id="query" name="query"
                         autocomplete="off" placeholder="검색어를 입력해주세요."
                     >

                     <button id="searchBtn" class="fa-solid fa-magnifying-glass"></button>
                 </fieldset>

               
             </form>

         </section>
     </section>

     <section></section>
 </header>

 <nav>
     <ul>
         <li><a href="#">공지사항</a></li>
         <li><a href="#">자유게시판</a></li>
         <li><a href="#">질문게시판</a></li>
         <li><a href="#">FAQ</a></li>
         <li><a href="#">1:1문의</a></li>
     </ul>
 </nav>
