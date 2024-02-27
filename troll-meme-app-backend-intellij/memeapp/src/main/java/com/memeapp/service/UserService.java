package com.memeapp.service;

import java.util.List;

import com.memeapp.dao.LoginDao;
import com.memeapp.dao.UserDetailsDao;
import com.memeapp.entities.LoginEntity;
import com.memeapp.entities.UserEntity;

public interface UserService {
	public UserEntity addUser(UserDetailsDao userDetailsDao);
	List<LoginEntity> getAllUsers();
	LoginEntity findUserById(int id);
	void deleteUserById(int id);
//	UserEntity updateUser(UserAndLogin userAndLogin);
//	
	public String validateLogin(LoginDao login);

}
