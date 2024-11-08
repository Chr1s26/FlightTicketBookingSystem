package DTO;

import Model.UserPolicy;

public class User {
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private UserPolicy userPolicy;
	
	public User(String username,String password,String confirmPassword,String email,UserPolicy userPolicy) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.userPolicy = userPolicy;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserPolicy getUserPolicy() {
		return userPolicy;
	}
	public void setUserPolicy(UserPolicy userPolicy) {
		this.userPolicy = userPolicy;
	}
	
	
}
