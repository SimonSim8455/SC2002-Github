package uI;

import java.util.*;
import manager.*;
import utils.*;
import model.*;

public class SystemConfig {
	
	public static void AppMain(Scanner sc) {
		while(true) {
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("              System Config               \n");
			System.out.print("========================================\n");
			System.out.print("1) Edit TicketPrice\n");
			System.out.print("2) Edit Holiday\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			num = sc.nextInt();
			
			switch(num) {
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
	
	public static void EditTicketPrice(Scanner sc) {
		while(true) {
			System.out.print("\n========================================\n");
			System.out.print("           Ticket Price Config            \n");
			System.out.print("========================================\n");
			System.out.print("1) Edit Base Price\n");
			System.out.print("2) Edit Weekend Price\n");
			System.out.print("3) Edit Student Discount\n");
			System.out.print("4) Edit Elderly Discount\n");
			System.out.print("5) Edit GoldClassTicket\n");
			System.out.print("6) Edit CoupleClassTicket\n");
			System.out.print("7) Edit Movie2D Price\n");
			System.out.print("8) Edit Movie3D Price\n");
			System.out.print("9) Edit MovieIMAX Price\n");
			System.out.print("10) Edit MoviePlatinium Price\n");
			System.out.print("11) Edit Holiday Price\n");
			System.out.print("12) ViewTicketPrice\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
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
					System.out.print("Enter New Price: ");
					price  = sc.nextDouble();
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
			System.out.print("\n========================================\n");
			System.out.print("             Holiday Config               \n");
			System.out.print("========================================\n");
			System.out.print("1) Update Holiday Name\n");
			System.out.print("2) Update Holiday Date\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			num = sc.nextInt();
			
			switch(num) {
				case 0:
					return;
				case 1:
					System.out.print("Enter holiday name");
					String name = sc.nextLine();
					HolidayMgr.updateHolidayName(holidayID, name);
					System.out.println("Success\n");
				case 2:
					System.out.print("Enter holiday date:\n");
					DateUtils date = DateUtils.promptInput(sc);
					HolidayMgr.updateHolidayDate(holidayID, date);
					System.out.println("Success\n");
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
