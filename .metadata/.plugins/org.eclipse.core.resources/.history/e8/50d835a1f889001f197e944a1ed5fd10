package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Customer;
import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class TicketDaoImpl extends AbstractDao<Ticket> {
	
	private FlightScheduleDaoImpl flightScheduleDao = new FlightScheduleDaoImpl();
	private CustomerDaoImpl customerDao = new CustomerDaoImpl();
	private SeatDaoImpl seatDao = new SeatDaoImpl();

	@Override
	public String gettableName() {
		return "TICKETS";
	}

	@Override
	public Ticket convertToObject(ResultSet resultset) throws SQLException {
		int id = resultset.getInt("id");
		int scheduleid = resultset.getInt("schedule_id");
		FlightSchedule flightScheduleid = flightScheduleDao.getById(scheduleid);
		int customerid = resultset.getInt("customer_id");
		Customer cid = customerDao.getById(customerid);
		int seatid = resultset.getInt("seat_id");
		Seat sid = seatDao.getById(seatid);
		double price = resultset.getDouble("price");
		Ticket ticket = new Ticket(id, flightScheduleid, cid,sid ,price);
		return ticket;
	}

	@Override
	void add(Ticket ticket) {
		String sql = "INSERT INTO TICKETS (schedule_id,customer_id,seat_id,price) VALUES (?,?,?,?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1,ticket.getSchedule().getScheduleid());
			prepareStatement.setInt(2,ticket.getCustomer().getCustomerId());
			prepareStatement.setInt(3, ticket.getSeat().getSeatid());
			prepareStatement.setDouble(4, ticket.getTicketprice());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

	
