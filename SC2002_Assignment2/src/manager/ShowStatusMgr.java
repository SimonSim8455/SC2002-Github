package manager;

import java.util.ArrayList;
import java.util.HashMap;

import database.Data;
import model.*;
import model.SeatType;
import model.ShowStatus;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.TimeUtils;
import utils.Validator;

public class ShowStatusMgr {
	private static HashMap<Integer, ShowStatus> showStatusList = Data.showStatusList;
	
	public static ArrayList<ShowStatus> getAllStatusListByMovieID(int movieID){
		ArrayList<ShowStatus> list = new ArrayList<ShowStatus>();
		for(ShowStatus buffer : showStatusList.values()) {
			if(buffer.getMovieID() == movieID) {
				
				list.add(ShowStatus.copy(buffer));
			}
		}
		return list;
	}
	
	public static ArrayList<ShowStatus> getAllStatusList(){
		ArrayList<ShowStatus> list = new ArrayList<ShowStatus>();
		for(ShowStatus buffer : showStatusList.values()) {
			list.add(ShowStatus.copy(buffer));
		}
		return list;
	}
	
	public static ShowStatus getShowStatusByID(int statusID) {
		if(Validator.validateShowStatus(statusID) != 1) {
			return null;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(statusID);
		return ShowStatus.copy(buffer);
	}
	
	public static boolean createShowStatus(int cineplexID, int cinemaID, 
			int movieID, DateUtils showDate, TimeUtils showTime, MovieType movieType, SeatType[][] seatStatus) {
		
		if(Validator.validateCineplex(cineplexID) != 1 || Validator.validateCinema(cinemaID) != 1) {
			return false;
		}
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		int showStatusID = Helper.getUniqueId(showStatusList);
		ShowStatus buffer = new ShowStatus(showStatusID, cineplexID, cinemaID, 
			movieID, showDate, showTime, movieType, seatStatus);
		showStatusList.put(showStatusID, buffer);
		return true;
	}
	
	
	public static boolean createShowStatus(int cineplexID, int cinemaID, 
			int movieID, DateUtils showDate, TimeUtils showTime, MovieType movieType) {
		
		if(Validator.validateCineplex(cineplexID) != 1 || Validator.validateCinema(cinemaID) != 1) {
			return false;
		}
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		int showStatusID = Helper.getUniqueId(showStatusList);
		Cinema cinema  = SearchUtils.searchCinema(cinemaID);
		SeatType [][] seatStatus = cinema.getSeatPlan();
		ShowStatus buffer = new ShowStatus(showStatusID, cineplexID, cinemaID, 
			movieID, showDate, showTime, movieType, seatStatus);
		showStatusList.put(showStatusID, buffer);
		return true;
	}
	
	public static boolean removeShowStatus(int showStatusID) {
		if(Validator.validateShowStatus(showStatusID) ==-1) {
			return false;
		}
		showStatusList.remove(showStatusID);
		return true;
	}
	
	public static boolean updateMovie(int showStatusID, int movieID) {
		if(Validator.validateShowStatus(showStatusID) ==-1 || Validator.validateMovie(movieID) == -1) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setMovieID(movieID);
		return true;
	}
	
	public static boolean updateMovie(int showStatusID, String movieName) {
		Movie movie = SearchUtils.searchMovie(movieName);
		if(movie == null) {
			return false;
		}
		if(Validator.validateShowStatus(showStatusID) ==-1) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setMovieID(movie.getMovieID());
		return true;
	}
	
	public static boolean updateShowDate(int showStatusID, DateUtils date) {
		if(Validator.validateShowStatus(showStatusID) ==-1) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setShowDate(date);
		return true;
	}
	
	public static boolean updateShowTime(int showStatusID, TimeUtils time) {
		if(Validator.validateShowStatus(showStatusID) ==-1) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setShowTime(time);
		return true;
	}
	
	
	public static boolean updateMovieType(int showStatusID, MovieType type) {
		if(Validator.validateShowStatus(showStatusID) ==-1) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setMovieType(type);
		return true;
	}
	
	public static boolean updateSeat(int showStatusID, int row, int col) {
		if(Validator.validateShowStatus(showStatusID) ==-1) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		SeatType [][] seats = buffer.getseatStatus();
		if(row >= seats.length || col>=seats[0].length) {
			return false;
		}
		SeatType type = seats[row][col];
		if(type == null || ValidSeat(type) == false) {
			return false;
		}
		
		int i = row;
		int j = col;
		buffer.getseatStatus()[i][j] = takenSeat(type);
		if(type == SeatType.COUPLE_1) {
			buffer.getseatStatus()[i][j+1] = takenSeat(SeatType.COUPLE_2);
		}
		if(type == SeatType.COUPLE_2) {
			buffer.getseatStatus()[i][j-1] = takenSeat(SeatType.COUPLE_1);
		}
		return true;
	}
	
	public static boolean ValidSeat(SeatType type) {
		if(type == SeatType.COUPLE_1_T) {
			return false;
		}
		
		if(type == SeatType.COUPLE_2_T) {
			return false;
		}
		
		if(type == SeatType.SINGLE_T) {
			return false;
		}
		
		if(type == SeatType.GOLD_T) {
			return false;
		}
		
		if(type == SeatType.NOT_EXIST) {
			return false;
		}
		
		return true;
	}
	public static SeatType takenSeat(SeatType type) {
		if(type == SeatType.COUPLE_1) {
			return SeatType.COUPLE_1_T;
		}
		
		if(type == SeatType.COUPLE_2) {
			return SeatType.COUPLE_2_T;
		}
		
		if(type == SeatType.SINGLE) {
			return SeatType.SINGLE_T;
		}
		
		if(type == SeatType.GOLD) {
			return SeatType.GOLD_T;
		}
		
		return null;
	}
		
}