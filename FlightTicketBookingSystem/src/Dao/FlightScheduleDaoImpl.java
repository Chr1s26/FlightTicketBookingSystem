package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Flight;
import Model.FlightSchedule;
import Model.Route;

public class FlightScheduleDaoImpl extends AbstractDao<FlightSchedule>{
	
	private RouteDaoImpl routeDao = new RouteDaoImpl();
	private FlightDaoImpl flightDao = new FlightDaoImpl();

	@Override
	public void add(FlightSchedule flightSchedule) {
		String sql = "INSERT INTO SCHEDULES (route_id,flight_id,depttime,arrivetime,createdat) VALUES (?,?,?,?,?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,flightSchedule.getRoute().getRouteId());
			preparedStatement.setInt(2,flightSchedule.getFlight().getFlightid());
			preparedStatement.setString(3,flightSchedule.getDeptTime());
			preparedStatement.setString(4, flightSchedule.getArrivalTime());
			preparedStatement.setString(5, flightSchedule.getCreatedAt());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String gettableName() {
		return "SCHEDULES";
	}

	@Override
	public FlightSchedule convertToObject(ResultSet resultset) throws SQLException {
		int id1 = resultset.getInt("id");
		int routeid = resultset.getInt("route_id");
		Route route = routeDao.getById(routeid);
		int flightid = resultset.getInt("flight_id");
		Flight flight = flightDao.getById(flightid);
		String depttime = resultset.getString("depttime");
		String arrivetime = resultset.getString("arrivetime");
		String createdAt = resultset.getString("createdat");
		FlightSchedule flightSchedule = new FlightSchedule(id1,flight, route, depttime, arrivetime, createdAt);
		return flightSchedule;
	}
	
}
