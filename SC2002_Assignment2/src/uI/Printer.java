package uI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import database.Data;
import manager.CinemaMgr;
import manager.CineplexMgr;
import manager.HolidayMgr;
import manager.MovieMgr;
import manager.MovieRankMgr;
import manager.ReviewMgr;
import manager.ShowStatusMgr;
import manager.TicketPriceMgr;
import manager.UserAccountMgr;
import model.*;
import utils.DateUtils;
import utils.SearchUtils;
import utils.TimeUtils;
import utils.Validator;

public class Printer {
	
	public static void displayTicketPrices() {
		TicketPrice buffer = TicketPriceMgr.getTicketPriceCopy();
		System.out.println("Base Price: "+buffer.getBasePrice());
		System.out.println("Weekend Price: "+buffer.getWeekendPrice());
		System.out.println("Student Discount: "+buffer.getStudentDiscount());
		System.out.println("Elderly Discount: "+buffer.getElderlyDiscount());
		System.out.println("GoldClassTicket: "+buffer.getGoldClassTicket());
		System.out.println("CoupleTicket: "+buffer.getCoupleClassTicket());
		System.out.println("Movie2DPrice: "+buffer.getMovie2DPrice());
		System.out.println("Movie3DPrice: "+buffer.getMovie3DPrice());
		System.out.println("MovieIMAXPrice: "+buffer.getMovieIMAXPrice());
		System.out.println("PlatiniumPrice: "+buffer.getPlatiniumPrice());
		System.out.println("HolidayPrice: "+buffer.getHolidayPrice());
	}
	
	public static void displayHolidayList() {
		ArrayList<Holiday> list = HolidayMgr.getAllHolidayList();
		for(int i =0;i<list.size();i++) {
			Holiday holiday = list.get(i);
			System.out.print("Holiday ID: ");
			System.out.print(holiday.getHolidayID());
			System.out.print("Holiday Name: ");
			System.out.print(holiday.getHolidayName());
			System.out.print("Holiday Date: ");
			DateUtils.print(holiday.getHolidayDate());
		}
		System.out.println();
	}
	
	
	public static ArrayList<ShowStatus> displaytMovieShowTime(int movieID) {
		System.out.println("Available Show Time:\n");
		ArrayList<ShowStatus> showStatusList = ShowStatusMgr.getAllStatusListByMovieID(movieID);
		if(showStatusList.size() ==0) {
			System.out.print("\nShowTime Is Empty\n");
			return showStatusList;
		}
		Movie movie = MovieMgr.getMovieByID(movieID);
		for(int i=0;i<showStatusList.size();i++) {
			TimeUtils showtime = showStatusList.get(i).getShowTime();
			DateUtils showDate = showStatusList.get(i).getShowDate();
			int cineplexID = showStatusList.get(i).getCineplexID();
			int cinemaID = showStatusList.get(i).getCinemaID();
			Cinema cinema = CinemaMgr.getCinemaByID(cinemaID);
			ShowStatus status = showStatusList.get(i);
			Cineplex cineplex = CineplexMgr.getCineplexByID(cineplexID);
			
			System.out.println("\n"+(i+1)+")");
			System.out.println("Movie Title: "+movie.getTitle());
			System.out.println("MovieType: "+status.getMovieType());
			System.out.println("Cineplex Name: "+cineplex.getName());
			System.out.println("Cinema ID: "+cinemaID);
			System.out.println("Cinema Class: "+cinema.getCinemaType());
			System.out.print("ShowDate: ");
			DateUtils.print(showDate);
			System.out.println();
			System.out.print("ShowTime: ");
			TimeUtils.print(showtime);
			System.out.println("\n");
		}
		
		return showStatusList;
	
	}
	
	public static void displayCasts(int movieID) {
		ArrayList<String> casts = MovieMgr.getCastsByMovieID(movieID);
		for(int i=0;i<casts.size();i++) {
			System.out.print(casts.get(i)+", ");
		}
		System.out.println();
	}
	
	
	public static void displayUserCred(int userID) {
		UserAccount user = UserAccountMgr.getUserAccount(userID);
		if(user == null) {
			System.out.print("User Not found\n");
			return;
		}
		System.out.println("UserID: "+user.getUserID());
		System.out.println("Username: "+user.getUsername());
		System.out.println("Password: "+user.getUsername());
		System.out.println("MobileNumber: "+user.getMobileNumber());
		System.out.println("Email: "+user.getEmail());
		System.out.println("Age: "+user.getAge());
	}
	
	//0 no sort, 1--> sales, 2-->rating
	public static void displayMovie(int key, int limit, int show) {
		ArrayList<MovieRank> movieRankList = MovieRankMgr.getAllMovieRankList();
		List<MovieRank> arr = new ArrayList<>();
		for(int i=0;i<movieRankList.size();i++) {
			arr.add(movieRankList.get(i));
		}

		if(key ==1) {
			Collections.sort(arr, new Comparator<MovieRank>() {
				@Override
				public int compare(MovieRank rank2, MovieRank rank1) {
					return ( rank1.getSales()>rank2.getSales() ? 1 : (rank1.getSales() < rank2.getSales() ? -1 : 0));
				}
			});
			
			for(int i=0;i<limit;i++) {
				if(i>=arr.size())return;
				Movie movie = MovieMgr.getMovieByID(arr.get(i).getMovieID());
				System.out.println(i+1+") " + movie.getTitle());
				System.out.println("   Total Sales: "+arr.get(i).getSales());
			}
		}
		else if (key==2) {
			Collections.sort(arr, new Comparator<MovieRank>() {
				@Override
				public int compare(MovieRank rank2, MovieRank rank1) {
					return ( rank1.getOverallRating()>rank2.getOverallRating() ? 1 : (rank1.getOverallRating() < rank2.getOverallRating() ? -1 : 0));
				}
			});
			
			for(int i=0;i<limit;i++) {
				if(i>=arr.size())return;
				Movie movie = MovieMgr.getMovieByID(arr.get(i).getMovieID());
				System.out.println(i+1+") " + movie.getTitle());
				System.out.println("   Overall Rating: "+arr.get(i).getOverallRating());
			}
		}	
	}
	
	//show ==0, then , dont show No_LONGER_SHOWING
	public static void displayAllMovie(int show) {
		ArrayList<Movie> movieList = MovieMgr.getAllMovieList();
		for(int i=0;i<movieList.size();i++) {
			Movie movie = movieList.get(i);
			if(movie.getMovieState() == MovieStates.NO_LONGER_SHOWING && show == 0) {
				continue;
			}
			displayMovieDetails(movie.getMovieID());
		}
	}
	
	
	public static void displayMovieDetails(int movieID) {
		Movie movie = MovieMgr.getMovieByID(movieID);
		if(movie == null) {
			System.out.println("Movie Not Found");
			return;
		}
		System.out.println("\n"+movieID+")");
		System.out.println("Title: "+movie.getTitle());
		System.out.println("Showing status: "+ movie.getMovieState());
		System.out.println("Director: "+ movie.getDirector());
		System.out.println("SYNOPSIS: "+ movie.getMovieContent());
		System.out.print("Cast: ");
		for(int i=0;i<movie.getCasts().size();i++) {
			System.out.print(movie.getCasts().get(i)+", ");
		}
		System.out.println();
		displayOverallRating(movieID);
		System.out.print("==============================\n");
		System.out.print("          PAST REVIEW         \n");
		System.out.print("==============================\n");
		displayMovieReviews(movieID);
		System.out.println();
	}
	
	
	public static void displayOverallRating(int movieID) {
		MovieRank buffer = MovieRankMgr.getMovieRankByMovieID(movieID);
		if(buffer == null) {
			System.out.print("Movie Not found\n");
			return;
		}
		double rating = buffer.getOverallRating();
		int raters = buffer.getNumRaters();
		if(raters>1) {
			System.out.println("Overall Rating: "+rating);
		}
		else {
			System.out.println("Overall Rating: NA");
		}
	}
	
	
	public static void displayMovieReviews(int movieID) {
		ArrayList<MovieReview> reviewList = ReviewMgr.getReveiwListByMovieID(movieID);
		if(reviewList.size() == 0) {
			System.out.print("Currently No Reviews\n");
			return;
		}
		for(int i=0;i<reviewList.size();i++) {
			MovieReview buffer = reviewList.get(i);
			System.out.print((i+1)+")");
			if(buffer.getRating()>0) {	
				System.out.println(" Rating: "+buffer.getRating());
			}else {
				System.out.println(" Rating: NA");
			}
			if(buffer.getComment() != null) {
				System.out.println("   Comment: "+buffer.getComment());
			}else {
				System.out.println("   Comment: NA");
			}
		}
	}
}
