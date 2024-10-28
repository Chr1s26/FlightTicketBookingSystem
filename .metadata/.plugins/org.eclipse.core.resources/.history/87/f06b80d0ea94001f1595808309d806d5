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
	public String getUpdateQuery() {
		return  "UPDATE customers SET name = ?, email = ? WHERE id = ?";
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, Customer object) {
		try {
			preparedStatement.setString(1, object.getCustomerName());
			preparedStatement.setString(2, object.getEmail());
			preparedStatement.setInt(3, object.getCustomerId());
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		
	}

	

}
