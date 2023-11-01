package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	/** 게시판 종류 목록 조회
	 * @return
	 */
	public List<Map<String, Object>> selectBoardTypeList() {
		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}


	/** 특정 게시판의 삭제되지 않은 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode) {
		return sqlSession.selectOne("boardMapper.getListCount", boardCode);
	}


	/** 특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
	 * @param pagination
	 * @param boardCode
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, int boardCode) {
		
		// RowBounds 객체
		// - 마이바티스에서 페이징처리를 위해 제공하는 객체
		// - offset 만큼 건너뛰고
		// 그 다음 지정된 행 개수(limit) 만큼 조회
		
		// 1) offset 계산
		int offset 
			= (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		// 3) selectList("namespace.id", 파라미터, RowBounds) 호출
		return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
	}

	
	/** 게시글 상세 조회
	 * @param map
	 * @return board
	 */
	public Board selectBoard(Map<String, Object> map) {
		return sqlSession.selectOne("boardMapper.selectBoard", map);
	}


	/** 좋아요 여부 확인 DAO
	 * @param map
	 * @return  result
	 */
	public int boardLikeCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.boardLikeCheck", map);
	}


	/** 조회수 증가 DAO
	 * @param boardNo
	 * @return result
	 */
	public int updateReadCount(int boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardMapper.updateReadCount",boardNo);
	}


	/** 좋아요 테이블 삽입
	 * @param paramMap
	 * @return
	 */
	public int insertBoardLike(Map<String, Integer> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardMapper.insertBoardLike",paramMap);
	}


	/** 좋아요 삭제 
	 * @param paramMap
	 * @return
	 */
	public int deleteBoardLike(Map<String, Integer> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("boardMapper.deleteBoardLike",paramMap);
	}


	/** 좋아요 개수 조회
	 * @param integer
	 * @return
	 */
	public int countBoardLike(Integer boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.countBoardLike",boardNo);
	}


	/** 게시글 수 조회(검색)
	 * @param paramMap
	 * @return
	 */
	public int getListCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.getListCount_search",paramMap);
	}


	/** 게시글 목록조회(검색)
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, Map<String, Object> paramMap) {
		// RowBounds 객체
		// - 마이바티스에서 페이징처리를 위해 제공하는 객체
		// - offset 만큼 건너뛰고
		// 그 다음 지정된 행 개수(limit) 만큼 조회
		
		// 1) offset 계산
		int offset 
			= (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		System.out.println("paramMap::" + paramMap);
		
		// 3) selectList("namespace.id", 파라미터, RowBounds) 호출
		return sqlSession.selectList("boardMapper.selectBoardList_search", paramMap, rowBounds);
	}


	/** DB 이미지 파일 조회
	 * @return
	 */
	public List<String> selectImageList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectImageListAll");
	}

}
