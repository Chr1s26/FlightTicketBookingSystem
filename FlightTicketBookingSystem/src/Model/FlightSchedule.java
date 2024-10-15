package Model;



public class FlightSchedule {
	
	private int scheduleid;
	private Flight flight;
	private Route route;
	private String deptTime;
	private String arrivalTime;
	private String createdAt;
	
	public FlightSchedule(int scheduleid,Flight flight,Route route,String deptTime,String arrivalTime,String createdAt) {
		this.scheduleid = scheduleid;
		this.flight = flight;
		this.route = route;
		this.deptTime = deptTime;
		this.arrivalTime = arrivalTime;
		this.createdAt = createdAt;
	}
	
	
	public FlightSchedule(Flight flight,Route route,String deptTime,String arrivalTime,String createdAt) {
		this.flight = flight;
		this.route = route;
		this.deptTime = deptTime;
		this.arrivalTime = arrivalTime;
		this.createdAt = createdAt;
	}

	public Flight getFlight() {
		return flight;
	}

	public int getScheduleid() {
		return scheduleid;
	}


	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}


	public Route getRoute() {
		return route;
	}


	public void setRoute(Route route) {
		this.route = route;
	}


	public String getDeptTime() {
		return deptTime;
	}


	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	@Override
	public String toString() {
		return  "\n\nScheduleid= "+scheduleid+","+flight + route + "\nDepature time =" + deptTime + ", Arrival time=" + arrivalTime +"Created At "+createdAt;
	}

	public String[] toArray(){
		String[] scheduleTabelRow = new String[8];
		scheduleTabelRow[0] = this.scheduleid+"";
		scheduleTabelRow[1] = this.getFlight().getFlightname();
		scheduleTabelRow[2] = this.getFlight().getFlightNumber();
		scheduleTabelRow[3] = this.getRoute().getDepatureAirport().getName();
		scheduleTabelRow[4] = this.getRoute().getArrivalAirport().getName();
		scheduleTabelRow[5] = this.getDeptTime();
		scheduleTabelRow[6] = this.getArrivalTime();
		scheduleTabelRow[7] = this.getCreatedAt();
		return scheduleTabelRow;
	}
}
