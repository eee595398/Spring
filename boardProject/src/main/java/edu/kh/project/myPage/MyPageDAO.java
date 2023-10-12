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
		return sqlSession.update("mypageMapper.updateInfo",updateMember);
	}

}
