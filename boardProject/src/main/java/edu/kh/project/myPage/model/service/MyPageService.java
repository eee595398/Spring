package edu.kh.project.myPage.model.service;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	/** 회원 정보 수정 서비스
	 * @param updateMember
	 * @return result
	 */
	int updateInfo(Member updateMember);

	int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws Exception;

}
