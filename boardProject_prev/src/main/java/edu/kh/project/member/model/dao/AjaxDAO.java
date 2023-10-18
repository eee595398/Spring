package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository // DB 연결 의미 + bean 으로 등록
public class AjaxDAO {

	@Autowired // bean 중에서 타입이 같은 객체를 DI
	private SqlSessionTemplate sqlSession;

	// 닉네임을 전화번호 조회
	public String selectMemberTel(String nickname) {
		
		return sqlSession.selectOne("ajaxMapper.selectMemberTel", nickname);
	}

	
	// 이메일로 회원정보 조회 
	public Member selectMember(String email) {
		
		return sqlSession.selectOne("ajaxMapper.selectMember", email);
	}

	// 이메일 중복검사
	public int checkEmail(String email) {
		
		return sqlSession.selectOne("ajaxMapper.checkEmail", email);
	}

	// 닉네임 중복검사
	public int checkNickname(String nickname) {
		
		return sqlSession.selectOne("ajaxMapper.checkNickname", nickname);
	}
	
}
