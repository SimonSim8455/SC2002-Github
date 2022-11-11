package manager;

import database.Data;
import model.TicketPrice;

public class TicketPriceMgr {
	private static TicketPrice ticketPrice = Data.ticketPrice;
	
	public static TicketPrice getTicketPriceCopy() {
		return TicketPrice.copy(ticketPrice);
	}
	
	public static boolean updateTicketPrice(int methodCode, double num) {
		switch(methodCode) {
			case 0:
				ticketPrice.setBasePrice(num);
				break;
			case 1:
				ticketPrice.setWeekendPrice(num);
				break;
			case 2:
				ticketPrice.setStudentDiscount(num);
				break;
			case 3:
				ticketPrice.setElderlyDiscount(num);
				break;
			case 4:
				ticketPrice.setGoldClassTicket(num);
				break;
			case 5:
				ticketPrice.setCoupleClassTicket(num);;
				break;
			case 6:
				ticketPrice.setMovie2DPrice(num);
				break;
			case 7:
				ticketPrice.setMovie3DPrice(num);
				break;
			case 8:
				ticketPrice.setMovieIMAXPrice(num);
				break;
			case 9:
				ticketPrice.setPlatiniumPrice(num);
				break;
			default:
				return false;
		}
		return true;
	}
}
