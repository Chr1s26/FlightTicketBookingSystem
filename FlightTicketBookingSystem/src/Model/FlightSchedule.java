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
	
	public String[] toArray() {
		String[] scheduleTableRow = new String[8];
		scheduleTableRow[0] = this.scheduleid+"";
		scheduleTableRow[1] = this.getFlight().getFlightname();
		scheduleTableRow[2] = this.getFlight().getFlightNumber();
		scheduleTableRow[3] = this.getRoute().getDepatureAirport().getName();
		scheduleTableRow[4] = this.getRoute().getArrivalAirport().getName();
		scheduleTableRow[5] = this.getDeptTime();
		scheduleTableRow[6] = this.getArrivalTime();
		scheduleTableRow[7] = this.getCreatedAt();
		
		return scheduleTableRow;
	}
	
	public String getScheduleInfo() {
		return this.flight.getFlightname()+"("+this.flight.getFlightNumber()+")\n"+"Dept : "+route.getDepatureAirport().getName()+"\n Arrive"+this.route.getArrivalAirport().getName()+"\nDept time"+this.deptTime;
	}
	
	
}
