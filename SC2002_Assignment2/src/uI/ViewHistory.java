package uI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import database.Data;
import manager.BookingMgr;
import manager.MovieMgr;
import manager.ReviewMgr;
import model.*;
import utils.DateUtils;
import utils.SearchUtils;
import utils.TimeUtils;

public class ViewHistory {
	public static int PromptUserInput(Scanner sc) {
		int num =-1;
		System.out.print("\n1: View Booking History\n");
		System.out.print("2: View Reveiew History\n");
		System.out.print("3: Go back\n");
		System.out.print("Please Choose Your Action: ");
		num = sc.nextInt();
		return num;
	}
	
	public static void AppMain(Scanner sc) {
		int a;
		do {
			a = PromptUserInput(sc);
			switch(a) {
				case 1:
					displayBookingHistory();
					break;
				case 2:
					displayReviewHistory();
					break;
				case 3:
					return;
				default:
					break;
			}
			
		}while(true);
	}
	
	public static void displayReviewHistory() {
		ArrayList<MovieReview> reviewList = ReviewMgr.getReveiwListByUserID(AppState.getUserID());
		for(int i=0;i<reviewList.size();i++) {
			MovieReview buffer = reviewList.get(i);
			Movie movie = MovieMgr.getMovieByID(buffer.getMovieID());
			System.out.println("Movie Title: "+movie.getTitle());
			if(buffer.getRating() == 0) {
				System.out.println("Rating: NA");
			}
			else {
				System.out.println("Rating: "+buffer.getRating());
			}
			System.out.println("Comment: "+buffer.getComment());
		}
	}
	
	public static void displayBookingHistory() {
		ArrayList<Booking> bookingList = BookingMgr.getBookingListByUserID(AppState.getUserID());
		for(int i=0;i<bookingList.size();i++) {
			Booking buffer = bookingList.get(i);
			Movie movie = MovieMgr.getMovieByID(buffer.getMovieID());
			System.out.println("\nMovie Title: "+movie.getTitle());
			System.out.println("Price: "+buffer.getPrice());
			System.out.print("Booking Date: ");
			DateUtils.print(buffer.getBookingDate());
			
			System.out.print("\nBooking Time: ");
			TimeUtils.print(buffer.getBookingTime());
			
			System.out.print("\nShow Date: ");
			DateUtils.print(buffer.getShowDate());
			
			System.out.print("\nShow Time: ");
			TimeUtils.print(buffer.getShowTime());
			
			System.out.println();
		}
	}
}
