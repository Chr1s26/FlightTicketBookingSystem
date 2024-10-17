package Dao;

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


}
