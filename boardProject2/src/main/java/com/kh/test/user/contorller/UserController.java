package com.kh.test.user.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.test.user.model.service.UserService;
import com.kh.test.user.model.vo.User;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/check")
	public String check(String userNo, Model model) {
		
	List<User> userList = service.check(userNo);
		
	
	  if(!userList.isEmpty()) {
		  
		  model.addAttribute("userList", userList);
		  return "searchSuccess";
		  
	  }else {
		  
		  return "searchFail";
	  }
	}
	
}
