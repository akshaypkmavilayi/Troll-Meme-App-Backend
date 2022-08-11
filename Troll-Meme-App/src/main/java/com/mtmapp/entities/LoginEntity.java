package com.mtmapp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_login_table")
public class LoginEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;
	private String emailId;
	private String password;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid",referencedColumnName="userid")
	private UserEntity user;
	
	public int getLogId() { 
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "LoginEntity [logId=" + logId + ", emailId=" + emailId + ", password=" + password + ", user=" + user
				+ "]";
	}
	
	
	
	
}
