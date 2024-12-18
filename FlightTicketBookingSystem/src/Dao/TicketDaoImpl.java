package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import Model.Customer;
import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class TicketDaoImpl extends TicketDao {
	
	private FlightScheduleDaoImpl flightScheduleDao = new FlightScheduleDaoImpl();
	private CustomerDaoImpl customerDao = new CustomerDaoImpl();
	private SeatDaoImpl seatDao = new SeatDaoImpl();

	@Override
	public String getTableName() {
		return "TICKETS";
	}

	@Override
	public Ticket convertToObject(ResultSet resultset){
		Ticket ticket = null;
		try {
		int id = resultset.getInt("id");
		int scheduleid = resultset.getInt("schedule_id");
		FlightSchedule flightScheduleid = flightScheduleDao.getById(scheduleid);
		int customerid = resultset.getInt("customer_id");
		Customer cid = customerDao.getById(customerid);
		int seatid = resultset.getInt("seat_id");
		Seat sid = seatDao.getById(seatid);
		double price = resultset.getDouble("price");
		ticket = new Ticket(id, flightScheduleid, cid,sid ,price);}
		catch (Exception e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		return ticket;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO TICKETS (schedule_id,customer_id,seat_id,price) VALUES (?,?,?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, Ticket ticket) {
		try {
			preparedStatement.setInt(1,ticket.getSchedule().getScheduleid());
			preparedStatement.setInt(2,ticket.getCustomer().getCustomerId());
			preparedStatement.setInt(3, ticket.getSeat().getSeatid());
			preparedStatement.setDouble(4, ticket.getTicketprice());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

	@Override
	public void delete(int id) {
		try {
			String query = "Delete From tickets where id = ?";
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}finally {
			this.connectionFactory.closeConnection();
		}
	}

	@Override
	public List<Ticket> findticketByCustomerId(int id) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		try {
			String query = "SELECT * FROM "+this.getTableName()+" WHERE customer_id = ?";
			Connection connection = connectionFactory.createConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1,id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int scheduleid = resultSet.getInt("schedule_id");
				FlightSchedule flightSchedule = flightScheduleDao.getById(scheduleid);
				int customerid = resultSet.getInt("customer_id");
				Customer customer = customerDao.getById(customerid);
				int seatid = resultSet.getInt("seat_id");
				Seat seat = seatDao.getById(seatid);
				double price = resultSet.getDouble("price");
				Ticket ticket = new Ticket(flightSchedule, customer, seat, price);
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
		return tickets;
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE tickets SET schedule_id = ?, customer_id = ?, seat_id = ?, price = ? WHERE id = ?";
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, Ticket object) {
		try {
			preparedStatement.setInt(1, object.getSchedule().getScheduleid());
			preparedStatement.setInt(2, object.getCustomer().getCustomerId());
			preparedStatement.setInt(3, object.getSeat().getSeatid());
			preparedStatement.setDouble(4, object.getTicketprice());
			preparedStatement.setInt(5, object.getTicketId());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
	}
}

	
