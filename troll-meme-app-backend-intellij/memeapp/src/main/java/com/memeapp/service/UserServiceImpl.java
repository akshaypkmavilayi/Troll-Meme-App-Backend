package com.memeapp.service;

import java.util.*;

import com.memeapp.dao.LoginDao;
import com.memeapp.dao.UserDetailsDao;
import com.memeapp.entities.Authorities;
import com.memeapp.entities.LoginEntity;
import com.memeapp.entities.UserEntity;
import com.memeapp.repository.LoginRepository;
import com.memeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LoginRepository logRepo;


	@Override
	public UserEntity addUser(UserDetailsDao userDetailsDao) {
		UserEntity userEntity = new UserEntity();
		LoginEntity loginEntity = new LoginEntity();
		Authorities authorities = new Authorities();
		authorities.setAuthority(userDetailsDao.getRoles());
		Set<Authorities> authoritiesSet = new HashSet<>();
		authoritiesSet.add(authorities);

		userEntity.setUserName(userDetailsDao.getUsername());
		userEntity.setGender(userDetailsDao.getGender());
		loginEntity.setRoles(authoritiesSet);
		loginEntity.setEnabled(userDetailsDao.getEnabled());
		loginEntity.setUsername(userDetailsDao.getUsername());
		loginEntity.setPassword(userDetailsDao.getPassword());
		loginEntity.setUser(userEntity);

		userRepo.save(userEntity);
		logRepo.save(loginEntity);
		return userEntity;
	}
	@Override
	public List<LoginEntity> getAllUsers() {

		return new ArrayList<>(logRepo.findAll());
	}
	@Override
	public LoginEntity findUserById(int id) {
		Optional<LoginEntity> user = logRepo.findById(id);
        return user.orElse(null);
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
	public String validateLogin(LoginDao loginDao) {
		LoginEntity login = new LoginEntity();
		login.setUsername(loginDao.getUsername());
		login.setPassword(loginDao.getPassword());

		loadUserByUsername(loginDao.getUsername());

		LoginEntity existingUser = logRepo.findByUsername(login.getUsername());

	 return "login data received";
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginEntity loginEntity = logRepo.findByUsername(username);
		/**
		 * CustomUserDetails.java is the custom implementation of UserDetails interface which contains user related information */
		CustomUserDetails customUserDetails = null;
		if(loginEntity != null){
			customUserDetails = new CustomUserDetails();
			customUserDetails.setLoginEntity(loginEntity);
		}else {
			throw new UsernameNotFoundException("User with username " + username + " not found");
		}
		return customUserDetails;
	}
}
