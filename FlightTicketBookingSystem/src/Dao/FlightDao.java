package Dao;

import java.util.List;

import Model.Flight;

public interface FlightDao {

	 List<Flight> getAllFlights();
	 Flight getFlightByFlightId(int id);
	 void addFlight(Flight flight);
}
