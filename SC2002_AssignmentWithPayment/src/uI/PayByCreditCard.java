package uI;

import java.util.Scanner;

public class PayByCreditCard extends PaymentMethod{
	
	public PayByCreditCard(double price) {
		super(price);
	}
	
	public boolean promptInput(Scanner sc) {
		try {
			
			int number;
			String name;
			System.out.println("Total Price: "+super.price);
			System.out.print("Please Enter Card Number: ");
			number = sc.nextInt();
			sc.nextLine();
			System.out.print("Please Enter Name on Card:");
			name = sc.nextLine();
			System.out.print("Please Enter CVV: ");
			number = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			System.out.print("Payment Fail");
			return false;
		}
		OnSuccessPayment();
		return true;
	}
	
	public void OnSuccessPayment() {
		System.out.println("Payment Successfull");
		System.out.println("Receipt will be send to you by email. Thank You");
	}
}
