package com.kh.test.user.model.service;

import java.util.List;

import com.kh.test.user.model.vo.User;

public interface UserService {

	List<User> check(String userNo);

}
