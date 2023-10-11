package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.AjaxDAO;
import edu.kh.project.member.model.dto.Member;

@Service // 서비스임을 명시 + bean 등록
public class AjaxServiceImpl implements AjaxService{

	@Autowired
	private AjaxDAO dao;

	// 닉네임으로 전화번호 조회
	@Override
	public String selectMemberTel(String nickname) {
		
		return dao.selectMemberTel(nickname);
	}

	// 이메일로 회원정보 조회
	@Override
	public Member selectMember(String email) {
	
		return dao.selectMember(email);
	}

	// 이메일 중복검사
	@Override
	public int checkEmail(String email) {
		
		return dao.checkEmail(email);
	}

	// 닉네임 중복검사
	@Override
	public int checkNickname(String nickname) {
		
		return dao.checkNickname(nickname);
	}
}
