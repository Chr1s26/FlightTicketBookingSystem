package Model;

public class Ticket {

	private FlightSchedule schedule;
	private double ticketprice;
	private Seat seat;
	private Customer customer;
	
	public Ticket(FlightSchedule schedule,Customer customer ,double ticketprice, Seat seat) {
		this.schedule = schedule;
		this.customer = customer;
		this.ticketprice = ticketprice;
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "Customer=" + customer + ", schedule=" + schedule + ", ticketprice=" + ticketprice + ", seat="
				+ seat ;
	}
	
	

}
