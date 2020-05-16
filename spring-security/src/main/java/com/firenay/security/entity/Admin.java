package com.firenay.security.entity;

/**
 * <p>Title: Admin</p>
 * Description：
 * date：2020/5/14 16:44
 */
public class Admin {
	private Integer id;

	private String loginacct;

	private String username;

	private String userpswd;

	private String email;

	public Admin(Integer id, String loginacct, String username, String userpswd, String email) {
		this.id = id;
		this.loginacct = loginacct;
		this.username = username;
		this.userpswd = userpswd;
		this.email = email;
	}

	public Admin() {}

	@Override
	public String toString() {
		return "Admin{" +
				"id=" + id +
				", loginacct='" + loginacct + '\'' +
				", username='" + username + '\'' +
				", userpswd='" + userpswd + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpswd() {
		return userpswd;
	}

	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
