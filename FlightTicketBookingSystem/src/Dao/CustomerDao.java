package Dao;

import Model.Customer;

public interface CustomerDao {

	 Customer[] getAllCustomers();
	 Customer getCustomerByName(String name);

}
