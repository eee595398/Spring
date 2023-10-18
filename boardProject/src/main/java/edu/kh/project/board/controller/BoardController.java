package edu.kh.project.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;

@SessionAttributes({"loginMember"})
@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	/*  목록 조회 : /board/1?cp=1 (cp: current page(현재페이지))
	 *  상세 조회 : /board/1/1500?cp=1
	 *  
	 *  ** 컨트롤러 따로 생성 **
	 *  삽입 : /board2/1/insert 
	 *  수정	: /board2/1/1500/update
	 *  삭제	: /board2/1/1500/delete
	 * */
	
	/*
	 * ******** @PathVariable 사용 시 문제점과 해결법 *****
	 * 
	 * 문제점 : 요청 주소와 @PathVariable로 가져다 쓸 주소의 레벨이 같다면
	 * 		 구분하지 않고 모두 매핑되는 문제가 발생
	 * 
	 * 해결방법 : @PathVariable 지정 시 정규 표현식 사용
	 * {키:정규표현식}
	 * 
	 * 
	 * @PathVariable : URL 경로에 있는 값을 매개변수로 이용할 수 있게하는 어노테이션
	 * + request scope에 세팅
	 * 
	 * - 자원(resource) 구분/식별
	 * ex) github.com/cmhinst
	 * ex) github.com/testUser
	 * ex) /board/1 -> 1번(공지사항) 게시판
	 * ex) /board/2 -> 2번(자유 게시판) 게시판
	 * 
	 * query string 을 사용하는 경우
	 * - 검색, 정렬, 필터링
	 * ex) search.naver.com?query=날씨
	 * ex) search.naver.com?query=종로맛집
	 * ex) /board2/insert?code=1
	 * ex) /board2/insert?code=2
	 * -> 삽입이라는 공통된 동작 수행
	 * 		단, code에 따라 어디에 삽입할지 지정(필터링)
	 * 
	 * ex) /board/list?order=recent (최신순)
	 * ex) /board/list?order=most  (인기순)
	 * 
	 * */
	
	// 게시글 목록 조회
	@GetMapping("/{boardCode:[0-9]+}") // boardCode는 1자리 이상 숫자
	public String selectBoardList( @PathVariable("boardCode") int boardCode,
								@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
								Model model
							) {
		
		
		// boardCode 확인
		//System.out.println("boardCode : " + boardCode);
		
		
		// 게시글 목록 조회 서비스 호출
		Map<String, Object> map = service.selectBoardList(boardCode, cp);
		
		// 조회 결과를 request scope에 세팅 후 forward
		model.addAttribute("map", map);
		
		
		return "board/boardList";
	}
	
	//@PathVariable : 주소에 지정된 부분을 변수에 저장
	//				+ request scope 세팅
	
	// 게시글 상세 조회
	// boardCode 게시판이 뭐냐를 나타내는 숫자
	// cp 페이지가 몇페이지를 나타내는 숫자 
	@GetMapping("/{boardCode}/{boardNo}")
	public String boardDetail(
				@PathVariable("boardCode") int boardCode,
				@PathVariable("boardNo") int boardNo,
				Model model, // 데이터 전달용 객체
				RedirectAttributes ra // 리다이렉트 시 데이터 전달 객체
			) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		// 게시글 상세 조회 서비스 호출
		Board board = service.selectBoard(map);
		
		String path = null;
		
		if(board != null) { // 조회 결과가 있을 경우
			
			// 로그인 상태인 경우 
		
			
		
				// 회원 번호를 얻어와
				// 좋아요 여부 확인 서비스 호출
				// 결과값 통해 분기처리 
					// 누르적이 있을 경우 처리
					// "likeCheck"
				
			
			
			
			
			
			path = "board/boardDetail"; // forward 할 jsp 경로
			model.addAttribute("board",board);
			
		} else { // 조회 결과가 없을 경우
			path = "redirect:/board/" + boardCode;
			// 게시판 첫페이지로 리다이렉트
			
			ra.addFlashAttribute("message", "해당 게시글이 존재하지 않습니다");
			
		}
		
		return path;
		
	}
	
	
	
	
}
