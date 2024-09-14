package Model;



public class FlightSchedule {

	private Flight flight;
	private Route route;
	private String date;
	private String time;
	
	public FlightSchedule(Flight flight,Route route,String date,String time) {
		this.flight = flight;
		this.route = route;
		this.date = date;
		this.time = time;
	}

	public Flight getFlight() {
		return flight;
	}
	
}
