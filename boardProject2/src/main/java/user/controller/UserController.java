package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import user.model.service.UserService;
import user.model.vo.User;

@Controller

public class UserController {

@Autowired

private UserService service;

@GetMapping("/selectUser")

// 받은 selectuser 내용을 처리

public String selectUsser(String userId, Model model) {
	
	// 데이터를 전달하기 위해 Model 객체를 사용함
	// input 이름과 User에 선언된 매개변수 userId는 이름이 같기에 자동매핑이 됨
	List<User> userList = service.selectUser(userId);
	// 유저아이디를 이용해 해당하는 유저의 정보를 찾고 유저리스트에 담음 

	
	
	if (!userList.isEmpty()) {
		// 유저리스트가 비어있지 않다면 
	
	model.addAttribute("userList", userList);
	
	// "userList" key와 userList 값을 addAttribute를 통해 model 객체 싣고 model이 데이터를 보낸다
	
	return "searchSuccess";
	
	// searchSuccess jsp로 보냄
	
	} else {
	
	return "searchFail";
	
	// 없다면 searchFail로 보냄 

}

}

}