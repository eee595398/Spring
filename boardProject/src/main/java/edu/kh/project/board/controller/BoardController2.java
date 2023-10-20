package edu.kh.project.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import edu.kh.project.board.model.service.BoardService2;
import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/board2")
@SessionAttributes({"loginMember}"})
public class BoardController2 {
	
	@Autowired
	private BoardService2 service;
	
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
	
}
