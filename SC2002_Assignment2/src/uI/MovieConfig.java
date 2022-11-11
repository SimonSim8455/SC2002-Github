package uI;
import java.util.Scanner;
import model.*;
import utils.SearchUtils;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import database.Data;
import manager.MovieMgr;

public class MovieConfig {
	
	public static void AppMain(Scanner sc) {
		
		while(true) {
			int a;
			System.out.print("\n1: Add New Movie\n");
			System.out.print("2: View Movie List\n");
			System.out.print("0: Back to Main Menu\n");
			System.out.print("Please Choose Your Action: ");
			a = sc.nextInt();
			switch(a) {
				case 0:
					return;
				case 1:
					AddNewMovie(sc);
					break;
				case 2:
					ViewMovieList(sc);
					break;
				default:
					break;
					
			}
		}
	}
	
	public static void AddNewMovie(Scanner sc) {
		sc.nextLine();
		System.out.print("Title: ");
		String title = sc.nextLine();
		
		System.out.print("Director: ");
		String director = sc.nextLine();
		
		System.out.print("Cast 1: ");
		String cast1 = sc.nextLine();
		
		System.out.print("Cast 2: ");
		String cast2 = sc.nextLine();
		
		ArrayList<String> casts = new ArrayList<String>();
		casts.add(cast1);
		casts.add(cast2);
		
		System.out.print("Sypnopsis: ");
		String movieContent= sc.nextLine();
		
		System.out.print("Duration: ");
		int duration= sc.nextInt();
		sc.nextLine();
		
		System.out.print("MovieState:\n");
		System.out.print("1: COMING SOON  ");
		System.out.print("2: NOW SHOWING  ");
		System.out.println("3: NO_LONGER_SHOWING  ");
		System.out.print("Status ID: ");
		int stateID= sc.nextInt();
		MovieStates state;
		switch(stateID) {
			case 1:
				state = MovieStates.COMING_SOON;
				break;
			case 2:
				state = MovieStates.NOW_SHOWING;
				break;
			case 3:
				state = MovieStates.NO_LONGER_SHOWING;
				break;
			default:
				state = MovieStates.NOW_SHOWING;
				break;
		}
		
		int index = MovieMgr.createMovie(title, director, casts, movieContent, duration, state);
		if(index == -1) {
			System.out.println("Unable to create this movie, Please try again");
		}
		else {
			System.out.println("Success created");
		}
	}

	public static void ViewMovieList(Scanner sc) {
		while(true) {
			int a =-1;
			System.out.print("\n1: View and Update Movies\n");
			System.out.print("2: View Top 5 Ticket Sales\n");
			System.out.print("3: view Top 5 overall Rating\n");
			System.out.print("0: go back\n");
			System.out.print("Please Choose Your Action: ");
			a = sc.nextInt();
			switch(a) {
				case 0:
					return;
				case 1:
					chooseMoviePrompt(sc) ;
					break;
				case 2:
					Printer.displayMovie(1, 5, 1);
					break;
				case 3:
					Printer.displayMovie(2, 5, 1);
					break;
				default:
					break;
			}
		}
	}
	
	public static void chooseMoviePrompt(Scanner sc) {
		int id;
		do {
			System.out.print("\nChoose a movieID to update (or enter -1 to quit: ");
			id = sc.nextInt();
			Movie movie = MovieMgr.getMovieByID(id);
			if(movie == null) {
				System.out.print("Movie cant be found, please try again\n");
			}else {
				configMovie(sc,id);
				return;
			}
		}while(id>=0);
	}
	

	public static void configMovie(Scanner sc ,int movieID) {
		Printer.displayMovieDetails(movieID);
		int a;
		do {
			System.out.print("\n1: Update Title \n");
			System.out.print("2: Update Director\n");
			System.out.print("3: Update synopesis\n");
			System.out.print("4: Update casts\n");
			System.out.print("5: Update duration\n");
			System.out.print("6: Update Movie states\n");
			System.out.print("0: go back\n");
			System.out.print("Please Choose Your Action: ");
			a = sc.nextInt();
			switch(a) {
				case 0:
					return;
				case 1:
					updateMovieString(sc,movieID,a);
					break;
				case 2:
					updateMovieString(sc,movieID,a);
					break;
				case 3:
					updateMovieString(sc,movieID,a);
					break;
				case 4:
					updateCast(sc, movieID);
					break;
				case 5:
					updateDuration(sc, movieID);
					break;
				case 6:
					updateMovieState(sc,movieID);
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void updateCast(Scanner sc, int movieID) {
		int choice;
		do {
			System.out.print("\n1: View casts: \n");
			System.out.print("2: Add casts\n");
			System.out.print("3: Remove Casts\n");
			System.out.print("0: go back\n");
			System.out.print("Please Choose Your Action: ");
			
			choice = sc.nextInt();
			String castName;
			switch(choice) {
				case 0:
					return;
				case 1:
					System.out.print("Casts: ");
					Printer.displayCasts(movieID);
					System.out.println();
					break;
				case 2:
					System.out.print("Cast Name to be added: ");
					sc.nextLine();
					castName = sc.nextLine();
					MovieMgr.addCasts(movieID, castName);
					break;
				case 3:
					System.out.print("Cast Name to be removed: ");
					sc.nextLine();
					castName = sc.nextLine();
					MovieMgr.removeCasts(movieID, castName);
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void updateMovieString(Scanner sc, int movieID, int action) {
		sc.nextLine();
		if(action ==1) {
			System.out.print("New Title: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieTitle(movieID, str);
			return;
		}
		if(action == 2) {
			System.out.print("New Dirctor: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieDirector(movieID, str);
			return;
		}
		if(action ==  3) {
			System.out.print("New Synopsis: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieContent(movieID, str);
			return;
		}
	}
	
	public static void updateDuration(Scanner sc, int movieID) {
		System.out.print("New Duaration: ");
		int duration = sc.nextInt();
		MovieMgr.updateMovieDuration(movieID, duration);
		return;
	}
	
	public static void updateMovieState(Scanner sc, int movieID) {
		System.out.print("1: COMING SOON  ");
		System.out.print("2: NOW SHOWING  ");
		System.out.println("3: NO_LONGER_SHOWING  ");
		System.out.print("New Status ID: ");
		int id = sc.nextInt();
		switch(id){
			case 1: 
				MovieMgr.updateMovieState(movieID,MovieStates.COMING_SOON);
				break;
			case 2: 
				MovieMgr.updateMovieState(movieID,MovieStates.NOW_SHOWING);
				break;
			case 3: 
				MovieMgr.updateMovieState(movieID,MovieStates.NO_LONGER_SHOWING );
				break;
			default:
				System.out.print("Invalid input\n");
				return;
		}
		
	}
	


	

}
