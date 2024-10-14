package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Flight;

public class FlightDaoImpl extends AbstractDao<Flight> {
	
	@Override
	public void add(Flight flight) {
		String sql = "INSERT INTO FLIGHTS (id,name,number) VALUES (?,?,?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, flight.getFlightNumber()); 
			preparedStatement.setString(2, flight.getFlightNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String gettableName() {
		return "flights";
	}

	@Override
	public Flight convertToObject(ResultSet resultset) throws SQLException {
		int id1 = resultset.getInt("id");
		String name = resultset.getString("name");
		String number1 = resultset.getString("number");
		Flight flight = new Flight(id1,name,number1);
		return flight;
	}
	
	}

	
