package com.assignment2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HotelBookingService {

	static Integer guestId = 0;
	static Map<Integer, Guest> guestMap = new HashMap<Integer, Guest>();
	static List<Booking> bookingList = new ArrayList<Booking>();
	static Map<Integer, Room> roomMap = new HashMap<Integer,Room>();
	
	public static void main(String[] args) {
		System.out.println("-----------------------------------------------"); 
		System.out.println("------ Welcome to FedUni Hotel Bookings ------- ");
		System.out.println("-----------------------------------------------");
		
//		int i = dateToDayNumber(12,1);
//		System.out.println("i="+i);
//		Booking booking = new Booking(1,"test1", 1, 101, 101, 213);
		preCreateTestData();
		handleMainMenu();
		
	}

	public static void printMainMenu() {
		System.out.println("Main Menu - please select an option:");
		System.out.println("1.) Add guest ");
		System.out.println("2.) Add room ");
		System.out.println("3.) Add booking ");
		System.out.println("4.) View bookings ");
		System.out.println("5.) Quit");
	}
	
	/**
	 * hanle main menu with user input
	 */
	public static void handleMainMenu() {
		Integer sel=5;
		do
		{
			printMainMenu();
			Scanner inputMain = new Scanner(System.in); 
			sel = inputMain.nextInt();
			inputMain.nextLine();
			switch (sel)
			{
				case 1: handleAddGuest();break;
				case 2: handleAddRoom(); break;
				case 3: handleAddBooking(); break;
				case 4: handleViewBookings(); break;
				case 5: handleExitSystem();break; 
				default: System.out.println("Value must be between 1 and 5. Please try again:");
			}
		} while (sel!=5);

		//inputMain.close();
	}
	
	public static void handleExitSystem() {
		System.out.println("Thanks for using FedUni Hotel Bookings!");
	}
	
	public static void handleAddGuest() {
		String sel = "A";
		Scanner inputAddGuest = new Scanner(System.in);
		do
		{
			System.out.println("Please enter guest name:");
			String name = inputAddGuest.nextLine().trim();
			guestId++;
			Guest guest = new Guest(guestId, name);
			guestMap.put(guestId, guest);
			System.out.println("Guest "+name+" has been created with guest ID: "+guest.getGuestID());
			sel = "";
			while (!sel.equals("R") && !sel.equals("A")) {
				System.out.println("Would you like to [A]dd a new guest or [R]eturn to the previous menu?");
				sel = inputAddGuest.nextLine().trim();
			}
		}while(sel.equals("A"));
	}
	
	public static void handleAddRoom()
	{
		String sel = "A";
		Scanner inputAddRoom = new Scanner(System.in);
		do {
			System.out.println("Please enter room number:");
			Integer roomNo = null;
			while (true) {
				roomNo = inputAddRoom.nextInt();
				inputAddRoom.nextLine();
				if (!roomMap.containsKey(roomNo))
					break;
				System.out.println("Room already exists. Please enter room number:");
			}
			
			System.out.println("Please enter room capacity:");
			Integer capacity = inputAddRoom.nextInt();
			inputAddRoom.nextLine();
			Room room = new Room(roomNo, capacity);
			roomMap.put(roomNo, room);
			sel = "";
			while (!sel.equals("R") && !sel.equals("A")) {
				System.out.println("Would you like to [A]dd a new room or [R]eturn to the previous menu?");
				sel = inputAddRoom.nextLine().trim();
			}
		}while (sel.equals("A"));
	}
	
	public static void handleAddBooking() {
		Scanner inputAddBooking = new Scanner(System.in);
		//input guestid 
		Integer inputGuestID = null;
		Guest inputGuest = null;
		do {
			System.out.println("Please enter guest ID:");
			inputGuestID = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			if (inputGuestID>guestId || !guestMap.containsKey(inputGuestID)) {
				System.out.println("Guest does not exist.");
			}
			else
			{
				inputGuest = guestMap.get(inputGuestID);
				break;
			}
		} while (inputGuestID>guestId);
		
		//select room  
		Integer roomNum = null;
		Room selectedRoom = null;
		Integer guestsNum = null;
		Boolean roomSelected = false;
		while (!roomSelected) {
			System.out.println("Please enter room number:");
			roomNum = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			if (!roomMap.containsKey(roomNum)) {
				System.out.println("Room does not exist.");
				continue;
			}
			selectedRoom = roomMap.get(roomNum);
			System.out.println("Please enter number of guests:");
			guestsNum = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			if (selectedRoom.getCapacity() < guestsNum) {
				System.out.println("Guest count exceeds room capacity of: " + selectedRoom.getCapacity());
				selectedRoom = null;
			}
			else
			{
				roomSelected = true;
			}
		} 
		
		// input checkin date and checkout date
		
		Integer ciMonth = 0;
		Integer coMonth = 0;
		Integer ciDay = 0;
		Integer coDay = 0;
		System.out.println("Please enter check-in month:");
		while (ciMonth>12 || ciMonth<1) {
			ciMonth = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			if (ciMonth>12 || ciMonth<1) {
				System.out.println("Invalid month. Please enter check-in month:");
			}
		}
		
		System.out.println("Please enter check-in day:");
		while (!isValidDayForMonth(ciDay, ciMonth)) {
			ciDay = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			if (!isValidDayForMonth(ciDay, ciMonth)) {
				System.out.println("Invalid day. Please enter check-in day:");
			}
		}
		int startDay = dateToDayNumber(ciMonth, ciDay);
		
		System.out.println("Please enter check-out month:");
		while (coMonth>12 || coMonth<1 || coMonth<ciMonth) {
			coMonth = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			if (coMonth>12 || coMonth<1 || coMonth<ciMonth) {
				System.out.println("Invalid month. Please enter check-out month:");
			}
		}
		
		System.out.println("Please enter check-out day:");
		while (!isValidDayForMonth(coDay, coMonth)) {
			coDay = inputAddBooking.nextInt();
			inputAddBooking.nextLine();
			int tempEndDay = dateToDayNumber(coMonth, coDay);
			if (!isValidDayForMonth(coDay, coMonth) || tempEndDay<startDay) {
				System.out.println("Invalid day. Please enter check-out day:");
			}
		}
		
		int endDay = dateToDayNumber(coMonth, coDay);
		
		//check if the selected room is available from  input ci date to co date
		Boolean isRoomAvailable = selectedRoom.isAvailable(startDay, endDay);
		
		if (!isRoomAvailable)
		{
			System.out.println("Room is not available during that period. Please enter new room number:");
			roomSelected = false;
			selectedRoom = null;
			int loopTimes = 0;
			while (!roomSelected) {
				roomNum = inputAddBooking.nextInt();
				inputAddBooking.nextLine();
				if (!roomMap.containsKey(roomNum)) {
					System.out.println("Room does not exist.");
					System.out.println("Please enter room number:");
					continue;
				}
				selectedRoom = roomMap.get(roomNum);
				if (selectedRoom.getCapacity() < guestsNum) {
					System.out.println("Guest count exceeds room capacity of: " + selectedRoom.getCapacity());
					System.out.println("Please enter room number:");
					selectedRoom = null;
				}
				else if (!selectedRoom.isAvailable(startDay, endDay)) {
					System.out.println("Room is not available during that period. Please enter new room number:");
					selectedRoom = null;
				}
				else
				{
					roomSelected = true;
				}
				loopTimes++;
				if (loopTimes>3 && !roomSelected)  // in case of endless loop ,will loop only 3 times
				{
					System.out.println("*** maybe no suitable room for booking, will return to main menu ***");
					return;
				}
			}
		}
		
		
		Booking booking = new Booking(inputGuestID, inputGuest.getName(), guestsNum, selectedRoom.getRoomNum(), startDay, endDay);
		bookingList.add(booking);
		selectedRoom.setBooked(startDay, endDay);
		System.out.println("*** Booking successful! ***");
	}
	
	public static void handleViewBookings() {
		String sel = "";
		Scanner inputViewBookings = new Scanner(System.in);
		do
		{
			System.out.println("Would you like to view [G]uest bookings, [R]oom booking, or e[X]it?");
			sel = inputViewBookings.nextLine().trim();
			if (sel.equals("G")) {
				viewGuestBookings();
			}
			else if (sel.equals("R")) {
				viewRoomBookings();
			}
			else if (!sel.equals("X")) {
				System.out.println("Invalid input!");
			}
		} while(!sel.equals("X"));
	}
	
	public static void viewRoomBookings() {
		Scanner viewRoomBookings = new Scanner(System.in);
		while(true)
		{
			System.out.println("Please enter room number:");
			Integer roomNum = viewRoomBookings.nextInt();
			viewRoomBookings.nextLine();
			if (roomMap.containsKey(roomNum)) {
				System.out.println("Room "+roomNum+" bookings:");
				for(Booking booking:bookingList) {
					if (booking.getRoomNumber().equals(roomNum))
					{
						System.out.println(booking.print(true));
					}
				}
				break;
			}
			else {
				System.out.print("Room does not exist.");
			}
		} 
	}
	
	public static void viewGuestBookings() {
		Scanner viewGuestBookings = new Scanner(System.in);
		while(true)
		{
			System.out.println("Please enter guest ID:");
			Integer inputGuest = viewGuestBookings.nextInt();
			viewGuestBookings.nextLine();
			if (inputGuest<=guestId && guestMap.containsKey(inputGuest)) {
				System.out.println("Guest "+inputGuest+":"+guestMap.get(inputGuest).getName());
				for(Booking booking:bookingList) {
					if (booking.getGuestID().equals(inputGuest))
					{
						System.out.println(booking.print(false));
					}
				}
				break;
			}
			else {
				System.out.print("Guest does not exist. ");
			}
		} 
	}
	
	public static boolean isValidDayForMonth(int day, int month) {
		if (day<1) 
			return false;
		Integer maxDay = 30;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			maxDay = 31; break; 
		case 2:
			maxDay = 28; break;
		case 4:
		case 6:
		case 9:
		case 11:
			maxDay = 30; break;
		}
		if (day>maxDay) 
			return false;
		else
			return true;
	}
	
	public static Integer dateToDayNumber(int month, int day) {
		if (month<1 || month >12  || day < 1 || day > 31) 
			return 0;
		Calendar ca = Calendar.getInstance();
		ca.set(2017, month-1, day);
		return ca.get(Calendar.DAY_OF_YEAR);
	}
	
	public static Boolean isRoomAvailable(Room selectedRoom, int startDay, int endDay) {
		
		if (selectedRoom==null) 
			return false;
		if (startDay<1 || endDay>365 || startDay>endDay)
			return false;
		Boolean isRoomAvailable = true;
		if (endDay==startDay)
			endDay = startDay+1;
		for(int i = startDay; i<endDay; i++) {
			if (selectedRoom.getBooklist().get(i-1)) {
				isRoomAvailable = false;
				break;
			}
		}
		return isRoomAvailable;
	}
	
	public static void preCreateTestData() {
		guestId++;
		Guest guest1 = new Guest(guestId, "pxafd");
		guestId++;
		Guest guest2 = new Guest(guestId, "wvaddaf");
		guestMap.put(guest1.getGuestID(), guest1);
		guestMap.put(guest2.getGuestID(), guest2);
		
		Room room1 = new Room(101, 2);
		Room room2 = new Room(201, 4);
		roomMap.put(room1.getRoomNum(), room1);
		roomMap.put(room2.getRoomNum(), room2);
	}
}
