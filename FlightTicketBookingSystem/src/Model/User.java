package Model;

import java.time.LocalDateTime;

public class User {
	
	private int id;
	private String name;
	private String passwordHash;
	private String email;
	private UserPolicy policy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String LoginToken;
	

	public User() {
		
	}
	
	public User(int id, String name, String passwordHash, String email, UserPolicy policy) {
		this.id = id;
		this.name = name;
		this.passwordHash = passwordHash;
		this.email = email;
		this.policy = policy;
	}
	
	public User(int id, String name, String passwordHash, String email, UserPolicy policy, LocalDateTime createdAt,LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.passwordHash = passwordHash;
		this.email = email;
		this.policy = policy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public User(String name, String passwordHash, String email, UserPolicy policy) {
		this.name = name;
		this.passwordHash = passwordHash;
		this.email = email;
		this.policy = policy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(UserPolicy policy) {
		this.policy = policy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getLoginToken() {
		return LoginToken;
	}

	public void setLoginToken(String loginToken) {
		LoginToken = loginToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", passwordHash=" + passwordHash + ", email=" + email + ", policy="
				+ policy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
