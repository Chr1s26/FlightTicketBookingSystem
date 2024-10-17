package Dao;

import java.util.List;

import Model.Airport;
import Model.Seat;

public abstract class AirportDao extends AbstractDao<Airport> {

	public abstract void updateAirport(Airport airport);
	public abstract void deleteAirport(int airportId);

}
