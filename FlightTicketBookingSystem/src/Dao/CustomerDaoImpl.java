package Dao;

import Database.ConnectionFactory;
import Database.MySQLConnectionFactory;
import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
	
	private ConnectionFactory connectionFactory;
	
	public CustomerDaoImpl() {
		this.connectionFactory = new MySQLConnectionFactory();
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		String sql = "SELECT * FROM CUSTOMERS";
		List<Customer> customers = new ArrayList<Customer>();
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				customers.add(new Customer(name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer getCustomerByName(String name) {
		String sql = "SELECT * FROM CUSTOMERS WHERE name = ?";
		Customer customer = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				String CustomerName = resultSet.getString("name");
				customer = new Customer(CustomerName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public void registerCustomer(Customer customer) {
		String sql = "INSERT INTO CUSTOMERS (NAME) VALUES (?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

}
