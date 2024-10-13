package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionFactory;
import Database.MySQLConnectionFactory;
import Model.Airport;

public class AirportDaoImpl implements AirportDao {
	
	private ConnectionFactory connectionFactory;
	
	public AirportDaoImpl() {
		this.connectionFactory = new MySQLConnectionFactory();
	}
	
	@Override
	public Airport getAirportById(int id) {
		String sql = "SELECT * FROM AIRPORTS WHERE id = ?";
		Airport airport = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int airportId = resultSet.getInt("id");
				String airportName = resultSet.getString("name");
				airport = new Airport(airportId,airportName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return airport;
	}

	@Override
	public List<Airport> getAllAirport() {
		String sql = "SELECT * FROM AIRPORTS";
		List<Airport> airports = new ArrayList<Airport>();
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				airports.add(new Airport(name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return airports;
	}

	@Override
	public void addAirport(Airport airport) {
		String sql = "INSERT INTO AIRPORTS";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, airport.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
