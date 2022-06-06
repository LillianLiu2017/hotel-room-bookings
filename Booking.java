package com.assignment2;

import java.util.Calendar;
import java.util.List;

public class Booking {

	private Integer guestID;
	private String guestName;
	private Integer guestNum;
	private Integer roomNumber;
	private Integer ciDate;
	private Integer coDate;
	
	private Integer ciMonth;
	private Integer ciDay;
	private Integer coMonth;
	private Integer coDay;
	
	public Booking() {}
	public Booking(Integer guestID, String guestName, Integer guestNum, Integer roomNumber,
			Integer ciDate, Integer coDate) {
		super();
		this.guestID = guestID;
		this.setGuestName(guestName);
		this.guestNum = guestNum;
		this.roomNumber = roomNumber;
		this.ciDate = ciDate;
		this.coDate = coDate;
		
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DAY_OF_YEAR, ciDate);
		ciMonth = a.get(Calendar.MONTH)+1;
		ciDay = a.get(Calendar.DATE);
		a.set(Calendar.DAY_OF_YEAR, coDate);
		coMonth = a.get(Calendar.MONTH)+1;
		coDay = a.get(Calendar.DATE);
	}
	public Integer getGuestID() {
		return guestID;
	}
	public void setGuestID(Integer guestID) {
		this.guestID = guestID;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getGuestNum() {
		return guestNum;
	}
	public void setGuestNum(Integer guestNum) {
		this.guestNum = guestNum;
	}
	public Integer getCiDate() {
		return ciDate;
	}
	public void setCiDate(Integer ciDate) {
		this.ciDate = ciDate;
	}
	public Integer getCoDate() {
		return coDate;
	}
	public void setCoDate(Integer coDate) {
		this.coDate = coDate;
	}
	
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	

	public String print(Boolean isByRoom) {
		String ret = "";
		if (isByRoom) {
			ret = "Guest "+this.getGuestID()+" – "+this.getGuestName();
		}
		else {
			ret = "Room "+this.getRoomNumber();
		}
		ret = ret + ", " + this.guestNum + " guest(s) from ";
		ret = ret + print2Nums(ciMonth) + "/";
		ret = ret + print2Nums(ciDay) + " to ";
		ret = ret + print2Nums(coMonth) + "/";
		ret = ret + print2Nums(coDay) + ".";
		return ret;
	}
	
	public String print2Nums(Integer num) {
		if (num>=0 && num<10) {
			return "0"+num;
		}
		return num.toString();
	}
	
}
