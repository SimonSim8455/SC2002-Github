package uI;

import java.util.*;

import manager.CineplexMgr;
import manager.MovieMgr;
import manager.ShowStatusMgr;
import model.*;
import utils.DateUtils;
import utils.SearchUtils;
import utils.TimeUtils;

public class ShowTimeConfig {

	
	
	public static void EditShowTime(Scanner sc) {
		System.out.print("\n Please Enter ShowTime ID: ");
		int showStatusID = sc.nextInt();
		ShowStatus showStatus = ShowStatusMgr.getShowStatusByID(showStatusID);
		if(showStatus ==null) {
			System.out.print("Not found, Please try again\n");
			return;
		}
		

		while(true) {
			System.out.print("\n1: Remove ShowStatus\n");
			System.out.print("2: Update Movie\n");
			System.out.print("3: Update Show Date\n");
			System.out.print("4: Update Show Time\n");
			System.out.print("5: Update Movie Type\n");
			System.out.print("0: Go Back\n");
			System.out.print("Please Choose Your Action: ");
			
			int day,month,year,hour,minute;
			int choice = sc.nextInt();
			switch(choice) {
				case 0:
					return;
				case 1:
					ShowStatusMgr.removeShowStatus(showStatusID);
					System.out.print("ShowStatus Removed\n");
					return;
				case 2:
					String name;
					sc.nextLine();
					System.out.print("Enter Movie Name: ");
					name = sc.nextLine();
					Movie movie = MovieMgr.getMovieByName(name);
					if(movie == null) {
						System.out.print("Movie not found\n");
						break;
					}
					ShowStatusMgr.updateMovie(showStatusID, name);
					System.out.print("Movie Updated\n");
					break;
				case 3:
					System.out.print("Enter ShowDate:\n");
					System.out.print("Enter day: ");
					day = sc.nextInt();
					System.out.print("Enter month:");
					month = sc.nextInt();
					System.out.print("Enter year:");
					year = sc.nextInt();
					DateUtils date = new DateUtils(day,month,year);
					ShowStatusMgr.updateShowDate(showStatusID, date);
					System.out.print("Date Updated\n");
					break;
				case 4:
					System.out.print("Enter ShowTime:\n");
					System.out.print("Enter hour: ");
					hour = sc.nextInt(); 
					System.out.print("Enter minute:");
					minute = sc.nextInt();
					TimeUtils time = new TimeUtils(hour,minute);
					ShowStatusMgr.updateShowTime(showStatusID, time);
					System.out.print("ShowTime updated Updated\n");
					break;
				case 5:
					MovieType type = promptMovieTypeInput(sc);
					ShowStatusMgr.updateMovieType(showStatusID, type);
					System.out.print("Movie Type Updated\n");
					break;
				default:
					break;
			}
		}
	}
	
	public static MovieType promptMovieTypeInput(Scanner sc) {
		int typeID;
		MovieType movieType = MovieType.TWOD;
		while(true) {	
			System.out.print("\nEnter MovieType:\n");
			System.out.println("1: 2D");
			System.out.println("2: 3D");
			System.out.println("3: IMAX");
			System.out.print("Type ID: ");
			typeID = sc.nextInt();
			
			switch(typeID) {
				case 1:
					movieType = MovieType.TWOD;
					break;
				case 2:
					movieType = MovieType.THREED;
					break;
				case 3:
					movieType = MovieType.IMAX;
					break;
				default:
					System.out.print("Please Enter a valid type");
			}
			
			if(typeID>=1 && typeID<=3) {
				break;
			}
		}
		return movieType;
	}
	public static void displayMovieShowTime(Scanner sc) {
		String str;
		System.out.print("Movie Name: ");
		sc.nextLine();
		str = sc.nextLine();
		Movie movie = MovieMgr.getMovieByName(str);
		if(movie==null) {
			System.out.print("Movie not found\n");
			return;
		}
		System.out.print("\n"+movie.getMovieID()+ "  " + movie.getTitle()+"\n");
		
	
	}
	
	public static void AddShowTime(Scanner sc) {
		String cineplexName,movieName;
		int cinemaID, cineplexID, movieID, day, month, year, hour, minute, typeID;
		Cineplex cineplex;
		Movie movie;
		MovieType movieType = MovieType.TWOD;
		sc.nextLine();
		do {
			System.out.print("Enter Cineplex Name= ");
			cineplexName = sc.nextLine();
			cineplex = CineplexMgr.getCineplexByName(cineplexName);
			if(cineplex ==null) {
				System.out.print("Not found, please try again \n");
			
			}else {
				break;
			}
		}while(true);
		
		cineplexID = cineplex.getCineplexID();
		
		System.out.print("Enter Cinema ID= ");
		cinemaID = sc.nextInt();
		
		sc.nextLine();
		do {
			System.out.print("Enter Movie Name: ");
			movieName = sc.nextLine();
			movie = MovieMgr.getMovieByName(movieName);
			if(movie == null) {
				System.out.print("Not found, please try again \n");
			}else {
				break;
			}
		}while(true);
		
		movieID = movie.getMovieID();
		
		System.out.print("Enter ShowDate:\n");
		System.out.print("Enter day: ");
		day = sc.nextInt();
		System.out.print("Enter month:");
		month = sc.nextInt();
		System.out.print("Enter year:");
		year = sc.nextInt();
		DateUtils date = new DateUtils(day,month,year);
		
		System.out.print("Enter ShowTime:\n");
		System.out.print("Enter hour: ");
		hour = sc.nextInt(); 
		System.out.print("Enter minute:");
		minute = sc.nextInt();
		TimeUtils time = new TimeUtils(hour,minute);
		
		while(true) {	
			System.out.print("Enter MovieType:\n");
			System.out.println("1: 2D");
			System.out.println("2: 3D");
			System.out.println("3: IMAX");
			System.out.print("Type ID: ");
			typeID = sc.nextInt();
			
			switch(typeID) {
				case 1:
					movieType = MovieType.TWOD;
					break;
				case 2:
					movieType = MovieType.THREED;
					break;
				case 3:
					movieType = MovieType.IMAX;
					break;
				default:
					System.out.print("Please Enter a valid type");
			}
			
			if(typeID>=1 && typeID<=3) {
				break;
			}
		}
		
		boolean pass = ShowStatusMgr.createShowStatus(cineplexID, cinemaID, movieID, date, time, movieType);
		
		if(!pass) {
			System.out.println("Fail to create show time");
		}
		else {
			System.out.println("Success create");
		}
		return;
	}
	
	public static void AppMain(Scanner sc) {
		while(true) {
			int num =-1;
			System.out.print("\n1: View All Movie And ShowTime\n");
			System.out.print("2: View a movie Show time\n");
			System.out.print("3: Add show time\n");
			System.out.print("4: edit show time\n");
			System.out.print("0: Go Back\n");
			System.out.print("Please Choose Your Action: ");
			num = sc.nextInt();
			
			switch(num) {
			case 0: 
				return;
				case 1: 
//					Printer.displayAllMovieAndShowTime();
					break;
				case 2:
					displayMovieShowTime(sc);
					break;
				case 3:
					AddShowTime(sc);
					break;
				case 4:
					EditShowTime(sc);
					break;
				default:
					break;
					
			}
		}
	}
}
