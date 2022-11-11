package uI;
import java.util.Scanner;

import manager.ReviewMgr;
public class CommentRateApp {
	

	private static void AddComments(Scanner sc) {
		String str;
		System.out.print("Your Comment: ");
		sc.nextLine();
		str = sc.nextLine();
		int rating;
		do {
			System.out.print("Your Rating in 1-5:  ");
			rating = sc.nextInt();
			if(rating>5 || rating <1) {
				System.out.print("Please rate from 1-5 only");
			}
			else {
				break;
			}
		}while(true);
		boolean a = ReviewMgr.createMovieReview(AppState.getUserID(), AppState.getMovieID(),rating, str);
		if(!a) {
			System.out.print("It seems that u have rated this movie before do u wish to update instead?\n");
		}
	}
	
	
	private static void UpdateComment(Scanner sc) {
		String str;
		System.out.print("Your Comment: ");
		sc.nextLine();
		str = sc.nextLine();
		ReviewMgr.updateReviewComment(AppState.getUserID(), AppState.getMovieID(), str);
		int rating;
		do {
			System.out.print("Your Rating in 1-5:  ");
			rating = sc.nextInt();
			if(rating>5 || rating <1) {
				System.out.print("Please rate from 1-5 only");
			}
			else {
				break;
			}
		}while(rating>=1 && rating <=5);
		ReviewMgr.updateReviewRating(AppState.getUserID(), AppState.getMovieID(), rating);

	}
	
	public static void AppMain(Scanner sc) {
		do {
			int choice =-1;
			System.out.print("\n1: Add Comments and Rating\n");
			System.out.print("2: Update Comments and Rating\n");
			System.out.print("0: Back to Movie Details\n");
			System.out.print("Please Choose Your Action: ");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 0:
					return;
				case 1:
					AddComments(sc);
					break;
				case 2:
					UpdateComment(sc);
					break;
				default:
					break;
					
			}
		}while(true);
	}
}
