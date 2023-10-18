package edu.kh.project.myPage;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService{
	
	int updateInfo(Member updateMember);

	int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws Exception;

}
