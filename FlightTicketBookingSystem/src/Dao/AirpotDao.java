package Dao;

import Model.Airport;

public interface AirpotDao {

	Airport[] getAirport();
	Airport getAirportByName(String name);

}
