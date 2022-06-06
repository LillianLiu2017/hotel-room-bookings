package com.assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {

	private Integer roomNum;
	private Integer capacity;
	private List<Boolean> booklist;
	
	public Room() {}
	public Room(Integer roomNum, Integer capacity) {
		this.roomNum = roomNum;
		this.capacity = capacity;
		booklist = new ArrayList<Boolean>(Collections.nCopies(365, false));
	}
	
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public List<Boolean> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<Boolean> booklist) {
		this.booklist = booklist;
	}
	
	public boolean setBooked(int startDayNumber, int endDayNumber) {
		if (startDayNumber>endDayNumber)
			return false;
		if (startDayNumber==endDayNumber)
			endDayNumber = startDayNumber+1;
		for (int i=startDayNumber; i<endDayNumber; i++) {
			this.booklist.set(i-1, true);
		}
		return true;
	}
	
	public boolean isAvailable(int startDay, int endDay) {

		if (startDay<1 || endDay>365 || startDay>endDay)
			return false;
		Boolean isRoomAvailable = true;
		if (endDay==startDay)
			endDay = startDay+1;
		for(int i = startDay; i<endDay; i++) {
			if (this.getBooklist().get(i-1)) {
				isRoomAvailable = false;
				break;
			}
		}
		return isRoomAvailable;
	}
	
}
