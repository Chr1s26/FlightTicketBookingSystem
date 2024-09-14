package Model;

public class Route {
	
	private int routeId;
	private Airport depatureAirport;
	private Airport arrivalAirport;
	
	public Route(int routeId,Airport depature, Airport arrival) {
		this.routeId = routeId;
		this.depatureAirport = depature;
		this.arrivalAirport = arrival;
	}

	public int getRouteId() {
		return routeId;
	}
	
	

	
}
