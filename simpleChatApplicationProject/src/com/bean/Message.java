package com.bean;

public class Message {
	public Message(String sender_username, String reciever_username, String message_text) {
		super();
		this.sender_username = sender_username;
		this.reciever_username = reciever_username;
		this.message_text = message_text;                 
	}

	private String sender_username;
	private String reciever_username;
	private String message_text;

	
	/* 
	 ==============================
	 	Getter method
	 ==============================
	 */
	public String getSender_username() {
		return sender_username;
	}
	
	/* 
	 ==============================
	 	Setter method
	 ==============================
	 */
	

	public void setSender_username(String sender_username) {
		this.sender_username = sender_username;
	}

	public String getReciever_username() {
		return reciever_username;
	}

	public void setReciever_username(String reciever_username) {
		this.reciever_username = reciever_username;
	}

	public String getMessage_text() {
		return message_text;
	}

	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

}




