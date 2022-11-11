package utils;

import java.util.ArrayList;
import java.util.HashMap;
import database.Data;
import model.*;

public class Validator {
	//  Global return value doc:
	/*  -1: Cannot be found
	 *   0: Found but Not Valid/OnGoing Status
	 *	 1: Found and Valid
	 *	 
	 */
	
	//
	
	
	public static int validateReview(int userID, int movieID) {
		ArrayList<Integer> reviewIDs = SearchUtils.ReviewIDListByID(userID, 0);
		HashMap<Integer, MovieReview> reviewList = Data.movieReviewList;
		for(int i=0;i<reviewIDs.size();i++) {
			MovieReview buffer = reviewList.get(reviewIDs.get(i));
			if(buffer.getMovieID() == movieID) {
				return 0;
			}
		}
		return 1;
	}
	public static int validateBooking(int id) {
		Booking buffer = SearchUtils.searchBooking(id);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	public static int validateMovieRank(int Movieid) {
		MovieRank buffer = SearchUtils.searchMovieRankByMovieID(Movieid);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateBookingList(int userID, ArrayList<Integer> list) {
		for(int i=0;i<list.size();i++) {
			Booking buffer = SearchUtils.searchBooking(list.get(i));
			if(buffer == null) {
				return -1;
			}
			if(buffer.getUserID() != userID) {
				return 0;
			}
		}
		return 1;
	}
	
	public static int validateReviewList(int userID, ArrayList<Integer> list) {
		for(int i=0;i<list.size();i++) {
			MovieReview buffer = SearchUtils.searchMovieReview(list.get(i));
			if(buffer == null) {
				return -1;
			}
			if(buffer.getUserID() != userID) {
				return 0;
			}
		}
		return 1;
	}
	
	public static int validateMovieReview(int reviewID) {
		MovieReview buffer = SearchUtils.searchMovieReview(reviewID);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateMovie(int movieID) {
		Movie buffer = SearchUtils.searchMovie(movieID);
		if(buffer == null) {
			return -1;
		}
		
		if(buffer.getMovieState() == MovieStates.NO_LONGER_SHOWING) {
			return 0;
		}
		return 1;
	}
	
	public static int validateMovie(String movieName) {
		Movie buffer = SearchUtils.searchMovie(movieName);
		if(buffer == null) {
			return -1;
		}
		
		if(buffer.getMovieState() == MovieStates.NO_LONGER_SHOWING) {
			return 0;
		}
		return 1;
	}
	
	public static int validateHoliday(int holidayID) {
		Holiday buffer = SearchUtils.searchHoliday(holidayID);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateHoliday(String name, DateUtils date) {
		Holiday buffer = SearchUtils.searchHoliday(name,date);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	
	// cinema Must be created before adding into Cineplex
	public static int validateCinemaList(int cineplexID, ArrayList<Integer> list) {
		for(int i=0;i<list.size();i++) {
			Cinema buffer = SearchUtils.searchCinema(list.get(i));
			if(buffer == null) {
				return -1;
			}
			if(buffer.getCineplexID() != cineplexID) {
				return 0;
			}
		}
		return 1;
	}
	
	public static int validateCinema(int cinemaID) {
		Cinema buffer = SearchUtils.searchCinema(cinemaID);
		if(buffer == null) {
			return -1;
		}
		HashMap<Integer,Cinema> cinemaList = Data.cinemaList;
		if(Validator.validateCineplex(buffer.getCineplexID()) ==-1) {
			cinemaList.remove(cinemaID);
			return 0;
		}
		return 1;
	}
	
	public static int validateCineplex(int cineplexID) {
		Cineplex buffer = SearchUtils.searchCineplex(cineplexID);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateCineplex(String cineplexName) {
		Cineplex buffer = SearchUtils.searchCineplex(cineplexName);
		if(buffer == null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateUser(String username) {
		UserAccount buffer = SearchUtils.searchUserAccount(username);
		if(buffer==null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateUser(int userID) {
		UserAccount buffer = SearchUtils.searchUserAccount(userID);
		if(buffer==null) {
			return -1;
		}
		return 1;
	}
	
	public static int validateShowStatus(int showStatusID) {
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		if(buffer == null) {
			return -1;
		}
		
		HashMap<Integer,ShowStatus> showStatusList = Data.showStatusList;
		if(Validator.validateCineplex(buffer.getCineplexID()) ==-1) {
			showStatusList.remove(showStatusID);
			return 0;
		}
		if(Validator.validateCinema(buffer.getCinemaID()) == -1) {
			showStatusList.remove(showStatusID);
			return 0;
		}
		
		return 1;
	}
	
	//ShowStatus must always be created b4 adding into CinemaList
	public static int validateShowStatusList(int cineplexID, int cinemaID, ArrayList<Integer> list) {
		for(int i=0;i<list.size();i++) {
			ShowStatus buffer = SearchUtils.searchShowStatus(list.get(i));
			if(buffer == null) {
				return -1;
			}
			if(buffer.getCineplexID() != cineplexID) {
				return 0;
			}
			if(buffer.getCinemaID() != cinemaID) {
				return 0;
			}
		}
		return 1;
	}
}
