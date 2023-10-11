package edu.kh.project.member.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.EmailDAO;

@Service
public interface EmailService {

	int signUp(String email, String string);



	int checkAuthKey(Map<String, Object> paramMap);
	
}