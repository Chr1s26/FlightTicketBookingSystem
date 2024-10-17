package Model;

public class Customer {
	
	private String customerName;
	private int customerId;
	private String email;
	
	public Customer(int id, String name,String email) {
		this.customerId = id;
		this.customerName = name;
		this.email = email;
	}
	
	public Customer(String name,String email) {
		this.customerName = name;
		this.email = email;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "\nCustomerId = "+customerId+", customerName = " + customerName ;
	}
	
	
}
