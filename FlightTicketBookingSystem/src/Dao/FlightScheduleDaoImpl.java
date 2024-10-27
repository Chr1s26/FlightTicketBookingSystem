package Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Model.Flight;
import Model.FlightSchedule;
import Model.Route;
import util.DateConverter;

public class FlightScheduleDaoImpl extends AbstractDao<FlightSchedule>{
	
	private RouteDaoImpl routeDao = new RouteDaoImpl();
	private FlightDaoImpl flightDao = new FlightDaoImpl();

	@Override
	public String getTableName() {
		return "SCHEDULES";
	}

	@Override
	public FlightSchedule convertToObject(ResultSet resultset){
		FlightSchedule flightSchedule = null;
		try {
		int id1 = resultset.getInt("id");
		int routeid = resultset.getInt("route_id");
		Route route = routeDao.getById(routeid);
		int flightid = resultset.getInt("flight_id");
		Flight flight = flightDao.getById(flightid);
		String depttime = resultset.getString("dept_time");
		String arrivetime = resultset.getString("arrive_time");
		String createdAt = resultset.getString("created_at");
	    flightSchedule = new FlightSchedule(id1,flight, route, DateConverter.toDateTimeObj(depttime),DateConverter.toDateTimeObj(arrivetime),DateConverter.toDateTimeObj(createdAt));
	    }
		
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		return flightSchedule;
	}


	@Override
	public String getInsertQuery() {
		return "INSERT INTO SCHEDULES (route_id,flight_id,dept_time,arrive_time,created_at) VALUES (?,?,?,?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, FlightSchedule flightSchedule){
		try {
			preparedStatement.setInt(1,flightSchedule.getRoute().getRouteId());
			preparedStatement.setInt(2,flightSchedule.getFlight().getFlightid());
			preparedStatement.setTimestamp(3,DateConverter.toTimestampObj(flightSchedule.getDeptTime()));
			preparedStatement.setTimestamp(4, DateConverter.toTimestampObj(flightSchedule.getArrivalTime()));
			preparedStatement.setTimestamp(5, DateConverter.toTimestampObj(flightSchedule.getCreatedAt()));
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE schedules SET route_id = ?, flight_id = ?, dept_time = ?, arrive_time = ?, created_at = ? WHERE id = ?";
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, FlightSchedule object) {
		try {
		preparedStatement.setInt(1,object.getRoute().getRouteId());
		preparedStatement.setInt(2,object.getFlight().getFlightid());
		preparedStatement.setTimestamp(3,DateConverter.toTimestampObj(object.getDeptTime()));
		preparedStatement.setTimestamp(4, DateConverter.toTimestampObj(object.getArrivalTime()));
		preparedStatement.setTimestamp(5, DateConverter.toTimestampObj(object.getCreatedAt()));
		preparedStatement.setInt(6, object.getScheduleid());
		}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());

		}
	}
	
}
