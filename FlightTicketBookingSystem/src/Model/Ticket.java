package Model;

public class Ticket {
	
	private int ticketId;
	private FlightSchedule schedule;
	private double ticketprice;
	private Seat seat;
	private Customer customer;
	
	public Ticket(FlightSchedule schedule,Customer customer , Seat seat, double ticketprice) {
		this.schedule = schedule;
		this.customer = customer;
		this.ticketprice = ticketprice;
		this.seat = seat;
	}
	
	public Ticket(int ticketId,FlightSchedule schedule,Customer customer , Seat seat, double ticketprice) {
		this.ticketId = ticketId;
		this.schedule = schedule;
		this.customer = customer;
		this.ticketprice = ticketprice;
		this.seat = seat;
	}
	
	public FlightSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(FlightSchedule schedule) {
		this.schedule = schedule;
	}

	public double getTicketprice() {
		return ticketprice;
	}

	public void setTicketprice(double ticketprice) {
		this.ticketprice = ticketprice;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public String toString() {
		return "Customer=" + customer + ", schedule=" + schedule + ", ticketprice=" + ticketprice + ", seat="
				+ seat ;
	}
	
	

}
