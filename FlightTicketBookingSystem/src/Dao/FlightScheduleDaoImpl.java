package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import Database.ConnectionFactory;
import Database.MySQLConnectionFactory;
import Model.Flight;
import Model.FlightSchedule;
import Model.Route;

public class FlightScheduleDaoImpl implements FlightScheduleDao{
	
	private ConnectionFactory connectionFactory;
	private RouteDao routeDao = new RouteDaoImpl();
	private FlightDao flightDao = new FlightDaoImpl();
	
	public FlightScheduleDaoImpl() {
		this.connectionFactory = new MySQLConnectionFactory();
	}

	@Override
	public List<FlightSchedule> getFlightSchedule() {
		String sql = "SELECT * FROM SCHEDULES";
		List<FlightSchedule> flightSchedules = new ArrayList<FlightSchedule>();
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int routeid = resultSet.getInt("route_id");
				Route route = routeDao.getRouteById(routeid);
				int flightid = resultSet.getInt("flight_id");
				Flight flight = flightDao.getFlightByFlightId(flightid);
				String depttime = resultSet.getString("deptTime");
				String arrivetime = resultSet.getString("arriveTime");
				String createdAt = resultSet.getString("createdAt");
				flightSchedules.add(new FlightSchedule(id,flight, route, depttime, arrivetime,createdAt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flightSchedules;
	}

	@Override
	public FlightSchedule getFlightScheduleById(int id) {
		String sql = "SELECT * FROM SCHEDULES WHERE id = ?";
		FlightSchedule flightSchedule = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id1 = resultSet.getInt("id");
				int routeid = resultSet.getInt("route_id");
				Route route = routeDao.getRouteById(routeid);
				int flightid = resultSet.getInt("flight_id");
				Flight flight = flightDao.getFlightByFlightId(flightid);
				String depttime = resultSet.getString("deptTime");
				String arrivetime = resultSet.getString("arriveTime");
				String createdAt = resultSet.getString("createdAt");
				flightSchedule = new FlightSchedule(id1,flight, route, depttime, arrivetime, createdAt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flightSchedule;
	}

	@Override
	public void addFlightSchedule(FlightSchedule flightSchedule) {
		String sql = "INSERT INTO SCHEDULES (route_id,flight_id,deptTime,arriveTime,createdAt) VALUES (?,?,?,?,?,?)";
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
	
}
