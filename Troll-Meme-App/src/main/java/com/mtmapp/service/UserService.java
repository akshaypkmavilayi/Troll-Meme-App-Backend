package com.mtmapp.service;

import java.util.List;

import com.mtmapp.entities.LoginEntity;
import com.mtmapp.entities.UserAndLogin;
import com.mtmapp.entities.UserEntity;

public interface UserService {
	public UserEntity addUser(UserAndLogin userAndLogin);
	List<LoginEntity> getAllUsers();
	LoginEntity findUserById(int id);
	void deleteUserById(int id);
//	UserEntity updateUser(UserAndLogin userAndLogin);
//	
	public LoginEntity validateLogin(LoginEntity login);

}
