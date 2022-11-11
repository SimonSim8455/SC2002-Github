package uI;
import java.util.*;

import manager.UserAccountMgr;
public class UserAccountApp {
	public static int LoginSignUp(Scanner sc) {
		int num =-1;
		System.out.print("\n1: Login\n");
		System.out.print("2: SignUp\n");
		System.out.print("0: Back to Main Menu\n");
		System.out.print("Please Choose Your Action: ");
		num = sc.nextInt();
		return num;
	}
	
	public static void AppMain(Scanner sc) {
		int a;
		boolean b =false;
		do {
			a = LoginSignUp(sc);
			switch(a) {
				case 0:
					return;
				case 1:
					b = displayLogin(sc);
					if(b) {
						return;
					}
					break;
				case 2:
					displaySignUp(sc);
				default:
					break;
			}
		}while(true);
	}
	
	
	public static boolean displayLogin(Scanner sc) {
		String str, str2;
		System.out.print("\n---------------- Login----------------------\n");
		System.out.print("Username: ");
		sc.nextLine();
		str = sc.nextLine();
		System.out.print("Password: ");
		str2 = sc.nextLine();
		int userID =UserAccountMgr.validateUserLogin(str, str2);
		if(userID <0) {
			System.out.print("Login Failed, Please Try Again\n\n");
			return false;
		}
		else {
			AppState.setUserID(userID);
			System.out.print("Log in Sucess\n\n");
			return true;
		}
	}
	
	public static boolean displaySignUp(Scanner sc) {
		//String username,String password,String mobileNumber,String email,int age
		String str, str2,str3,str4;
		int age;
		System.out.print("\n---------------- Sign Up----------------------\n");
		System.out.print("Username: ");
		sc.nextLine();
		str = sc.nextLine();
		System.out.print("Password: ");
		str2 = sc.nextLine();
		System.out.print("MobileNumber: ");
		str3 = sc.nextLine();
		System.out.print("Email: ");
		str4 = sc.nextLine();
		System.out.print("Age: ");
		age = sc.nextInt();
		boolean a =UserAccountMgr.createUserAccount(str, str2, str3, str4, age);
		if(!a) {
			System.out.print("Sign Up Failed, Please Try Again\n\n");
			return false;
		}
		else {
			System.out.print("Sign Up Sucess, Please try Login\n\n");
			return true;
		}
	}
}
