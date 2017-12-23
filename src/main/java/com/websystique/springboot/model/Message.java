package com.websystique.springboot.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Message {

	private long id;
	
	private String msg;
	private User user1;
	private User user2;
	
	@JsonFormat(pattern="YYYY-MM-DD HH:MM:SS")
	private Date sent;
	
	@JsonFormat(pattern="YYYY-MM-DD HH:MM:SS")
	private Date seen;
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

	public Date getSeen() {
		return seen;
	}

	public void setSeen(Date seen) {
		this.seen = seen;
	}

	

	public Message(){
		id=0;
	}
	
	public Message(long id,User user1,User user2, String msg,Date sent, Date seen){
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
		this.msg = msg;
		this.sent = sent;
		this.seen = seen;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}
	
	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user1=" + user1 + ", user2=" + user2 + ",sent="+ sent +",seen=" + seen + "]";
	}


}
