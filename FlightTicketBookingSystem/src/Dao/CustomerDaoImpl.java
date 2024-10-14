package Dao;
import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDaoImpl extends AbstractDao<Customer> {
	

	@Override
	public void add(Customer customer) {
		String sql = "INSERT INTO CUSTOMERS (NAME) VALUES (?)";
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String gettableName() {
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

	

}
