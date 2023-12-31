package edu.kh.project.myPage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository
public class MyPageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int updateInfo(Member updateMember) {
		// TODO Auto-generated method stub
		return sqlSession.update("myPageMapper.updateInfo",updateMember);
	}

	// 프로필 이미지 수정 
	public int updateProfileImage(Member loginMember) {
		// TODO Auto-generated method stub

		return sqlSession.update("myPageMapper.updateProfileImage",loginMember);
	}

}
