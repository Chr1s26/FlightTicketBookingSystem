package Model;

import java.util.List;

public class Flight {
	
	private int id;
	private String name;
	private String number;
	private List<Seat> seats;
	
	public Flight(int id, String name,String number, List<Seat> seats) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.seats = seats;
	}

	public Flight() {
	}


	public List<Seat> getSeats() {
		return seats;
	}

	
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	

}
