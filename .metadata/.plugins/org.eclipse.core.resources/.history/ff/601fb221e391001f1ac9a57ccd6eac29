package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Airport;
import Model.Route;

public class RouteDaoImpl extends AbstractDao<Route> {
	
	private AirportDaoImpl airportdao = new AirportDaoImpl();

	@Override
	public String getTableName() {
		return "ROUTES";
	}

	@Override
	public Route convertToObject(ResultSet resultset) {
		Route route = null;
		try {
		int Routeid = resultset.getInt("id");
		int arrival = resultset.getInt("arrive_airport_id");
		Airport arrivalairport = airportdao.getById(arrival);
		int dept = resultset.getInt("dept_airport_id");
		Airport deptairport = airportdao.getById(dept);
		int distance = resultset.getInt("distance");
		route = new Route(Routeid,deptairport, arrivalairport, distance);}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		return route;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO ROUTES(arrive_airport_id,dept_airport_id,distance) VALUES (?,?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, Route route) {
		try {
			preparedStatement.setInt(1, route.getArrivalAirport().getAirportId());
			preparedStatement.setInt(2, route.getDepatureAirport().getAirportId());
			preparedStatement.setInt(3, route.getDistance());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE routes SET arrive_airport_id = ?, dept_airport_id = ?, distance = ? WHERE id = ?";
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, Route object) {
		try {
			preparedStatement.setInt(1, object.getArrivalAirport().getAirportId());
			preparedStatement.setInt(2, object.getDepatureAirport().getAirportId());
			preparedStatement.setInt(3, object.getDistance());
			preparedStatement.setInt(4, object.getRouteId());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

}
