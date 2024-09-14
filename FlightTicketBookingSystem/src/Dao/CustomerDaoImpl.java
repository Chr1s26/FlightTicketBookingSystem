package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.ConnectionFactory;
import Model.Customer;
import util.Constant;

public class CustomerDaoImpl extends AbstractDao<Customer> {

	public CustomerDaoImpl(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	protected String getTableName() {
		return Constant.Table.CUSTOMER;
	}

	@Override
	protected Customer convertToEntity(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"));
		return customer;
	}

	
	
	
}
