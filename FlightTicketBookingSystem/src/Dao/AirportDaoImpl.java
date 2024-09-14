package Dao;

import Model.Airport;

public class AirportDaoImpl implements AirpotDao {

	  Airport[] airportarr = {
			 	new Airport(1,"JFK"),
	            new Airport(2,"LAX"),
	            new Airport(3,"Heathrow"),
	            new Airport(4,"Charles de Gaulle"),
	            new Airport(5,"Tokyo Haneda"),
	            new Airport(6,"Dubai International"),
	            new Airport(7,"Singapore Changi"),
	            new Airport(8,"Hong Kong International"),
	            new Airport(9,"Sydney Kingsford Smith"),
	            new Airport(10,"Toronto Pearson")};
	 
	 @Override
	 public Airport[] getAirport() {
		 return airportarr;
	 }

	@Override
	public Airport getAirportByName(String name) {
		for (Airport airport : airportarr) {
			if(airport.getName().equalsIgnoreCase(name)) {
				return airport;
			}
		}
		return null;
	}

}
