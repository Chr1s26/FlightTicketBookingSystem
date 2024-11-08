package Model;

public class UserPolicy {
	
	private int id;
	private String name;
	private String description;
	
	public UserPolicy() {
		
	}
	
	public UserPolicy(String name,String description) {
		this.name = name;
		this.description = description;
	}
	
	public UserPolicy(int id,String name,String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
