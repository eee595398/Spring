package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Comment> select(int boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("commentMapper.selectList",boardNo);
	}






	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		 return sqlSession.insert("commentMapper.addComment",comment);
		

	}

}
