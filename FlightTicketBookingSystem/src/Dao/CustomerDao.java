package Dao;

import java.sql.SQLException;
import java.util.List;

import Model.Customer;

public interface CustomerDao {
	
	 void registerCustomer(Customer customer);
	 List<Customer> getAllCustomers() throws SQLException;
	 Customer getCustomerByName(String name);
	 
}
