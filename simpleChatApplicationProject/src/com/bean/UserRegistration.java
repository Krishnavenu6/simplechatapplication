package com.bean;

public class UserRegistration {
	public UserRegistration(String username, String password, long mobileno, int userid, String email) {
		super();
		this.username = username;
		this.password = password;
		this.mobileno = mobileno;
		this.userid = userid;
		this.email = email;
	}
	public UserRegistration() {
		
	}
	private String username;
	private String password;
	private long mobileno;
	private int userid;
	private String email;
	
	
	
	/* 
	 ==============================
	 	Getter method
	 ==============================
	 */
	
	
	public String getUsername() {
		return username;
	}
	

	/* 
	 ==============================
	 	Setter method
	 ==============================
	 */
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}