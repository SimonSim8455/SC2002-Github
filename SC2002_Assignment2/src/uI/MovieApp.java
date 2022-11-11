package uI;
import java.util.ArrayList;
import java.util.Scanner;

import manager.MovieMgr;
import model.Movie;
import model.MovieStates;
import utils.*;

public class MovieApp {

	public static void AppMain(Scanner sc) {
		do {
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("             Movie Listing              \n");
			System.out.print("========================================\n");
			System.out.print("1) View All Movie List\n");
			System.out.print("2) View Top 5 Movie By Sales\n");
			System.out.print("3) View Top 5 Movie By Overall Rating\n");
			System.out.print("4) Search Movie\n");
			System.out.print("5) Book Ticket\n");
			System.out.print("6) Comment and Rate A Movie\n");
			System.out.print("0) Go back\n");
			System.out.print("\nEnter your choice: ");
			num = sc.nextInt();
			switch(num) {
				case 0:
					return;
				case 1:
					Printer.displayAllMovie(0);
					break;
				case 2:
					Printer.displayMovie(1, 5, 1);
					break;
				case 3:
					Printer.displayMovie(2, 5, 1);
					break;
				case 4:
					SearchMovie(sc);
					break;
				case 5:
					BookTicket(sc);
					break;
				case 6:
					CommentRate(sc);
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void CommentRate(Scanner sc) {
		if(AppState.getUserID() ==-1) {
			System.out.print("You must login first before you can rate a movie\n");
			return;
		}
		int movieID = SearchMovie(sc);
		AppState.setMovieID(movieID);
		CommentRateApp.AppMain(sc);
		return;
	}
	
	public static void BookTicket(Scanner sc) {
		if(AppState.getUserID() ==-1) {
			System.out.print("You must login first before you can book tickets\n");
			return;
		}
		String str;
		sc.nextLine();
		while(true) {
			System.out.print("\nEnter movie title for booking: ");
			str = sc.nextLine();
			ArrayList<Movie> result = new ArrayList<Movie>();
			result = Helper.SearchResultsForBooking(str);
			if(result.size() == 0) {
				System.out.println("No Movie Found\n");
				return;
			}
			for(int j=0;j<result.size();j++) {
				System.out.println(j+1+") "+result.get(j).getTitle());
			}
			int resultIndex;
			System.out.println();
			while(true) {	
				System.out.print("Please Enter Movie ID For Booking(or enter 0 to quit): ");
				resultIndex = sc.nextInt() -1;
				sc.nextLine();
				if(resultIndex <= -1) {
					return;
				}
				if(resultIndex>= result.size()) {
					System.out.print("Please choose a valid ID\n");
				}else {
					break;
				}
			}
			Movie movie = result.get(resultIndex);
			AppState.setMovieID(movie.getMovieID());
			BookingApp.AppMain(sc);
			return;
		}
	}

	
	public static int SearchMovie(Scanner sc) {
		String str;
		sc.nextLine();
		while(true) {
			System.out.print("\nEnter movie title: ");
			str = sc.nextLine();
			ArrayList<Movie> result = new ArrayList<Movie>();
			result = Helper.SearchResultsForViewing(str);
			if(result.size() == 0) {
				System.out.println("No Movie Found\n");
				return -1;
			}
			for(int j=0;j<result.size();j++) {
				System.out.println(j+1+") "+result.get(j).getTitle());
			}
			int resultIndex;
			System.out.println();
			while(true) {	
				System.out.print("Please Enter Movie ID (or enter 0 to quit): ");
				resultIndex = sc.nextInt() -1;
				sc.nextLine();
				if(resultIndex <= -1) {
					return -1;
				}
				if(resultIndex>= result.size()) {
					System.out.print("Please choose a valid ID\n");
				}else {
					break;
				}
			}
			Movie movie = result.get(resultIndex);
			Printer.displayMovieDetails(movie.getMovieID());
			return movie.getMovieID();
		}
	}

}
