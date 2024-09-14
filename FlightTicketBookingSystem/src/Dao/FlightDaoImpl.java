package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.ConnectionFactory;
import Model.Flight;
import Model.Seat;
import util.Constant;

public class FlightDaoImpl extends AbstractDao<Flight> {

	public FlightDaoImpl(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	protected String getTableName() {
		return Constant.Table.FLIGHT;
	}

	@Override
	protected Flight convertToEntity(ResultSet resultSet) throws SQLException {
		
		return null;
	}
	
	
	
}

	
