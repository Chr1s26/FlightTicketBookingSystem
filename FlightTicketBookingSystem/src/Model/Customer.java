package Model;

public class Customer {
	
	private String customerName;
	private int customerId;
	
	public Customer(int id, String name) {
		this.customerId = id;
		this.customerName = name;
	}
	
	public Customer(String name) {
		this.customerName = name;
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

	@Override
	public String toString() {
		return "\nCustomerId = "+customerId+", customerName = " + customerName ;
	}
	
	
}
