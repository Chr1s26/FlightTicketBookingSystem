package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionFactory;
import Database.PgSqlConnectionFactory;
import Model.Airport;

public abstract class AbstractDao<T> {
	
	public ConnectionFactory connectionFactory;
	
	public AbstractDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}
	
	public abstract String gettableName();
	
	public abstract T convertToObject(ResultSet resultset) throws SQLException;
	abstract void add(T object);
	
	public List<T> getAll() throws SQLException{
		String query = "SELECT * FROM "+this.gettableName();
		List<T> objects = new ArrayList<T>();
		Connection connection = this.connectionFactory.createConnection();
		PreparedStatement preparestatement = connection.prepareStatement(query);
		ResultSet resultset = preparestatement.executeQuery();
		while(resultset.next()) {
			T object = this.convertToObject(resultset);
			objects.add(object);
		}
		this.connectionFactory.closeConnection();
		return objects;
	}
	
	public T getById(int id) throws SQLException {
		String query = "SELECT * FROM "+this.gettableName()+" WHERE id = ?";
		T object = null;
		try(Connection connection = connectionFactory.createConnection()) {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				object = this.convertToObject(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	

}
