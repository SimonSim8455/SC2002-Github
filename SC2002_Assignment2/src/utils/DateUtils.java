package utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class DateUtils implements Serializable{
    private int day;
    private int month;
    private int year;
    
    public DateUtils(int day, int month, int year) {
    	this.day = day;
    	this.month =month;
    	this.year = year;
    }
    public static boolean equal(DateUtils date1, DateUtils date2) {
    	boolean a = date1.getDay() != date2.getDay();
    	boolean b = date1.getMonth() != date2.getMonth();
    	boolean c = date1.getYear() != date2.getYear();
    	if(a|| b|| c) {
    		return false;
    	}
    	return true;
    
    }
    
    public static DateUtils LocalDateToDateUtils(LocalDate today) {
    	int year = Integer.parseInt(today.toString().substring(0, 4));
        int month = Integer.parseInt(today.toString().substring(5, 7));
        int day = Integer.parseInt(today.toString().substring(8, 10));
        DateUtils date = new DateUtils(day,month,year);
        return date;
  
    }
    
    public static void print(DateUtils date) {
    	String day = String.valueOf(date.getDay());
    	String year = String.valueOf(date.getYear());
    	String month = String.valueOf(date.getMonth());

    	if(date.getMonth() <10) {
    		month = "0" + String.valueOf(date.getMonth());
    	}
    	
    	if(date.getDay() <10) {
    		day = "0" + String.valueOf(date.getDay());
    	}
    	System.out.print(day+"/"+month+"/"+ year);
    }
    
    public static DateUtils promptInput(Scanner sc) {
    	System.out.print("Enter day: ");
    	int day = sc.nextInt();
    	System.out.print("Enter month: ");
    	int month = sc.nextInt();
    	System.out.print("Enter year: ");
    	int year = sc.nextInt();
    	sc.nextLine();
    	DateUtils date = new DateUtils(day, month, year);
    	return date;
    }
    public int getDay(){
        return this.day;
    }

    public void setDay(int d){
        this.day  = d;
    }

    public int getMonth(){
        return this.month;
    }

    public void setMonth(int d){
        this.month  = d;
    }

    public int getYear(){
        return this.year;
    }

    public void setYear(int d){
        this.year  = d;
    }
}
