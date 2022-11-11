package uI;

import java.util.*;
import manager.*;
import utils.*;
import model.*;

public class SystemConfig {
	public static int PromptInput(Scanner sc) {
		int num =-1;
		System.out.print("\n1: Edit TicketPrice\n");
		System.out.print("2: Edit Holiday\n");
		System.out.print("0: Go Back\n");
		System.out.print("Please Choose Your Action: ");
		num = sc.nextInt();
		return num;
	}
	
	public static void AppMain(Scanner sc) {
		while(true) {
			int a = PromptInput(sc);
			switch(a) {
				case 0:
					return;
				case 1: 
					EditTicketPrice(sc);
					break;
				case 2:
					EditHoliday(sc);
					break;
				default:
					break;
					
					
			}
		}
	}
	
	public static double promptPriceInput(Scanner sc) {
		System.out.print("New Price: ");
		double price  = sc.nextDouble();
		return price;
	}
	
	public static void EditTicketPrice(Scanner sc) {
		while(true) {
			
			System.out.print("\n1: Edit BasePrice\n");
			System.out.print("2: Edit WeekendPrice\n");
			System.out.print("3: Edit StudentDiscount\n");
			System.out.print("4: Edit ElderlyDiscount\n");
			System.out.print("5: Edit GoldClassTicket\n");
			System.out.print("6: Edit CoupleClassTicket\n");
			System.out.print("7: Edit Movie2DPrice\n");
			System.out.print("8: Edit Movie3DPrice\n");
			System.out.print("9: Edit MovieIMAXPrice\n");
			System.out.print("10: Edit MoviePlatiniumPrice\n");
			System.out.print("11: ViewTicketPrice\n");
			System.out.print("0: Go Back\n");
			System.out.print("Please choose your action: ");
			int choice= sc.nextInt();
			double price;
			
			while(true) {
				if(choice ==0) {
					return;
				}
				if(choice == 11) {
					Printer.displayTicketPrices();
					System.out.println();
					break;
				}
				if(choice>=1 && choice<=10) {
					price = promptPriceInput(sc);
					TicketPriceMgr.updateTicketPrice(choice-1, price);
					System.out.print("New Price Updated\n");
					break;
				}
			}
		}
	
	}
	
	public static void updateHoliday(Scanner sc, int holidayID) {
		while(true) {
			
			int num =-1;
			System.out.print("\n1: Update Holiday Name\n");
			System.out.print("2: Update Holiday Date\n");
			System.out.print("0: Go Back\n");
			System.out.print("Please Choose Your Action: ");
			num = sc.nextInt();
			
			switch(num) {
				case 0:
					return;
				case 1:
					System.out.print("Enter holiday name");
					String name = sc.nextLine();
					HolidayMgr.updateHolidayName(holidayID, name);
					System.out.print("Success\n");
				case 2:
					System.out.print("Enter holiday date:\n");
					DateUtils date = DateUtils.promptInput(sc);
					HolidayMgr.updateHolidayDate(holidayID, date);
					System.out.print("Success\n");
				default:
					break;
			}
		}
		
		
	}
	
	public static void AddHoliday(Scanner sc) {
		String name;
		System.out.print("Enter holiday name");
		name = sc.nextLine();
		
		System.out.print("Enter holiday date:\n");
		DateUtils date = DateUtils.promptInput(sc);
		
		int index = HolidayMgr.createHoliday(name, date);
		if(index == -1) {
			System.out.print("Holiday already existed");
		}
		else {
			System.out.print("Holiday created");
		}
		
	}
	
	
	public static void EditHoliday(Scanner sc) {
		while(true) {
			
			int num =-1;
			System.out.print("\n1: View Holiday List\n");
			System.out.print("2: Add Holiday\n");
			System.out.print("3: Edit Holiday\n");
			System.out.print("0: Go Back\n");
			System.out.print("Please Choose Your Action: ");
			num = sc.nextInt();
			
			
			switch(num) {
				case 0:
					return;
				case 1:
					Printer.displayHolidayList();
					break;
				case 2:
					AddHoliday(sc);
					break;
				case 3:
					System.out.print("Enter holiday ID");
					int id = sc.nextInt();
					Holiday holiday = HolidayMgr.getHoliday(id);
					if(holiday == null) {
						System.out.print("Holiday not exist\n");
						break;
					}
					updateHoliday(sc,id);
					break;
				default:
					break;
				
			}
		}
	}
}
