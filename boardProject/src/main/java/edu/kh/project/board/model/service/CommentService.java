package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.dto.Comment;

public interface CommentService {

	List<Comment> select(int boardNo);




	
	int addComment(Comment comment);









	int delete(int commentNo);


}
