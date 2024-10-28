package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Airport;

public class AirportDaoImpl extends AbstractDao<Airport> {
	
	@Override
	public String getTableName() {
		return "AIRPORTS";
	}

	@Override
	public Airport convertToObject(ResultSet resultset)  {
		Airport airport = null;
		try {
		int airportId = resultset.getInt("id");
		String airportName = resultset.getString("name");
		airport = new Airport(airportId,airportName);}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		return airport;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO AIRPORTS (name) VALUES (?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, Airport object) {
		try {
			preparedStatement.setString(1, object.getName());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
	}

	@Override
	public String getUpdateQuery() {
		
		return "UPDATE airports SET name = ? WHERE id = ?";
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, Airport object) {
		try {
			preparedStatement.setString(1, object.getName());
			preparedStatement.setInt(2, object.getAirportId());
		} catch (Exception e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}


}
