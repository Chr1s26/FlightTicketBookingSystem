package Model;

public class Seat {

	private String SeatNumber;
	private String seatType;
	private double seatPrice;
	private boolean isAvailable;
	
	public Seat(String seatNumber, String seattype,double seatPrice ,boolean isAvailable) {
        this.SeatNumber = seatNumber;
        this.seatType = seattype;
        this.seatPrice = seatPrice;
        this.isAvailable = isAvailable;
    }

	public String getSeatNumber() {
		return SeatNumber;
	}

	public double getSeatPrice() {
		return seatPrice;
	}
	
	

}
