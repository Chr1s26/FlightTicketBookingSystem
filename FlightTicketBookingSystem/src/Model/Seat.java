package Model;

public class Seat {
	
	private int seatid;
	private String SeatNumber;
	private String seatType;
	private double seatPrice;
	private boolean isAvailable;
	private Flight flight;
	
	public Seat(int seatid,String seattype,Flight flight,String SeatNumber) {
		this.seatid = seatid;
		this.SeatNumber = SeatNumber;
        this.seatType = seattype;
        this.flight = flight;
    }
	
	public Seat(String SeatNumber,String seattype,boolean isAvailable,Flight flight) {
		this.SeatNumber = SeatNumber;
        this.seatType = seattype;
        this.isAvailable = isAvailable;
        this.flight = flight;
    }
	

	public String getSeatNumber() {
		return SeatNumber;
	}

	public double getSeatPrice() {
		return seatPrice;
	}

	public int getSeatid() {
		return seatid;
	}

	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}

	@Override
	public String toString() {
		return "SeatNumber=" + SeatNumber + ", seatType=" + seatType 
				+ ", isAvailable=" + isAvailable+"Flight number is "+this.flight;
	}
	
	

}
