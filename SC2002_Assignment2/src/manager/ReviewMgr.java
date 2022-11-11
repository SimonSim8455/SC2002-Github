package manager;

import java.util.ArrayList;
import java.util.HashMap;
import database.Data;
import model.MovieRank;
import model.MovieReview;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class ReviewMgr {
	private static HashMap<Integer, MovieReview> movieReviewList = Data.movieReviewList;
	
	public static ArrayList<MovieReview> getReveiwListByUserID(int userID){
		ArrayList<MovieReview> list = new ArrayList<MovieReview>();
		if(Validator.validateUser(userID)!= 1 ) {
			return null;
		}
		for(MovieReview review : movieReviewList.values()) {
			if(review.getUserID() == userID) {
				list.add(MovieReview.copy(review));
			}
		}
		return list;
	}
	
	public static ArrayList<MovieReview> getReveiwListByMovieID(int movieID){
		ArrayList<MovieReview> list = new ArrayList<MovieReview>();
		if(Validator.validateMovie(movieID) == -1) {
			return null;
		}
		for(MovieReview review : movieReviewList.values()) {
			if(review.getMovieID() == movieID) {
				list.add(MovieReview.copy(review));
			}
		}
		return list;
	}
	
	public static boolean createMovieReview(int userID, int movieID, int rating, String comment) {
		if(Validator.validateUser(userID)!= 1 || Validator.validateMovie(movieID) == -1) {
			return false;
		}
		if(Validator.validateReview(userID, movieID) !=1) {
			return false;
		}
		int reviewID = Helper.getUniqueId(movieReviewList);
		MovieReview newReview = new MovieReview(reviewID,userID,movieID,rating,comment);
		movieReviewList.put(reviewID, newReview);
		MovieRankMgr.addRating(movieID, userID, rating);
		return true;
	}
	
	public static boolean createMovieReview(int userID, int movieID,String comment) {
		if(Validator.validateUser(userID)!= 1 || Validator.validateMovie(movieID) == -1) {
			return false;
		}
		if(Validator.validateReview(userID, movieID) !=1) {
			return false;
		}
		int reviewID = Helper.getUniqueId(movieReviewList);
		MovieReview newReview = new MovieReview(reviewID,userID,movieID,comment);
		movieReviewList.put(reviewID, newReview);
		return true;
	}
	
	public static boolean createMovieReview(int userID, int movieID, int rating) {
		if(Validator.validateUser(userID)!= 1 || Validator.validateMovie(movieID) == -1) {
			return false;
		}
		if(Validator.validateReview(userID, movieID) !=1) {
			return false;
		}
		int reviewID = Helper.getUniqueId(movieReviewList);
		MovieReview newReview = new MovieReview(reviewID,userID,movieID,rating);
		movieReviewList.put(reviewID, newReview);
		MovieRankMgr.addRating(movieID, userID, rating);		
		return true;
	}
	

	public static boolean updateReviewRating(int userID, int movieID, int num ) {
		if(Validator.validateUser(userID) == -1 || Validator.validateMovie(movieID) == -1) {
			return false;
		}
		MovieReview buffer = SearchUtils.searchMovieReview(userID, movieID);
		if(Validator.validateMovieReview(buffer.getReviewID()) == -1) {
			return false;
		}
		MovieRankMgr.changeRating(movieID, userID, num);
		buffer.setRating(num);
		return true;
	}
	
	
	public static boolean updateReviewComment(int userID, int movieID, String comment) {
		if(Validator.validateUser(userID) == -1 || Validator.validateMovie(movieID) == -1) {
			return false;
		}
		MovieReview buffer = SearchUtils.searchMovieReview(userID, movieID);
		if(Validator.validateMovieReview(buffer.getReviewID()) == -1) {
			return false;
		}
		buffer.setComment(comment);
		return true;
	}
	
	
	
}
