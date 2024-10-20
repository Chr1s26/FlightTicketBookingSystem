package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Airport;

public class AirportDaoImpl extends AirportDao {
	
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
		return "INSERT INTO AIRPORTS (id,name) VALUES (?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, Airport object) {
		try {
			preparedStatement.setInt(1, object.getAirportId());
			preparedStatement.setString(2, object.getName());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
	}

	@Override
	public void updateAirport(Airport airport) {
		try {
			String query = "UPDATE airports SET name = ? WHERE id = ?";
			Connection connection = connectionFactory.createConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, airport.getName());
			prepareStatement.setInt(2, airport.getAirportId());
			int rowsAffected = prepareStatement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Airport updated successfully.");
	        } else {
	            System.out.println("No airport found with the given ID.");
	        }
			}catch (SQLException e) {
				System.out.print("SQL Exception for : "+e.getMessage());
			}
			finally {
				this.connectionFactory.closeConnection();
			}
		
	}

	@Override
	public void deleteAirport(int airportId) {
		try {
			String query = "DELETE FROM airports WHERE id = ?";
			Connection connection = connectionFactory.createConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, airportId);
			int rowsAffected = prepareStatement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Airport deleted successfully.");
	        } else {
	            System.out.println("No airport found with the given ID.");
	        }
			}catch (SQLException e) {
				System.out.print("SQL Exception for : "+e.getMessage());
			}
			finally {
				this.connectionFactory.closeConnection();
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
