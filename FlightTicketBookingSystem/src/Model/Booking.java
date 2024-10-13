package Model;

public class Booking {
	
	private int BookingId;
	private Customer customer;
	private Ticket ticket;
	
	public Booking(int BookingId,Customer customer,Ticket ticket,Airport airport) {
		this.BookingId = BookingId;
		this.customer = customer;
		this.ticket = ticket;
	}

	public int getBookingId() {
		return BookingId;
	}

	@Override
	public String toString() {
		return "Booking [BookingId=" + BookingId + "\n" + customer + "\n" + ticket + "]";
	}

}
