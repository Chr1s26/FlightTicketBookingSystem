package Model;

public class Airport {

	private String name;
	private int Id;

	
	public Airport(int id,String name) {
		this.Id = id;
		this.name = name;	
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	


}
