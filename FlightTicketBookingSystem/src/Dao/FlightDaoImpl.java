package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionFactory;
import Database.MySQLConnectionFactory;
import Model.Flight;

public class FlightDaoImpl implements FlightDao {
	
	private ConnectionFactory connectionFactory;
	
	public FlightDaoImpl() {
		this.connectionFactory = new MySQLConnectionFactory();
	}

	@Override
	public List<Flight> getAllFlights() {
		String sql = "SELECT * FROM FLIGHTS";
		List<Flight> flights = new ArrayList<Flight>();
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String number = resultSet.getNString("number");
				flights.add(new Flight(id,name, number));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

	@Override
	public Flight getFlightByFlightId(int id) {
		String sql = "SELECT * FROM FLIGHTS WHERE id = ?";
		Flight flight = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String number1 = resultSet.getString("number");
				flight = new Flight(id1,name,number1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flight;
	}

	@Override
	public void addFlight(Flight flight) {
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
	
	
	
	}

	
