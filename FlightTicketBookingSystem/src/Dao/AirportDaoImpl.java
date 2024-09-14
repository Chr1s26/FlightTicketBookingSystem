package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.ConnectionFactory;
import Model.Airport;
import util.Constant;

public class AirportDaoImpl extends AbstractDao<Airport> {

	public AirportDaoImpl(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	protected String getTableName() {
		return Constant.Table.AIRPORT;
	}

	@Override
	protected Airport convertToEntity(ResultSet resultSet) throws SQLException {
		Airport airport = new Airport(resultSet.getInt("id"), resultSet.getString("name"));
		return airport;
	}

	 
	

}
