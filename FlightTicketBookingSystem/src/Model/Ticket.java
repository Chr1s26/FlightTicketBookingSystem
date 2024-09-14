package Model;

public class Ticket {

	private int ticketId;
	private FlightSchedule schedule;
	private double ticketprice;
	private Seat seat;
	
	public Ticket(int ticketId,FlightSchedule schedule, double ticketprice, Seat seat) {
		this.ticketId = ticketId;
		this.schedule = schedule;
		this.ticketprice = ticketprice;
		this.seat = seat;
	}

	public int getTicketId() {
		return ticketId;
	}

}
