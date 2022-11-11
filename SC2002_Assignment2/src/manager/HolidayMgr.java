package manager;

import java.util.ArrayList;
import java.util.HashMap;

import database.Data;
import model.Holiday;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class HolidayMgr {
	private static HashMap<Integer,Holiday> holidayList = Data.holidayList;
	
	public static ArrayList<Holiday> getAllHolidayList(){
		ArrayList<Holiday> list = new ArrayList<Holiday>();
		for(Holiday holiday : holidayList.values()) {
			list.add(Holiday.copy(holiday));
		}
		return list;
	}
	
	public static int createHoliday(String name, DateUtils date) {
		if(Validator.validateHoliday(name,date)==1) {
			return -1;
		}
		int holidayId = Helper.getUniqueId(holidayList);
		Holiday holiday = new Holiday(holidayId,name,date);
		holidayList.put(holidayId, holiday);
		return holidayId;
	}
	
	public static boolean removeHoliday(int holidayId) {
		if(Validator.validateHoliday(holidayId)!=1) {
			return false;
		}
		holidayList.remove(holidayId);
		return true;
	}

		
	public static boolean removeHoliday(String name, DateUtils date) {
		if(Validator.validateHoliday(name,date)!=1) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(name, date);
		int holidayId = holiday.getHolidayID();
		holidayList.remove(holidayId);
		return true;
	}
	
	public static boolean updateHolidayName(String name, DateUtils date, String newName) {
		if(Validator.validateHoliday(name,date)!=1) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(name, date);
		holiday.setHolidayName(newName);
		return true;
		
	}
	
	public static boolean updateHolidayName(int holidayID, String newName) {
		if(Validator.validateHoliday(holidayID)!=1) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(holidayID);
		holiday.setHolidayName(newName);
		return true;
		
	}
	
	public static boolean updateHolidayDate(String name, DateUtils date, DateUtils newDate) {
		if(Validator.validateHoliday(name,date)!=1) {
			return false;
		}
		Holiday updateHoliday = SearchUtils.searchHoliday(name, date);
		updateHoliday.setHolidayDate(newDate);
		return true;
		
	}
	
	
	public static boolean updateHolidayDate(int holidayID, DateUtils newDate) {
		if(Validator.validateHoliday(holidayID)!=1) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(holidayID);
		holiday.setHolidayDate(newDate);
		return true;
		
	}
	
	public static Holiday getHoliday(int holidayID) {
		if(Validator.validateHoliday(holidayID)!=1) {
			return null;
		}
		Holiday holiday = SearchUtils.searchHoliday(holidayID);
		return Holiday.copy(holiday);
	}
	
	
	public static Holiday getHoliday(String name, DateUtils date) {
		if(Validator.validateHoliday(name,date)!=1) {
			return null;
		}
		Holiday holiday = SearchUtils.searchHoliday(name, date);
		return Holiday.copy(holiday);
	}

	
}
