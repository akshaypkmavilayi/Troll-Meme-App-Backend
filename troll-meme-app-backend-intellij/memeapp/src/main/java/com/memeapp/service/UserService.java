package com.memeapp.service;

import java.util.List;

import com.memeapp.entities.LoginEntity;
import com.memeapp.entities.UserAndLogin;
import com.memeapp.entities.UserEntity;

public interface UserService {
	public UserEntity addUser(UserAndLogin userAndLogin);
	List<LoginEntity> getAllUsers();
	LoginEntity findUserById(int id);
	void deleteUserById(int id);
//	UserEntity updateUser(UserAndLogin userAndLogin);
//	
	public LoginEntity validateLogin(LoginEntity login);

}
