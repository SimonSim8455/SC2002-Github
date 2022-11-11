package model;

public class TicketPrice {
    private double basePrice;
    private double weekendPrice;
    private double studentDiscount;
    private double elderlyDiscount;
    private double goldClassTicket;
    private double coupleTicket;
    private double movie2DPrice;
    private double movie3DPrice;
    private double movieIMAXPrice;
    private double platiniumPrice;
    private double holidayPrice;
    
    public TicketPrice(double basePrice,double weekendPrice,double studentDiscount,double elderlyDiscount,double goldClassTicket,
    		double coupleTicket,double movie2DPrice,double movie3DPrice,double movieIMAXPrice,double platiniumPrice, double holidayPrice) {
        this.basePrice = basePrice ;
        this.weekendPrice =weekendPrice;
        this.studentDiscount=studentDiscount;
        this.elderlyDiscount=elderlyDiscount;
        this.goldClassTicket=goldClassTicket;
        this.coupleTicket=coupleTicket;
        this.movie2DPrice=movie2DPrice;
        this.movie3DPrice=movie3DPrice;
        this.movieIMAXPrice=movieIMAXPrice;
        this.platiniumPrice = platiniumPrice;
        this.holidayPrice = holidayPrice;
    }
    
    public TicketPrice() {};
    
    public static TicketPrice copy(TicketPrice another) {
    	TicketPrice tp = new TicketPrice(
    			another.getBasePrice(),
    			another.getWeekendPrice(),
    			another.getStudentDiscount(),
    			another.getElderlyDiscount(),
    			another.getGoldClassTicket(),
    			another.getCoupleClassTicket(),
    			another.getMovie2DPrice(),
    			another.getMovie3DPrice(),
    			another.getMovieIMAXPrice(),
    			another.getPlatiniumPrice(),
    			another.getHolidayPrice()
    	);
    	return tp;
    }
    
    public void setHolidayPrice(double price) {
    	this.holidayPrice = price;
    }
    public double getHolidayPrice() {
    	return this.holidayPrice;
    }
    public void setPlatiniumPrice(double price) {
    	this.platiniumPrice = price;
    }
    public double getPlatiniumPrice() {
    	return this.platiniumPrice;
    }
    public double getBasePrice(){
        return this.basePrice;
    }

    public void setBasePrice(double price){
        this.basePrice = price;
    }

    public double getWeekendPrice(){
        return this.weekendPrice;
    }

    public void setWeekendPrice(double price){
        this.weekendPrice= price;
    }

    public double getStudentDiscount(){
        return this.studentDiscount;
    }

    public void setStudentDiscount(double price){
        this.studentDiscount= price;
    }

    public double getElderlyDiscount(){
        return this.elderlyDiscount;
    }

    public void setElderlyDiscount(double price){
        this.elderlyDiscount= price;
    }

    public double getGoldClassTicket(){
        return this.goldClassTicket;
    }

    public void setGoldClassTicket(double price){
        this.goldClassTicket= price;
    }

    public double getCoupleClassTicket(){
        return this.coupleTicket;
    }

    public void setCoupleClassTicket(double price){
        this.coupleTicket= price;
    }

    public double getMovie2DPrice(){
        return this.movie2DPrice;
    }

    public void setMovie2DPrice(double price) {
    	this.movie2DPrice = price;
    }
    
    public double getMovie3DPrice(){
        return this.movie3DPrice;
    }

    public void setMovie3DPrice(double price) {
    	this.movie3DPrice = price;
    }
    
    public double getMovieIMAXPrice(){
        return this.movieIMAXPrice;
    }

    public void setMovieIMAXPrice(double price) {
    	this.movieIMAXPrice = price;
    }
}
