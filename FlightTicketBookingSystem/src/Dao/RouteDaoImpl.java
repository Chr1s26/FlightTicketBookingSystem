package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Airport;
import Model.Route;

public class RouteDaoImpl extends AbstractDao<Route> {
	
	private AirportDaoImpl airportdao = new AirportDaoImpl();

	@Override
	public void add(Route route) {
		String sql = "INSERT INTO ROUTES(arrive_airport_id,dept_airport_id,distance,flight_id) VALUES (?,?,?,?)";
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

	@Override
	public String gettableName() {
		return "ROUTES";
	}

	@Override
	public Route convertToObject(ResultSet resultset) throws SQLException {
		int Routeid = resultset.getInt("id");
		int arrival = resultset.getInt("arrive_airport_id");
		Airport arrivalairport = airportdao.getById(arrival);
		int dept = resultset.getInt("dept_airport_id");
		Airport deptairport = airportdao.getById(dept);
		int distance = resultset.getInt("distance");
		Route route = new Route(Routeid,deptairport, arrivalairport, distance);
		return route;
	}

}
