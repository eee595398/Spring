	
package user.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import user.model.dao.UserDAO;
import user.model.vo.User;


@Service

public class UserServiceImpl implements UserService {

@Autowired

private UserDAO dao;

@Override

public List<User> selectUser(String userId) {

	// dao로 보냄
return dao.selectUser(userId);

}

}