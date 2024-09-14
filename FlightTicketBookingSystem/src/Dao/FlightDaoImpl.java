package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.ConnectionFactory;
import Model.Flight;
import Model.Seat;
import util.Constant;

public class FlightDaoImpl extends AbstractDao<Flight> {

	private final SeatDaoImpl seatDaoImpl;
	
	public FlightDaoImpl(ConnectionFactory connectionFactory) {
		super(connectionFactory);
		this.seatDaoImpl = new SeatDaoImpl(connectionFactory);
	}

	@Override
	protected String getTableName() {
		return Constant.Table.FLIGHT;
	}

	@Override
	protected Flight convertToEntity(ResultSet resultSet) throws SQLException {
		Flight flight = new Flight();
		flight.setId(resultSet.getInt("id"));
		flight.setName(resultSet.getString("name"));
		flight.setNumber(resultSet.getString("number"));
		flight.setSeats(this.seatDaoImpl.getAllByFlightId(flight.getId()));
		return flight;
	}
	
	
	
}

	
