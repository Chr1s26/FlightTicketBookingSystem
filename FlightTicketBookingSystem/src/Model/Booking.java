package Model;

public class Booking {
	
	private int BookingId;
	private Customer customer;
	private Flight flight;
	private Ticket ticket;
	
	public Booking(int BookingId,Customer customer, Flight flight, Ticket ticket) {
		this.BookingId = BookingId;
		this.customer = customer;
		this.flight = flight;
		this.ticket = ticket;
	}

	public int getBookingId() {
		return BookingId;
	}

}
