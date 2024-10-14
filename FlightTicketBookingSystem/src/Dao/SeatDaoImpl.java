package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Flight;
import Model.Seat;

public class SeatDaoImpl extends AbstractDao<Seat> {
	
	private FlightDaoImpl flightDao = new FlightDaoImpl();

	@Override
	public void add(Seat seat) {
		String sql = "INSERT INTO SEATS(seat_type,flight_id,name) VALUES(?,?,?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,seat.getSeatType());
			preparedStatement.setInt(2, seat.getFlight().getFlightid());
			preparedStatement.setString(3, seat.getSeatNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String gettableName() {
		return "SEATS";
	}

	@Override
	public Seat convertToObject(ResultSet resultset) throws SQLException {
		int id = resultset.getInt("id");
		String seatType = resultset.getString("seat_type");
		int flightid = resultset.getInt("flight_id");
		Flight flight = flightDao.getById(flightid);
		String seatNumber = resultset.getString("name");
		Seat seat = new Seat(id, seatType, flight, seatNumber);
		return seat;
	}

	}



