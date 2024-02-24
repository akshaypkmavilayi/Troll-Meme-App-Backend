package com.memeapp.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class LoginEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;

	private String username;

	private String password;

	private Boolean enabled;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid",referencedColumnName="userid")
	private UserEntity user;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "logId"),inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Authorities> roles;
	
	public int getLogId() { 
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Authorities> getRoles() {
		return roles;
	}

	public void setRoles(Set<Authorities> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "LoginEntity [logId=" + logId + ", emailId=" + username + ", password=" + password + ", user=" + user
				+ "]";
	}
	
	
	
	
}
