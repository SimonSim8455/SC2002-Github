package manager;

import java.util.HashMap;

import database.Data;
import model.Cineplex;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class CineplexMgr {
	private static HashMap<Integer,Cineplex> cineplexList =  Data.cineplexList;
	
	public static HashMap<Integer,Cineplex> getCineplexList(){
		return cineplexList;
	}
	
	public static boolean createCineplex(String name) {
		if(Validator.validateCineplex(name)!= -1) {
			return false;
		}
		int cineplexID = Helper.getUniqueId(cineplexList);
		Cineplex newCineplex = new Cineplex(cineplexID, name);
		cineplexList.put(cineplexID, newCineplex);
		return true;
	}
	
	public static Cineplex getCineplexByID(int cineplexID) {
		if(Validator.validateCineplex(cineplexID)!= 1) {
			return null;
		}
		Cineplex buffer = SearchUtils.searchCineplex(cineplexID);
		return Cineplex.copy(buffer);
	}
	
	public static Cineplex getCineplexByName(String name) {
		if(Validator.validateCineplex(name) != 1) {
			return null;
		}
		for(Cineplex buffer : cineplexList.values()) {
			if(buffer.getName().equals(name)) {
				return Cineplex.copy(buffer);
			}
		}
		return null;
	}

}
