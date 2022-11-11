package utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtils {
	private int hour;
    private int minute;
    
    public TimeUtils(int hour, int minute) {
    	this.hour= hour;
    	this.minute = minute;
    }
    public static boolean equal(TimeUtils time1, TimeUtils time2) {
    	boolean eHour = time1.getHour() != time2.getHour();
    	boolean eMinute = time1.getMinute() != time2.getMinute();
    	if(eHour || eMinute) {
    		return false;
    	}
    	return true;
    }
    
    public static TimeUtils LocalTimeToTimeUtils(LocalTime timeNow) {
    	int hour = Integer.parseInt(timeNow.toString().substring(0, 2));
        int minute = Integer.parseInt(timeNow.toString().substring(3, 5));
        TimeUtils time = new TimeUtils(hour, minute);
        return time;
  
    }
    
    public static void print(TimeUtils time) {
    	System.out.print(time.getHour()+":"+time.getMinute());
    }
    
    public int getHour(){
        return this.hour;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }
}
