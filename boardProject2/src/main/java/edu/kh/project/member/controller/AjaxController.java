package edu.kh.project.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.AjaxService;

@Controller // 요청/응답 제어 + bean 등록
public class AjaxController {
	
	@Autowired
	private AjaxService service;

	
	// ** 닉네임으로 전화번호 조회
	@GetMapping("/selectMemberTel")
	@ResponseBody
	public String selectMemberTel(String nickname) {
							// 쿼리스트링에 담겨있는 파라미터
		
		
		// return / 포워드 -리다이렉트 > 새로운 화면 보임 (동기식)
		
		// return 데이터 -> 데이터를 요청한 곳으로 반환 (비동기식)
		
		// @ResponseBody
		// -> Controller의 결과로 데이터를 반환할 때 사용하는 어노테이션
		
		return service.selectMemberTel(nickname);
	}
	

	
	// **이메일로 회원 정보 조회
									 // !!!produces 속성은 한글 깨질 때 사용!!!
	@PostMapping(value="/selectMember", produces="application/json; charset=UTF-8" )
	@ResponseBody // Java 데이터 -> JSON, TEXT로 변환  + 비동기 요청한곳으로 응답
	public Member selectMember(@RequestBody Map<String, Object> paramMap) {
		
		// @RequestBody Map<String, Object> paramMap
		// -> 요청된 HTTP Body에 담긴 모든 데이터를 Map으로 반환
		
		// System.out.println("paramMap:: " + paramMap); // {email=user01@kh.or.kr}
		String email = (String)paramMap.get("email"); // user01@kh.or.kr
		
		return service.selectMember(email);
	}
	
	
	@GetMapping("/dupCheck/email")
	@ResponseBody
	public int checkEmail(String email) {
		return service.checkEmail(email);
	}
	
	
	@GetMapping("/dupCheck/nickname")
	@ResponseBody
	public int checkNickname(String nickname) {
		return service.checkNickname(nickname);
	}
	
	
	
	
	
	
	
	
	
	
}
