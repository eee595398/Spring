package edu.kh.project.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;

	
	@Transactional
	@Override
	public int updateInfo(Member updateMember) {
		// TODO Auto-generated method stub
		return dao.updateInfo(updateMember);
	}
	
	

}
