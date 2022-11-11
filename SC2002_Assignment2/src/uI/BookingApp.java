package uI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import database.Data;
import manager.BookingMgr;
import manager.CinemaMgr;
import manager.ShowStatusMgr;
import manager.UserAccountMgr;
import utils.DateUtils;
import utils.PriceCalculator;
import utils.SearchUtils;
import utils.StringToSeat;
import utils.TimeUtils;
import model.*;

public class BookingApp {
	public static void AppMain(Scanner sc) {
		ArrayList<ShowStatus> showStatusList = Printer.displaytMovieShowTime(AppState.getMovieID());
		if(showStatusList.size() == 0) {
			return;
		}
		
		int index;
		while(true) {
			System.out.print("Enter Booking ID (or enter -1 to exit): ");
			index = sc.nextInt();
			sc.nextLine();
			if(index<0) {
				return;
			}
			if(index >= showStatusList.size()) {
				System.out.println("Invalid ID, Please Try Again");
			}else {
				break;
			}
		}

		ShowStatus buffer = showStatusList.get(index);
		Cinema cinema = CinemaMgr.getCinemaByID(buffer.getCinemaID());
		UserAccount user = UserAccountMgr.getUserAccount(AppState.getUserID());
		
		StringToSeat.printSeat(buffer.getseatStatus());
		
		int i=0,j=0;
		String seatID ="";
		while(true) {
			System.out.print("Enter SeatID (A0): ");
			 seatID = sc.nextLine();
			String alpha=  "ABCDEFGHIJKLMNOP";
			for(int y=0;y<alpha.length();y++) {
				if(seatID.charAt(0) == alpha.charAt(y)) {
					j=y;
					break;
				}
			}
			i = Character.getNumericValue(seatID.charAt(1));
			if(i>= buffer.getseatStatus().length || j>=buffer.getseatStatus()[0].length) {
				System.out.print("Invalid Seat, Please try again\n");
				continue;
			}
			boolean pass = ShowStatusMgr.updateSeat(buffer.getShowStatusID(), i,j);
			if(pass) {
				break;
			}else {
				System.out.print("Invalid Seat, Please try again\n");
			}
		}
		
		double price = PriceCalculator.calculate(cinema.getCinemaType(), buffer.getseatStatus()[j][i], user.getAge(), 
				buffer.getShowDate(),buffer.getMovieType());
		
		LocalDate bookDate = LocalDate.now();
		LocalTime bookTime = LocalTime.now();
		int newID = BookingMgr.createBooking(AppState.getUserID(), buffer.getShowStatusID(), price, bookDate, bookTime);
		
		Booking booking = BookingMgr.getBookingCopy(newID);
		System.out.println("\nTicket details:\n");
		System.out.println("Name: "+user.getUsername());
		System.out.println("Mobile Number: "+user.getMobileNumber());
		System.out.println("Email: "+user.getEmail());
		System.out.println("TID: "+booking.getTID());
		System.out.println("Ticket Price: "+booking.getPrice());
		System.out.println("SeatID: "+seatID);
		System.out.println("MovieType: "+buffer.getMovieType());
		
	}
	
	
}