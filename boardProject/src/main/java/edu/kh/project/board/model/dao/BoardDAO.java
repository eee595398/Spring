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
	
	 
	/**게시판 종류 목록 조회
	 * @return
	 */
	public List<Map<String, Object>> selectBoardTypeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}
	

	/** 특정 게시판의 삭제되지 않은 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode) {
		
		return sqlSession.selectOne("boardMapper.getListCount",boardCode);
		
	}
	
	
	
	/** 특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
	 * @param pagination
	 * @param boardCode
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, int boardCode){
		
	
	// 특정 게시판의 목록 조회 
	// 1. 최신 순서 
	// 2. 1page (1~10행) 조회 
	// 3. 삭제된 글 제외 
	
	
	// RowBounds 객체 
	// - 마이바티스에서 페이징처리를 위해 제공하는 객체 
	// - offset 만큼 건너뛰고 
	// 그 다음 지정된 행 객수만큼 조회 
	
	// 1) offset 계산
	int offset=(pagination.getCurrentPage()-1)*pagination.getLimit();
	
	// 2) RowBounds 객체 생성 
	// 20행 만큼 건너뛴 후 다음 10개의 행 조회 이런식으로 쓰는게 RowBounds
	RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
	
	// 3) slelectList("namespace.id", 파라미터,RowBounds) 호출
	return sqlSession.selectList("boardMapper.selectBoardList",boardCode,rowBounds);
	
	}
	

}
