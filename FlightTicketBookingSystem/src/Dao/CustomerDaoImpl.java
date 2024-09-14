package Dao;

import Model.Customer;

public class CustomerDaoImpl implements CustomerDao {
	
	Customer[] customers = {
			new Customer("John Doe"),
            new Customer("Jane Smith")
	};

	@Override
	public Customer[] getAllCustomers() {
		return customers;
	}

	@Override
	public Customer getCustomerByName(String name) {
		for (Customer customer : customers) {
            if (customer.getCustomerName().equals(name)) {
                return customer;
            }
        }
        return null;
	}

	

}
