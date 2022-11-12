package uI;

import java.util.Scanner;

public class PayByQRCode extends PaymentMethod{
	public PayByQRCode(double price) {
		super(price);
	}
	
	public boolean promptInput(Scanner sc) {
		System.out.println("Total Price: "+super.price);
		System.out.print("Please Scan Below QRCode for Payment\n");
		System.out.print(""+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||     |||||||||     |||||||||||||||||\n"+
			"|||||  ||||||  |||||||  ||||  ||||||||||||||\n"+
			"|||||  ||||||  |||||||  ||||  ||||||||||||||\n"+
			"|||||  ||||||  |||||||  ||||  ||||||||||||||\n"+
			"|||||  ||||||  |||||||  |  |||||||||||||||||\n"+
			"|||||  ||||    |||||||  |||  |||||||||||||||\n"+
			"||||||||     ||  |||||  |||||  |||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"+
			"||||||||||||||||||||||||||||||||||||||||||||\n"
		);
		System.out.print("Please Enter Any Number to continue: ");
		int buffer = sc.nextInt();
		sc.nextLine();
		OnSuccessPayment();
		return true;
	}
	
	public void OnSuccessPayment() {
		System.out.println("Payment Successfull");
		System.out.println("Receipt will be send to you by email. Thank You");
	}
}
