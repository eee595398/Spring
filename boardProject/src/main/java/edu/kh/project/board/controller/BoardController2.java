package edu.kh.project.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.board.model.service.BoardService2;
import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/board2")
@SessionAttributes({"loginMember}"})
public class BoardController2 {
	
	@Autowired
	private BoardService2 service; // 삽입, 수정 ,삭제 
	
	@Autowired
	private BoardService boardService; // 목록, 상세조회
	
	// 게시글 작성 화면 전환
	@GetMapping("/{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode) {
		// @PathVariable : 주소 값 가져오기 + request scope에 값 올리기 
		
		
		return"board/boardWrite";
		
		
		
	}
	
	// 게시글 작성
	@PostMapping("/{boardCode:[0-9]+}/insert")
	public String boardInsert(
			 @PathVariable("boardCode") int boardCode
			,Board board // 커맨드 객체(필드에 파라미터 담겨있음)
			, @RequestParam(value="images", required = false)List<MultipartFile> images  //이미지 이름 가져옴  타입이 전부 파일임 그래서 
			, @SessionAttribute("loginMember") Member loginMember  // 위에 loginMember 만들어놈
			, RedirectAttributes ra // 메세지 전달용 객체
			, HttpSession session
			)throws IllegalStateException, IOException {
		
		// 파라미터 : 제목, 내용, 파일(0~5개)
		// 파일 저장 경로 : HttpSession
		// 세션 : 로그인한 회원의 번호
		// 리다이렉트 시 데이터 전달 : RedirectAttributes ra (메세지 전달)
		
		/* List<MultipartFile>
		 *  업로된 이미지가 없어도 List에 MultipartFile 요소는 존재함 
		 *  
		 *  단 업로드된 이미지가 MultipartFile 요소는 
		 *  파일 크기 size가 0 또는 파일(getOriginalFileName()) "" 빈칸
		 *  
		 * */
		
		// 1. 로그인 회원 번호를 얻어와 board 세팅 
		
		board.setMemberNo(loginMember.getMemberNo());
		
		// 2. boardCode도 board 세팅 
		board.setBoardCode(boardCode);
		
		// 3. 업로드 된 이미지 서버에 실제로 저장되는 경로
		// + 웹에서 요청 시 이미지를 볼 수 있는 경로(웹 접근 경로)
		// filePath는 실제경로
		
		String webPath ="/resources/images/board/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		// 게시글 삽입 서비스 호출 후 삽입된 게시글 번호 반환 받기 
		int boardNo = service.boardInsert(board, images, webPath, filePath);
		
		
		
		// 게시글 삽입 성공 시
		
		// -> 방금 삽입한 게시글의 상세 조회 페이지 리다이렉트
		// -> /board/{boardCode}/{boardNo}
		
		String message=null;
		String path= "redirect:";
		
		if(boardNo>0) {
			
			message ="게시글이 등록되었었습니다.";
			path += "/board/" +boardCode+ "/" + boardNo;
			
		}else {
			message ="게시글 등록 실패 ";
			path +="insert";
			
		}
		
		ra.addFlashAttribute("message" ,message);
		
		
		
		
		
		return path;
		
	}
	
	
	// 게시글 수정 화면 전화 
	@GetMapping("/{boardCode}/{boardNo}/update")
	public String boardUpdate(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			Model model
			// Model : 데이터 전달용 객체(기본 scope : request)
			
			
			) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		Board board = boardService.selectBoard(map);
		
		model.addAttribute("board", board);
		
		
		
		return "board/boardUpdate";
		
	}
	
	// 게시글 수정 
	@PostMapping("/{boardCode}/{boardNo}/update")
	public String boardUpdate(
			Board board, // 커맨드 객체(name == 필드 경우 필드에 파라미터 세팅)
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp, // 쿼리스트링 유지
			@RequestParam(value="images", required = false) List<MultipartFile> images,// 업로드된 파일 리스트
			@RequestParam(value="deleteList", required = false) String deleteList, // 삭제할 이미지 순서 
			HttpSession session, // 서버 파일 저장 경로 얻어올 용도
			RedirectAttributes ra // 리다이렉트 시 값 전달용(message)
			)throws IllegalStateException, IOException {
		
		
		// 1) boardCode, boardNo를 커맨드 객체(board)에 세팅
		board.setBoardCode(boardCode);
		board.setBoardNo(boardNo);
		
		// board(boardCode, boardNo, boardTitile, boardContent)
		
		
		// 2) 이미지 서버 저장경로, 웹 접근 경로
		String webPath = "/resources/images/board/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		//getServletContext() == 웹 어플리케이션
		
		
		// 3) 게시글 수정 서비스 호출
		
		int rowCount = service.boardUpdate(board, images, webPath, filePath, deleteList);
		
		
		// 4) 결과에 따라 message, path 설정 
		
		String message = null;
		String path = "redirect:";
		
		if(rowCount>0) {
			message = "게시글이 수정되었습니다";
			path += "/board/" +boardCode + "/" +boardNo + "?cp="+cp;
			
		} else {
			message="게시글 수정 실패";
			path += "update";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
		
	}
	
	
	
	// 게시글 삭제 
	
	@GetMapping("/{boardCode}/{boardNo}/delete")
	public String boardDelete(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			HttpSession session,
			RedirectAttributes ra
			) {
	
		//boardCode, boardNo 서비스로 넘겨야함
		// map으로 담아서 보내느걸 추천
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		int rowCount = service.boardDelete(map);
		
		
		// 결과값이 >0
		// "삭제되었습니다." 
		// /board/{boardCode}
		
		// 삭제실패
		// "삭제 실패"
		// /board/{boardCode} / {boardNo}
		
		
		return null;
		
	}
	
	
	
	
}
