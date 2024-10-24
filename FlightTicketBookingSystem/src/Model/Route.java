package Model;

public class Route {
	
	private int routeId;
	private Airport depatureAirport;
	private Airport arrivalAirport;
	private int distance;
	private Flight flight;
	
	public Route(int routeId,Airport arrival, Airport depature,int distance) {
		this.routeId = routeId;
		this.depatureAirport = depature;
		this.arrivalAirport = arrival;
		this.distance = distance;
	}
	
	public Route(Airport arrival, Airport depature,int distance) {
		this.depatureAirport = depature;
		this.arrivalAirport = arrival;
		this.distance = distance;
	}

	public Airport getDepatureAirport() {
		return depatureAirport;
	}

	public void setDepatureAirport(Airport depatureAirport) {
		this.depatureAirport = depatureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public int getRouteId() {
		return routeId;
	}
	
	public String getRouteInfo() {
		return "Dept Location : "+this.depatureAirport.getName()+"To "+this.arrivalAirport.getName()+"("+this.distance+")";
	}

	@Override
	public String toString() {
		return "\nRoute [routeId=" + routeId + ", depatureAirport=" + depatureAirport + ", arrivalAirport="
				+ arrivalAirport + ",distance="+distance+"]";
	}

	
}
