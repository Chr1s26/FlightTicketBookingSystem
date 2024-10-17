package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	}



