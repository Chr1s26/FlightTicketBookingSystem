package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Flight;
import Model.Seat;

public class SeatDaoImpl extends SeatDao {
	
	private FlightDaoImpl flightDao = new FlightDaoImpl();

	@Override
	public String getTableName() {
		return "SEATS";
	}

	@Override
	public Seat convertToObject(ResultSet resultset) {
		Seat seat = null;
		try {
		int id = resultset.getInt("id");
		String seatType = resultset.getString("seat_type");
		int flightid = resultset.getInt("flight_id");
		Flight flight = flightDao.getById(flightid);
		String seatNumber = resultset.getString("name");
		seat = new Seat(id, seatType, flight, seatNumber);}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		return seat;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO SEATS(seat_type,flight_id,name) VALUES(?,?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, Seat seat) {
		try {
			preparedStatement.setString(1,seat.getSeatType());
			preparedStatement.setInt(2, seat.getFlight().getFlightid());
			preparedStatement.setString(3, seat.getSeatNumber());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

	@Override
	public boolean isAvailableSeat(int schedule_id, int seat_id) {
		int seatCount = -1;
		try {
		String query = "select count(*) as seat_count from schedules join tickets on schedules.id = tickets.schedule_id where schedule_id = ? AND seat_id = ?";
		Connection connection = this.connectionFactory.createConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,schedule_id);
		preparedStatement.setInt(2, seat_id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
		seatCount = resultSet.getInt("seat_count");}
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return seatCount == 0;
	}

	@Override
	public List<Seat> getAvaiableSeatBySchedule(int scheduleId) {
		List<Seat> seats = new ArrayList<Seat>();
		try {
		String query = "SELECT seats.* \r\n"
				+ "FROM flights\r\n"
				+ "JOIN seats ON flights.id = seats.flight_id\r\n"
				+ "LEFT JOIN tickets ON tickets.seat_id = seats.id\r\n"
				+ "JOIN schedules ON schedules.flight_id = flights.id\r\n"
				+ "WHERE tickets.seat_id IS NULL\r\n"
				+ "AND schedules.id = ?";
		Connection connection = this.connectionFactory.createConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,scheduleId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			Seat seat = this.convertToObject(resultSet);
			seats.add(seat);
		}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
		return seats;
	}


	@Override
	public String getUpdateQuery() {
		return "UPDATE seats SET seat_type = ?, flight_id = ?, name = ? WHERE id = ?";
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, Seat object) {
		try {
			preparedStatement.setString(1, object.getSeatType());
			preparedStatement.setInt(2, object.getFlight().getFlightid());
			preparedStatement.setString(3, object.getSeatNumber());
			preparedStatement.setInt(4, object.getSeatid());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

	}



