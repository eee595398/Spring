package edu.kh.project.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImage;

@Repository
public class BoardDAO2 {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	

	/** 게시글 삽입
	 * @param board
	 * @return boardNo
	 */
	public int boardInsert(Board board) {
		// TODO Auto-generated method stub
		
		int result = sqlSession.insert("boardMapper.boardInsert", board);
		
		
		// 삽입 성공시 
		if(result > 0) result = board.getBoardNo();
		
		
		return result;
	}

	

	/** 이미지 리스트 삽입
	 * @param uploadList
	 * @return result
	 */
	public int insertImageList(List<BoardImage> uploadList) {
		// TODO Auto-generated method stub
		
		return sqlSession.insert("boardMapper.insertImageList", uploadList);
	}

	
	
	
	
	
	
	
}
