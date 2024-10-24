package Model;

public class Seat {
	
	public final static String[] SEAT_TYPES = {"First Class","Economy","Business"};
	private int seatid;
	private String SeatNumber;
	private String seatType = "economy";
	private double seatPrice;
	private boolean isAvailable;
	private Flight flight;
	
	public Seat(int seatid,String seattype,Flight flight,String SeatNumber) {
		this.seatid = seatid;
		this.SeatNumber = SeatNumber;
        this.seatType = seattype;
        this.flight = flight;
    }
	
	public Seat(String seattype,Flight flight,String SeatNumber) {
		this.SeatNumber = SeatNumber;
        this.seatType = seattype;
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
	
	public double calculatePrice() {
		if(this.isFirstClass()) {
			return 500;
		}else if(this.isBusiness()) {
			return 300;
		}else if(this.isEconomy()) {
			return 100;
		}else {
			return 0;
		}
	}

	private boolean isEconomy() {
		return this.seatType.equalsIgnoreCase("economy");
	}

	private boolean isBusiness() {
		return this.seatType.equalsIgnoreCase("business");
	}

	private boolean isFirstClass() {
		return this.seatType.equalsIgnoreCase("first class");
	}

	@Override
	public String toString() {
		return "SeatId=" + seatid + ", seatType=" + seatType 
				+ ", isAvailable=" + isAvailable+"Flight number is "+this.flight+"SeatNumber="+SeatNumber;
	}
	
	public String[] toArray() {
		String[] dataArr = new String[4];
		dataArr[0] = this.getSeatid()+"";
		dataArr[1] = this.getSeatNumber();
		dataArr[2] = this.getSeatType();
		dataArr[3] = this.calculatePrice()+"";
		return dataArr;
		
	}
	

}
