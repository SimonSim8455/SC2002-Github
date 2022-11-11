package manager;

import java.util.ArrayList;
import java.util.HashMap;
import database.Data;
import model.Movie;
import model.MovieStates;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class MovieMgr {
	private static HashMap<Integer,Movie> movieList =  Data.movieList;
	
	
	public static int createMovie(String title, String director, 
			ArrayList<String> casts, String movieContent, double duration, MovieStates state, 
			int numRaters, double overallRating, double sales) {
		
		if(Validator.validateMovie(title) != -1) {
			return -1;
		}
		int movieID = Helper.getUniqueId(movieList);
		Movie buffer = new Movie(movieID,title,director,casts,movieContent,duration,state);
		movieList.put(movieID, buffer);
		MovieRankMgr.createMovieRank(movieID, numRaters, overallRating, sales);
		return movieID;
		
	}
	
	public static int createMovie(String title, String director, 
			ArrayList<String> casts, String movieContent, double duration, MovieStates state) {
		
		if(Validator.validateMovie(title) != -1) {
			return -1;
		}
		int movieID = Helper.getUniqueId(movieList);
		Movie buffer = new Movie(movieID,title,director,casts,movieContent,duration,state);
		movieList.put(movieID, buffer);
		MovieRankMgr.createMovieRank(movieID);
		return movieID;
		
	}
	
	public static boolean removeMovie(int movieID) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie movie = SearchUtils.searchMovie(movieID);
		movie.setMovieState(MovieStates.NO_LONGER_SHOWING);
		return true;
	}
		
	public static boolean updateMovieContent(int movieID, String text) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		updateMovie.setMovieContent(text);
		return true;
	}
	
	
	public static boolean updateMovieDirector(int movieID, String text) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		updateMovie.setDirector(text);
		return true;
	}
	
	public static boolean updateMovieTitle(int movieID, String title) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		updateMovie.setTitle(title);
		return true;
	}
	
	public static boolean updateMovieState(int movieID,MovieStates state) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		updateMovie.setMovieState(state);
		return true;
		
	}
	
	public static boolean addCasts(int movieID, String cast) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		ArrayList<String> casts = updateMovie.getCasts();
		for(int i=0;i<casts.size();i++) {
			if(casts.get(i).equals(cast)) {
				return false;
			}
		}
		casts.add(cast);
		return true;
	}
	
	public static boolean removeCasts(int movieID, String cast) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		ArrayList<String> casts = updateMovie.getCasts();
		int index= -1;
		for(int i=0;i<casts.size();i++) {
			if(casts.get(i).equals(cast)) {
				index = i;
				break;
			}
		}
		if(index == -1) {
			return false;
		}
		casts.remove(index);
		return true;
	}
	
	public static boolean updateMovieDuration(int movieID, double duration) {
		if(Validator.validateMovie(movieID) == -1) {
			return false;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		updateMovie.setDuration(duration);
		return true;
	}
	

	public static Movie getMovieByID(int movieID) {
		if(Validator.validateMovie(movieID) == -1) {
			return null;
		}
		Movie updateMovie = SearchUtils.searchMovie(movieID);
		return Movie.copy(updateMovie);
	}
	
	public static Movie getMovieByName(String name) {
		Movie updateMovie = SearchUtils.searchMovie(name);
		if(updateMovie == null) {
			return null;
		}
		return Movie.copy(updateMovie);
	}
	
	public static ArrayList<Movie> getAllMovieList() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		for(Movie movie: movieList.values()) {
			Movie buffer = Movie.copy(movie);
			list.add(buffer);
		}
		return list;
	}
	
	public static ArrayList<String> getCastsByMovieID(int movieID){
		if(Validator.validateMovie(movieID) == -1) {
			return null;
		}
		Movie movie = SearchUtils.searchMovie(movieID);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> casts = movie.getCasts();
		for(int i=0;i<casts.size();i++) {
			list.add(casts.get(i));
		}
		return list;
	}
}
