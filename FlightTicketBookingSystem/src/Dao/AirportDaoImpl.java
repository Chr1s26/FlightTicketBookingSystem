package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Airport;

public class AirportDaoImpl extends AbstractDao<Airport> {
	
	@Override
	public void add(Airport airport) {
		String sql = "INSERT INTO AIRPORTS";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, airport.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String gettableName() {
		return "AIRPORTS";
	}

	@Override
	public Airport convertToObject(ResultSet resultset) throws SQLException {
		int airportId = resultset.getInt("id");
		String airportName = resultset.getString("name");
		Airport airport = new Airport(airportId,airportName);
		return airport;
	}


}
