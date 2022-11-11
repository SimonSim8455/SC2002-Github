package uI;

import java.util.Scanner;

public class CinemaStaffLogin {
	
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
		System.out.print("Username: ");
		sc.nextLine();
		str1 = sc.nextLine();
		System.out.print("Password: ");
		str2= sc.nextLine();
		
		if(ValidateLogin(str1,str2)) {
			return true;
		}
		return false;
	}
	
	public static void AppMain(Scanner sc) {
		boolean a = Login(sc);
		if(!a) {
			return;
		}
		else {
			StaffConfiguration.AppMain(sc);
		}
	}
}
