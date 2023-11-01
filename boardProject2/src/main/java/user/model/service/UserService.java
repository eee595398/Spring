package user.model.service;

import java.util.List;

import user.model.vo.User;



public interface UserService {
// 상속 받은 UserServiceImpl에서 처리한다
List<User> selectUser(String userId);

}