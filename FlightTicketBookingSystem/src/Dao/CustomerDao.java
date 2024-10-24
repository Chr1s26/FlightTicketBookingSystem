package Dao;


import Model.Customer;

public abstract class CustomerDao extends AbstractDao<Customer> {
	
	public abstract Customer findCustomerByName(String name);
	 
}
