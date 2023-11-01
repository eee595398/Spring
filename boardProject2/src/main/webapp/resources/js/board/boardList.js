const insertBtn = document.querySelector("#insertBtn");

// 글쓰기 버튼 클릭시 
if(insertBtn !=null){

	// 예외 처리 방법이다
	// 로그인 여부에 따라 insertBtn이 있는가 없는가에 대한 예외처리
	
	insertBtn.addEventListener("click",()=>{
			// 해당 주소로 요청을 보내주세요 
			
			//Js 객체 중 location
			// 	loction.href= "주소";
			// 해당 주소로 요청(GET 방식) 
			
			// `${변수}` 로 사용가능 템플릿 리퍼럴
			
			location.href= `/board2/${location.pathname.split("/")[2]}/insert`;
			//       /board2/1/insert 이런식으로 들어감  
			
	
	});

}



