package model;

import java.io.Serializable;

import utils.DateUtils;
import utils.TimeUtils;

public class Booking implements Serializable {
	private int bookingID;
    private int userID;
    private String seatID;
    private double price;
    private int showStatusID;
	private DateUtils bookingDate;
    private TimeUtils bookingTime;
    private String TID;
    
    public Booking() {}
    
    public Booking(int bookingID, int userID, int showStatusID,double price,
    		DateUtils bookingDate,TimeUtils bookingTime) {
    	this.bookingID = bookingID;
    	this.userID = userID;
    	this.price = price;
    	this.bookingDate = bookingDate;
    	this.bookingTime = bookingTime;
    	this.showStatusID = showStatusID;
    	
    	String day = String.valueOf(bookingDate.getDay());
    	String year = String.valueOf(bookingDate.getYear());
    	String month = String.valueOf(bookingDate.getMonth());
    	
    	if(bookingDate.getMonth() <10) {
    		month = "0" + String.valueOf(bookingDate.getMonth());
    	}
    	
    	if(bookingDate.getDay() <10) {
    		day = "0" + String.valueOf(bookingDate.getDay());
    	}
    	
    	String hour = String.valueOf(bookingTime.getHour());
    	if(bookingTime.getHour()<10) {
    		hour = "0" + String.valueOf(bookingTime.getHour());
    	}
    	
    	String minute =String.valueOf(bookingTime.getMinute());
    	if(bookingTime.getMinute() <10) {
    		minute = "0" + String.valueOf(bookingTime.getMinute());
    	}
    	String strBookID = String.valueOf(bookingID);
    	String strUserID = String.valueOf(userID);
    	String str = strBookID+strUserID+year+month+day+hour+minute+"";
    	this.TID = new String(str);

    }
    
    public static Booking copy(Booking another) {
    	Booking booking  = new Booking(
    		another.getBookingID(),
    		another.getUserID(),
    		another.getShowStatusID(),
    		another.getPrice(),
    		another.getBookingDate(),
    		another.getBookingTime()
    	);
    	return booking;
    }

    public void setTID(String tid) {
    	this.TID = tid;
    }
    public String getTID() {
    	return this.TID;
    }
    
    public int getShowStatusID() {
    	return this.showStatusID;
    }
    public void setShowStatusID(int id) {
    	this.showStatusID = id;
    }
    public void setSeatID(String id) {
    	this.seatID = id;
    }
    public String getSeatID() {
    	return this.seatID;
    }
    public void setUserID(int id) {
    	this.userID = id;
    }
    public int getUserID() {
    	return this.userID;
    }
    public double getPrice() {
    	return this.price;
    }
    
    public void setPrice(double price) {
    	this.price = price;
    }
    
    public DateUtils getBookingDate(){
        return this.bookingDate;
    }

    public void setBookingDate(DateUtils date){
        this.bookingDate = date;
    }


    public TimeUtils getBookingTime(){
        return this.bookingTime;
    }

    public void setBookingTime(TimeUtils time){
        this.bookingTime  = time;
    }


    public int getBookingID(){
        return this.bookingID;
    }

    public void setBookingID(int id){
        this.bookingID = id;
    }
}
