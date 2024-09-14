package Dao;

import Model.Flight;

public interface FlightDao {

	 Flight[] getAllFlights();
	 Flight getFlightByFlightNumber(String number);
}
