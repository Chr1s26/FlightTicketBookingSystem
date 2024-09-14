package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.ConnectionFactory;
import Model.Seat;
import util.Constant;

public class SeatDaoImpl extends AbstractDao<Seat> {

	public SeatDaoImpl(ConnectionFactory connectionFactory) {
		super(connectionFactory);
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
		return seat;
	}

	
}



