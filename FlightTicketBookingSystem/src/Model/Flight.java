package Model;

import java.util.Arrays;

public class Flight {
	
	private int flightid;
	private String flightname;
	private String FlightNumber;
	
	public Flight(int flightid,String flightname,String FlightNumber) {
		this.flightid = flightid;
		this.flightname = flightname;
		this.FlightNumber = FlightNumber;
	}
	
	public Flight(String flightname,String FlightNumber) {
		this.flightname = flightname;
		this.FlightNumber = FlightNumber;
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

	
	public int getFlightid() {
		return flightid;
	}

	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}

	@Override
	public String toString() {
		return "" + flightname + " - " + FlightNumber ;
				
	}

	@Override
	public boolean equals(Object obj) {
		Flight flight = (Flight) obj;
		return this.getFlightid() == flight.getFlightid();
	}
	
	
	

}
