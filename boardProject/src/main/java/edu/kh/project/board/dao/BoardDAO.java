package edu.kh.project.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public List<Map<String, Object>> selectBoardTypeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}
	
	
	

}
