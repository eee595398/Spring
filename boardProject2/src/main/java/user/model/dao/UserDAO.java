package user.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import user.model.vo.User;



@Repository

public class UserDAO {

@Autowired

private SqlSessionTemplate sqlSession;

public List<User> selectUser(String userId) {

	// 유저 아이디를 가지고 userMapper에서 찾는다
return sqlSession.selectList("userMapper.selectUser", userId);

}

}