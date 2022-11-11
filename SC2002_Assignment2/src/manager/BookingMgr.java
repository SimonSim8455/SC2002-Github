package manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.HashMap;
import model.*;

import database.Data;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.TimeUtils;
import utils.Validator;

public class BookingMgr {
	private static HashMap<Integer, Booking> bookingList = Data.bookingList;
	
	
	public static int createBooking(int userID, int showStatusID, double price,
    		DateUtils bookingDate,TimeUtils bookingTime) {
		if(Validator.validateUser(userID) != 1 || Validator.validateShowStatus(showStatusID) != 1) {
			return -1;
		}

		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		
		int bookingID = Helper.getUniqueId(bookingList);
		
		Booking newBooking = new Booking(bookingID,userID, buffer.getMovieID(), buffer.getCineplexID(), buffer.getCinemaID(), 
			buffer.getMovieType(), price, bookingDate,bookingTime,buffer.getShowDate(),buffer.getShowTime());
		
		bookingList.put(bookingID, newBooking);
		MovieRankMgr.addSales(buffer.getMovieID(),price);
		return bookingID;
		
	}
	
	public static int createBooking(int userID, int showStatusID, double price,
    		LocalDate date,LocalTime time) {
		if(Validator.validateUser(userID) != 1 || Validator.validateShowStatus(showStatusID) != 1) {
			return -1;
		}
		
		DateUtils bookingDate = DateUtils.LocalDateToDateUtils(date);
		TimeUtils bookingTime = TimeUtils.LocalTimeToTimeUtils(time);
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		
		int bookingID = Helper.getUniqueId(bookingList);
		
		Booking newBooking = new Booking(bookingID,userID, buffer.getMovieID(), buffer.getCineplexID(), buffer.getCinemaID(), 
			buffer.getMovieType(), price, bookingDate,bookingTime,buffer.getShowDate(),buffer.getShowTime());
		
		bookingList.put(bookingID, newBooking);
		MovieRankMgr.addSales(buffer.getMovieID(),price);
		return bookingID;
		
	}
	
	public static Booking getBookingCopy(int bookingID) {
		if(Validator.validateBooking(bookingID) != 1) {
			return null;
		}
		Booking buffer = SearchUtils.searchBooking(bookingID);
		return Booking.copy(buffer);
	}
	
	public static ArrayList<Booking> getBookingListByUserID(int userID){
		if(Validator.validateUser(userID) != 1 ) {
			return null;
		}
		ArrayList<Booking> list = new ArrayList<Booking>();
		for(Booking buffer: bookingList.values()) {
			if(buffer.getUserID() == userID) {
				list.add(buffer);
			}
		}
		return list;
	}
	
	public static ArrayList<Booking> getAllBookingList(){
		ArrayList<Booking> list = new ArrayList<Booking>();
		for(Booking buffer: bookingList.values()) {
			Booking copy = Booking.copy(buffer);
			list.add(copy);
		}
		return list;
	}
}