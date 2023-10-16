package edu.kh.project.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.dao.BoardDAO;






@Service
public class BoardServiceImpl implements BoardService {

@Autowired
private BoardDAO dao;


@Override
public List<Map<String, Object>> selectBoardTypeList() {
return dao.selectBoardTypeList();
}

}