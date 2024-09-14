package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Database.ConnectionFactory;
import Model.Flight;
import Model.Seat;
import util.Constant;

public class SeatDaoImpl extends AbstractDao<Seat> {
	private final FlightDaoImpl flightDaoImpl;
	private final ConnectionFactory connectionFactory;
	public SeatDaoImpl(ConnectionFactory connectionFactory) {
		super(connectionFactory);
		this.flightDaoImpl = new FlightDaoImpl(connectionFactory);
		this.connectionFactory = connectionFactory;
	}
	
	public List<Seat> getAllByFlightId(int id){
		List<Seat> results = new ArrayList<Seat>();
		String query = "SELECT * FROM "+getTableName()+" WHERE flight_id = ?";
		try(Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					Seat entity = this.convertToEntity(resultSet);
					results.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return results;
	}

	@Override
	protected String getTableName() {
		return Constant.Table.SEAT;
	}

	@Override
	protected Seat convertToEntity(ResultSet resultSet) throws SQLException {
		Seat seat = new Seat();
		seat.setId(resultSet.getInt("id"));
		seat.setName(resultSet.getString("name"));
		seat.setSeatType(resultSet.getString("seat_type"));
		Optional<Flight> flight = this.flightDaoImpl.findById(resultSet.getInt("fligt_id"));
		seat.setFlight(flight.get());
		return seat;
	}

	
}



