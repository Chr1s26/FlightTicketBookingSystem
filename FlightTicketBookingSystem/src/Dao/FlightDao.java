package Dao;

import java.util.List;

import Model.Flight;
import Model.Flight;

public abstract class FlightDao extends AbstractDao<Flight>{

	public abstract void updateFlight(Flight flight);
	public abstract void deleteFlight(int FlightId);
}
