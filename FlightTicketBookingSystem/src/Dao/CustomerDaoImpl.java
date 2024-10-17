package Dao;
import Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDaoImpl extends CustomerDao {

	@Override
	public String getTableName() {
		return "CUSTOMERS";
	}

	@Override
	public Customer convertToObject(ResultSet resultset) {
		try {
			int CustomerId = resultset.getInt("id");
			String CustomerName = resultset.getString("name");
			String email = resultset.getString("email");
			Customer customer = new Customer(CustomerId,CustomerName,email);
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO CUSTOMERS (id,name,email) VALUES (?,?,?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement,Customer object) {
		try {
			preparedStatement.setInt(1, object.getCustomerId());
			preparedStatement.setString(2, object.getCustomerName());
			preparedStatement.setString(3, object.getEmail());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}

	}

	@Override
	public Customer findCustomerByName(String name) {
		Customer customer = null;
		try {
		String query = "SELECT * FROM "+this.getTableName()+" WHERE name LIKE ? ";
		Connection connection = connectionFactory.createConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, name);
		ResultSet resultSet = prepareStatement.executeQuery();
		if(resultSet.next()) {
			int customerid = resultSet.getInt("id");
			customer = this.getById(customerid);
 		}
		}catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
		return customer;
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		try {
		String query = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
		Connection connection = connectionFactory.createConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, customer.getCustomerName());
		prepareStatement.setString(2, customer.getEmail());
		prepareStatement.setInt(3, customer.getCustomerId());
		int rowsAffected = prepareStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("No customer found with the given ID.");
        }
		}catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		try {
			String query = "DELETE FROM customers WHERE id = ?";
			Connection connection = connectionFactory.createConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, customerId);
			int rowsAffected = prepareStatement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Customer deleted successfully.");
	        } else {
	            System.out.println("No customer found with the given ID.");
	        }
			}catch (SQLException e) {
				System.out.print("SQL Exception for : "+e.getMessage());
			}
			finally {
				this.connectionFactory.closeConnection();
			}
	}

	

}
