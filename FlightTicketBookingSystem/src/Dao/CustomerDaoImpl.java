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
			Customer customer = new Customer(CustomerId,CustomerName);
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO CUSTOMERS (NAME) VALUES (?)";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement,Customer object) {
		try {
			preparedStatement.setString(1, object.getCustomerName());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}

	}

	@Override
	public int findCustomerByName(String name) {
		try {
		String query = "SELECT * FROM "+this.getTableName()+" WHERE name = ?";
		Connection connection = connectionFactory.createConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, name);
		ResultSet resultSet = prepareStatement.executeQuery();
		if(resultSet.next()) {
			int customerid = resultSet.getInt("id");
			return customerid;
		}
		}catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
		return 0;
		
	}

	

}
