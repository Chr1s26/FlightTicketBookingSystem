package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionFactory;
import Database.MySQLConnectionFactory;
import Model.Flight;
import Model.Seat;

public class SeatDaoImpl implements SeatDao {
	
	private ConnectionFactory connectionFactory;
	private FlightDao flightDao = new FlightDaoImpl();
	
	public SeatDaoImpl() {
		this.connectionFactory = new MySQLConnectionFactory();
	}

	@Override
	public List<Seat> getAllSeats() {
		String sql = "SELECT * FROM SEATS";
		List<Seat> seats = new ArrayList<Seat>();
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String seatType = resultSet.getString("seatType");
				int flightid = resultSet.getInt("flight_id");
				String seatNumber = resultSet.getString("SeatNumber");
				Flight flight = flightDao.getFlightByFlightId(flightid);
				seats.add(new Seat(id, seatType, flight,seatNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seats;
	}

	@Override
	public Seat getSeatById(int seatId) {
		String sql = "SELECT * FROM SEATS WHERE id = ?";
		Seat seat = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seatId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id = resultSet.getInt("id");
				String seatType = resultSet.getString("seatType");
				int flightid = resultSet.getInt("flight_id");
				Flight flight = flightDao.getFlightByFlightId(flightid);
				String seatNumber = resultSet.getString("seatNumber");
				seat = new Seat(seatId, seatType, flight, seatNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seat;
	}

	@Override
	public void addSeat(Seat seat) {
		String sql = "INSERT INTO SEATS(seatType,flight_id,seatNumber) VALUES(?,?,?)";
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
	

	}



