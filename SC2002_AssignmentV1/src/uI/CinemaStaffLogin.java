package uI;

import java.util.Scanner;

public class CinemaStaffLogin {
	
	public static void AppMain(Scanner sc) {
		boolean a = Login(sc);
		if(!a) {
			return;
		}
		else {
			StaffConfiguration(sc);
			return;
		}
	}
	
	public static void StaffConfiguration(Scanner sc) {
		while(true) {
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("             Staff Main Menu              \n");
			System.out.print("========================================\n");
			System.out.print("1) Edit Movie Listing\n");
			System.out.print("2) Edit Showtime List \n");
			System.out.print("3) Edit System Setting\n");
			System.out.println("0) Log Out\n");
			System.out.print("Enter Your Choice: ");
			num = sc.nextInt();
			switch(num) {
			case 0:
				return;
				case 1: 
					MovieConfig.AppMain(sc);
					break;
				case 2:
					ShowTimeConfig.AppMain(sc);
					break;
				case 3:
					SystemConfig.AppMain(sc);
					break;
				default:
					break;
			}
		}
	}
	
	public static boolean ValidateLogin(String text1, String text2) {
		String username = "Test2";
		String password = "1234";
		if(!text1.equals(username) || !text2.equals(password)) {
			System.out.print("Wrong Credentials\n");
			return false;
		}
		return true;
	}
	
	public static boolean Login(Scanner sc) {
		String str1,str2;
		System.out.print("\nEnter Username: ");
		sc.nextLine();
		str1 = sc.nextLine();
		System.out.print("Enter Password: ");
		str2= sc.nextLine();
		
		if(ValidateLogin(str1,str2)) {
			return true;
		}
		return false;
	}
	
}
