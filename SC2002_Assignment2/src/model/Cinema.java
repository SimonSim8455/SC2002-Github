package model;


public class Cinema {
 
    private int cinemaID;
    private int cineplexID;
    private CinemaType cinemaType;
    private SeatType[][] seatPlan;
    
    public Cinema(int cinemaID, int cineplexID, CinemaType type, SeatType[][] seatPlan) {
    	this.cinemaID = cinemaID;
    	this.cineplexID = cineplexID;
    	this.cinemaType = type;
    	this.seatPlan = seatPlan;
    }
    
    public static Cinema copy(Cinema another) {
    	Cinema cinema = new Cinema(
    			another.getCinemaID(),
    			another.getCineplexID(),
    			another.getCinemaType(),
    			another.getSeatPlan()
    	);
    	return cinema;
    }
    public void setCinemaType(CinemaType type) {
    	this.cinemaType = type;
    }
    
    public CinemaType getCinemaType() {
    	return this.cinemaType;
    }
    public SeatType[][] getSeatPlan(){
    	return this.seatPlan;
    }
    
    public void setSeatPlan(SeatType[][] seatPlan) {
    	this.seatPlan = seatPlan;
    }
    public void setCineplexID(int id) {
    	this.cineplexID =id;
    }
    
    public int getCineplexID() {
    	return this.cineplexID;
    }
    
    public int getCinemaID() {
    	return this.cinemaID;
    }
    
    public void setCinemaID(int id) {
    	this.cinemaID = id;
    }

}
