package model;

import utils.DateUtils;
import utils.TimeUtils;

public class Booking {
	private int bookingID;
    private int userID;
    private int movieID;
    private int cineplexID;
    private int cinemaID;
    
    private MovieType movieType;
    private double price;
    
	private DateUtils bookingDate;
    private TimeUtils bookingTime;
    private DateUtils showDate;
    private TimeUtils showtime;
    private String TID;
    
    public Booking() {}
    
    public Booking(int bookingID, int userID, int movieID, int cineplexID, int cinemaID, 
    		MovieType movieType, double price,
    		DateUtils bookingDate,TimeUtils bookingTime,DateUtils showDate,TimeUtils showtime) {
    	this.bookingID = bookingID;
    	this.userID = userID;
    	this.movieID = movieID;
    	this.cineplexID = cineplexID;
    	this.cinemaID = cinemaID;
    	this.movieType = movieType;
    	this.price = price;
    	this.bookingDate = bookingDate;
    	this.bookingTime = bookingTime;
    	this.showDate = showDate;
    	this.showtime = showtime;
    	
    	String year = String.valueOf(bookingDate.getYear());
    	
    	String month = String.valueOf(bookingDate.getMonth());
    	if(bookingDate.getMonth() <10) {
    		month = "0" + String.valueOf(bookingDate.getMonth());
    	}
    	
    	String day = String.valueOf(bookingDate.getDay());
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
    		another.getMovieID(),
    		another.getCineplexID(),
    		another.getCinemaID(),
    		another.getMovieType(),
    		another.getPrice(),
    		another.getBookingDate(),
    		another.getBookingTime(),
    		another.getShowDate(),
    		another.getShowTime()
    	);
    	return booking;
    }
    
    public void setTID(String tid) {
    	this.TID = tid;
    }
    public String getTID() {
    	return this.TID;
    }
    public DateUtils getShowDate() {
    	return this.showDate;
    }
    
    public void setShowDate(DateUtils date) {
    	this.showDate = date;
    }
    
    
    public int getMovieID() {
    	return this.movieID;
    }
    
    public void setMovieID(int id) {
    	this.movieID = id;
    }
    
    public MovieType getMovieType() {
    	return this.movieType;
    }
    
    public void setMovieType(MovieType type) {
    	this.movieType = type;
    }
    
    public int getCineplexID() {
    	return this.cineplexID;
    }

    public int getCinemaID() {
    	return this.cinemaID;
    }
    
    public void setCineplexID(int id) {
    	this.cineplexID =id;
    }
    
    public void setCinemaID(int id) {
    	this.cinemaID = id;
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

    public TimeUtils getShowTime(){
        return this.showtime;
    }

    public void setShowtime(TimeUtils showtime){
        this.showtime = showtime;
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
