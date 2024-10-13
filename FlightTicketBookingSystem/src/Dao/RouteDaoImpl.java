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
import Model.Flight;
import Model.Route;

public class RouteDaoImpl implements RouteDao {
	
	private ConnectionFactory connectionFactory;
	private AirportDao airportdao = new AirportDaoImpl();
	private FlightDao flightDao =  new FlightDaoImpl();
	
	public RouteDaoImpl() {
		this.connectionFactory = new MySQLConnectionFactory();
	}

	@Override
	public List<Route> getAllRoutes() {
		String sql = "SELECT * FROM ROUTES";
		List<Route> routes = new ArrayList<Route>();
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int arrival = resultSet.getInt("arrive_airprotid");
				Airport arrivalairport = airportdao.getAirportById(arrival);
				int dept = resultSet.getInt("dept_airportid");
				Airport deptairport = airportdao.getAirportById(dept);
				int distance = resultSet.getInt("distance");
				routes.add(new Route(deptairport, arrivalairport, distance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return routes;
	}

	@Override
	public Route getRouteById(int id) {
		String sql = "SELECT * FROM ROUTES WHERE id = ?";
		Route route = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int Routeid = resultSet.getInt("id");
				int arrival = resultSet.getInt("arrive_airprotid");
				Airport arrivalairport = airportdao.getAirportById(arrival);
				int dept = resultSet.getInt("dept_airportid");
				Airport deptairport = airportdao.getAirportById(dept);
				int distance = resultSet.getInt("distance");
				route = new Route(deptairport, arrivalairport, distance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return route;
	}

	@Override
	public void addRoute(Route route) {
		String sql = "INSERT INTO ROUTES(arrive_airportid,dept_airportid,distance,flight_id) VALUES (?,?,?,?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, route.getArrivalAirport().getAirportId());
			preparedStatement.setInt(2, route.getDepatureAirport().getAirportId());
			preparedStatement.setInt(3, route.getDistance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
