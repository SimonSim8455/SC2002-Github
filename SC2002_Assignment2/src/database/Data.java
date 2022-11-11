package database;

import java.util.HashMap;

import model.*;

public class Data {
	
	public static HashMap<Integer, Movie> movieList = new HashMap<Integer, Movie>();
	public static HashMap<Integer, UserAccount> userAccountList = new HashMap<Integer, UserAccount>();
	public static HashMap<Integer, Holiday> holidayList = new HashMap<Integer, Holiday>();
	public static HashMap<Integer, Cineplex> cineplexList = new HashMap<Integer, Cineplex>();
	public static HashMap<Integer, Booking> bookingList = new HashMap<Integer, Booking>();
	public static HashMap<Integer, ShowStatus> showStatusList = new HashMap<Integer, ShowStatus>();
	public static HashMap<Integer, Cinema> cinemaList = new HashMap<Integer, Cinema>();
	public static HashMap<Integer, MovieReview> movieReviewList = new HashMap<Integer, MovieReview>();
	public static HashMap<Integer, MovieRank> movieRankList = new HashMap<Integer, MovieRank>();
	public static TicketPrice ticketPrice = new TicketPrice();
	
	
}
