package Model;

public class Route {
	
	private int routeId;
	private Airport depatureAirport;
	private Airport arrivalAirport;
	private int distance;
	private Flight flight;
	
	public Route(int routeId,Airport depature, Airport arrival,int distance) {
		this.routeId = routeId;
		this.depatureAirport = depature;
		this.arrivalAirport = arrival;
		this.distance = distance;
	}
	
	public Route(Airport depature, Airport arrival,int distance) {
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

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", depatureAirport=" + depatureAirport + ", arrivalAirport="
				+ arrivalAirport + ",distance is,"+distance+"]";
	}

	
}
