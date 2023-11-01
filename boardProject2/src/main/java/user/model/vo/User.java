package user.model.vo;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;

import lombok.ToString;

@Getter

@Setter

@NoArgsConstructor

@ToString

// 데이터 전달을 위해 참조변수를 선언함
public class User {

private int userNo;

private String userId;

private String userName;

private int userAge;

}