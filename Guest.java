package com.assignment2;

public class Guest {

	private Integer guestID;
	private String name;
	public Integer getGuestID() {
		return guestID;
	}
	public void setGuestID(Integer guestID) {
		this.guestID = guestID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Guest(){}
	public Guest(Integer id, String name) {
		this.guestID = id;
		this.name = name;
	}
	
}
