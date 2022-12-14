package manager;

import java.util.ArrayList;
import java.util.HashMap;

import database.Data;
import database.FileType;
import model.Cineplex;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class CineplexMgr {
	
	
	public static boolean createCineplex(String name) {
		if(Validator.validateCineplex(name)  == true) {
			return false;
		}
		int cineplexID = Helper.getUniqueId(Data.cineplexList);
		Cineplex newCineplex = new Cineplex(cineplexID, name);
		Data.cineplexList.put(cineplexID, newCineplex);
		Data.saveFile(FileType.CINEPLEX);
		return true;
	}
	
	public static Cineplex getCineplexByID(int cineplexID) {
		if(Validator.validateCineplex(cineplexID) == false) {
			return null;
		}
		Cineplex buffer = SearchUtils.searchCineplex(cineplexID);
		return Cineplex.copy(buffer);
	}
	
	public static Cineplex getCineplexByName(String name) {
		if(Validator.validateCineplex(name) ==false ) {
			return null;
		}
		for(Cineplex buffer : Data.cineplexList.values()) {
			if(buffer.getName().equals(name)) {
				return Cineplex.copy(buffer);
			}
		}
		return null;
	}
	
	public static ArrayList<Cineplex> getCineplexList(){
		ArrayList<Cineplex> list = new ArrayList<Cineplex>();
		for(Cineplex buffer :Data.cineplexList.values()) {
			list.add(Cineplex.copy(buffer));
		}
		return list;
	}
}
