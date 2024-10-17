package Dao;


import Model.Customer;

public abstract class CustomerDao extends AbstractDao<Customer> {
	
	public abstract Customer findCustomerByName(String name);
	public abstract void updateCustomer(Customer customer);
	public abstract void deleteCustomer(int customerId);
	 
}
