package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Flight;

public class FlightDaoImpl extends AbstractDao<Flight> {


	@Override
	public String getTableName() {
		return "flights";
	}

	@Override
	public Flight convertToObject(ResultSet resultset) {
		Flight flight = null;
		try {
		int id1 = resultset.getInt("id");
		String name = resultset.getString("name");
		String number1 = resultset.getString("number");
		flight = new Flight(id1,name,number1);}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		return flight;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO FLIGHTS (name,number) VALUES (?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, Flight object) {
		try {
			preparedStatement.setString(1, object.getFlightname());
			preparedStatement.setString(2, object.getFlightNumber());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		} 
		
	}
	
	}

	
