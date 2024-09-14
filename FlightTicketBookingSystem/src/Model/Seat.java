package Model;

public class Seat {

	private int id;
	private String name;
	private String seatType;
	
	public Seat() {
		
	}
	
	public Seat(int id, String name, String seatType) {
		this.id = id;
		this.name = name;
		this.seatType = seatType;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	


}
