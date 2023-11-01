package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

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


	
	/** 게시글 수정 
	 * @param board
	 * @return rowCount
	 */
	public int boardUpdate(Board board) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.boardUpdate",board);
	}



	/** 이미지 삭제 
	 * @param deleteMap
	 * @return rowCount
	 */
	public int imageDelete(Map<String, Object> deleteMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("boardMapper.imageDelete", deleteMap);
	}



	/** 이미지 수정
	 * @param img
	 * @return
	 */
	public int imageUpdate(BoardImage img) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.imageUpdate",img);
	}



	/** 이미지 삽입
	 * @param img
	 * @return rowCount
	 */
	public int imageInsert(BoardImage img) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.imageInsert",img);
	}



	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	public int boardDelete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.boardDelete",map);
	}

	
	
	
	
	
	
	
}
