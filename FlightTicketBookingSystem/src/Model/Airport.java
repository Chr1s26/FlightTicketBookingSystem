package Model;

public class Airport {

	private String name;
	private int airportId;
	
	public int getAirportId() {
		return airportId;
	}


	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}


	public Airport(int airportId,String name) {
		this.airportId = airportId;
		this.name = name;
	}

	
	public Airport(String name) {
		this.name = name;	
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return  "Airportname=" + name ;
	}


}
