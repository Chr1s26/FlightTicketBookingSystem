package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.ConnectionFactory;
import Database.PgSqlConnectionFactory;

public abstract class AbstractDao<T> {
	
	public ConnectionFactory connectionFactory;
	
	public AbstractDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}
	
	public abstract String getTableName();
	
	public abstract T convertToObject(ResultSet resultset) ;
	
	public abstract String getInsertQuery();
	
	public abstract String getUpdateQuery();
	
	public abstract void prepareParams(PreparedStatement preparedStatement,T object);
	
	public abstract void prepareParamsForUpdate(PreparedStatement preparedStatement,T object);
	
	public List<T> getAll() {
		List<T> objects = new ArrayList<T>();
		try {
		String query = "SELECT * FROM "+this.getTableName();
		Connection connection = this.connectionFactory.createConnection();
		PreparedStatement preparestatement = connection.prepareStatement(query);
		ResultSet resultset = preparestatement.executeQuery();
		while(resultset.next()) {
			T object = this.convertToObject(resultset);
			objects.add(object);
		}
		}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
			
		}finally {
			this.connectionFactory.closeConnection();
		}
		return objects;
	}
	
	
	public T getById(int id)  {
		T object = null;
		try {
			String query = "SELECT * FROM "+this.getTableName()+" WHERE id = ?";
			Connection connection = connectionFactory.createConnection() ;
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				object = this.convertToObject(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
		return object;
	}
	
	
	public void create(T object) {
		try {
			String query = this.getInsertQuery();
		
		Connection connection = connectionFactory.createConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		this.prepareParams(preparedStatement,object);
		preparedStatement.executeUpdate();
		this.connectionFactory.closeConnection();}
		catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}finally {
			this.connectionFactory.closeConnection();
		}
	}
	  
	public void update(T object) {
		try {
			String query = this.getUpdateQuery();
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			this.prepareParamsForUpdate(preparedStatement, object);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}finally {
			this.connectionFactory.closeConnection();
		}
	}
	
	public void delete(int id) {
	    try {
	        String query = "DELETE FROM " + this.getTableName() + " WHERE id = ?";
	        Connection connection = this.connectionFactory.createConnection();
	        PreparedStatement prepareStatement = connection.prepareStatement(query);
	        prepareStatement.setInt(1, id);
	        prepareStatement.executeUpdate(); 
	        System.out.println("Record with ID " + id + " deleted successfully.");
	    } catch (SQLException e) {
	        System.out.print("SQL Exception: " + e.getMessage());
	    } finally {
	        this.connectionFactory.closeConnection();
	    }
	}

	
	
	

}
