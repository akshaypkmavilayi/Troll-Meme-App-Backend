package com.memeapp.service;

import java.util.List;
import java.util.Optional;

import com.memeapp.entities.LoginEntity;
import com.memeapp.entities.UserAndLogin;
import com.memeapp.entities.UserEntity;
import com.memeapp.repository.LoginRepository;
import com.memeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LoginRepository logRepo;
	@Override
	public UserEntity addUser(UserAndLogin userAndLogin) {
		
		UserEntity user = userAndLogin.getUser();
		userRepo.save(user);
		LoginEntity login = userAndLogin.getLogin();
		login.setUser(user);
		logRepo.save(login);
		return user;
	}
	@Override
	public List<LoginEntity> getAllUsers() {
		List<LoginEntity> userList = logRepo.findAll();
		return userList;
	}
	@Override
	public LoginEntity findUserById(int id) {
		Optional<LoginEntity> user = logRepo.findById(id);
		return user.get();
	}
	@Override
	public void deleteUserById(int id) {
		logRepo.deleteById(id);
		
	}
//	@Override
//	public UserEntity updateUser(UserAndLogin userAndLogin) {
//		UserEntity user = userAndLogin.getUser();
//		userRepo.save(user);
//		LoginEntity login = userAndLogin.getLogin();
////		login.setUser(user);
//		logRepo.save(login);
//		return user;
//	}
	@Override
	public LoginEntity validateLogin(LoginEntity login) {
		
		LoginEntity existingUser = logRepo.findByEmailAndPassword(login.getEmailId(), login.getPassword());
		if(existingUser != null) {
			return existingUser;
		}else {
			return null;
		}
		
	 
	}
	
	

}
