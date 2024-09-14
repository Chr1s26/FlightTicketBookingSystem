package Model;

public class Flight {
	
	private String flightname;
	private String FlightNumber;
	private Seat[] seat;
	
	public Flight(String flightname,String FlightNumber, Seat[] seat) {
		this.flightname = flightname;
		this.FlightNumber = FlightNumber;
		this.seat = seat;
	}

	public String getFlightname() {
		return flightname;
	}

	public String getFlightNumber() {
		return FlightNumber;
	}
	
	public boolean equalFlight(Flight flight) {
		if(flight == null) {
			return false;
		}
		return this.flightname.equals(flight.getFlightname());
	}
	

}
