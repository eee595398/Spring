package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.CommentDAO;
import edu.kh.project.board.model.dto.Comment;



@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO dao;
	
	@Override
	public List<Comment> select(int boardNo) {
		// TODO Auto-generated method stub
		return dao.select(boardNo);
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		return dao.addComment(comment);
	}










}
