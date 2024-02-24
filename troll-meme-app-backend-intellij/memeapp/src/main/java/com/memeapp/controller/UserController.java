package com.memeapp.controller;

import java.util.List;

import com.memeapp.dao.UserDetailsDao;
import com.memeapp.entities.LoginEntity;
import com.memeapp.entities.UserAndLogin;
import com.memeapp.entities.UserEntity;
import com.memeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostMapping("/register")
	public ResponseEntity addUser(@RequestBody UserDetailsDao userDetailsDao) {

		String encryptedPassword = passwordEncoder.encode(userDetailsDao.getPassword());
		userDetailsDao.setPassword(encryptedPassword);

		userService.addUser(userDetailsDao);
		return new ResponseEntity(HttpStatus.OK);
	}


	@PutMapping("/update-user")
	public UserEntity updateUser(@RequestParam("userid") int userid,@RequestParam("loginid") int loginid,@RequestParam("name") String userName
			,@RequestParam("gender") String userGender,
			@RequestParam("email") String userEmail,
			@RequestParam("password") String userPassword) {
		LoginEntity login = new LoginEntity();
		System.out.println(login);
		UserEntity user= new UserEntity();
		
		UserAndLogin userAndLogin = new UserAndLogin(); 
		user.setUserId(userid);
		user.setUserName(userName);
		user.setGender(userGender);
		login.setLogId(loginid);
		login.setPassword(userPassword);
		userAndLogin.setUser(user);
		userAndLogin.setLogin(login);
		
		return null;
	}
	
	
	
	@GetMapping("/view-all-user")
	public List<LoginEntity> viewAll(){
		List<LoginEntity> userList = userService.getAllUsers();
		return userList;
		
	}
	@GetMapping("/view-single-user/{id}")
	public LoginEntity getUserById(@PathVariable int id) {
		LoginEntity singleUser = userService.findUserById(id);
		return singleUser;
		
	}
	@DeleteMapping("/delete-user/{id}")
	public String deleteUserById(@PathVariable int id) {
		System.out.println(id);
		userService.deleteUserById(id);
		return "success..";
		
	}
	
	@PostMapping("/login")
	public LoginEntity Login(@RequestParam("email") String email,@RequestParam("password") String password){
		LoginEntity login = new LoginEntity();
		System.out.println(email+" "+password);
		login.setPassword(password);
		return userService.validateLogin(login);
		
	}

}
