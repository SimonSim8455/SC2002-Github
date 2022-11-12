package utils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;
import database.Data;
import model.*;

public class PriceCalculator {
	public static double calculate(CinemaType cinemaType, SeatType seatType, int age, DateUtils showDate, MovieType movieType) {
		TicketPrice tp = Data.ticketPrice;
		double basePrice = tp.getBasePrice();
		
		HashMap<Integer,Holiday> holidayList = Data.holidayList;
		for(Holiday holiday: holidayList.values()) {
			if(DateUtils.equal(holiday.getHolidayDate(), showDate)) {
				basePrice = basePrice +tp.getHolidayPrice();
			}
		}
		
		LocalDate someDate = LocalDate.of(showDate.getYear(), showDate.getMonth(), showDate.getDay()); 
		if(isWeekend(someDate)){
			basePrice = basePrice +tp.getWeekendPrice();		
		}
		
		
		if(seatType == SeatType.COUPLE_1_T) {
			basePrice = basePrice + tp.getCoupleClassTicket();
		}
		
		if(seatType == SeatType.COUPLE_2_T) {
			basePrice = basePrice + tp.getCoupleClassTicket();
		}
		
		if(movieType == MovieType.TWOD) {
			basePrice = basePrice +tp.getMovie2DPrice();
		}
		
		if(movieType == MovieType.THREED) {
			basePrice = basePrice +tp.getMovie3DPrice();
		}
		
		if(movieType == MovieType.IMAX) {
			basePrice = basePrice +tp.getMovieIMAXPrice();
		}
		
		
		if(cinemaType == CinemaType.PLATINIUM) {
			basePrice = basePrice + tp.getPlatiniumClassPrice();
		}
		
		if(cinemaType == CinemaType.GOLD) {
			basePrice = basePrice + tp.getGoldClassTicket();
		}
		
		if(age<=12 && age>=6) {
			basePrice = basePrice - tp.getStudentDiscount();
		}
		
		if(age>=65) {
			basePrice = basePrice - tp.getElderlyDiscount();
		}
		
		return basePrice;
	
	}
	
	 private static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }
}
