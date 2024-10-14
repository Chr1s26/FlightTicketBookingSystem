package Dao;

import Model.Booking;
import Model.Customer;
import Model.Flight;
import Model.Ticket;

public class BookingDaoImpl implements BookingDao {

	CustomerDao customerDao = new CustomerDaoImpl();
	FlightDao flightDao = new FlightDaoImpl();
	TicketDao ticketDao = new TicketDaoImpl();
	
	Customer[] customers = customerDao.getAllCustomers();
	Flight[] flights = flightDao.getAllFlights();
	Ticket[] tickets = ticketDao.getAllTickets();
	
	Booking[] bookings = {
			new Booking(1,customers[0], flights[0], tickets[0]),
            new Booking(2,customers[1], flights[1], tickets[1])
	};

	@Override
	public Booking[] getAllBookings() {
		return bookings;
	}

	@Override
	public Booking getBookingById(int id) {
		for (Booking booking : bookings) {
            if (booking.getBookingId() == id) {
                return booking;
            }
        }
        return null;
	}

}
