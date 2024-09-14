package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Database.ConnectionFactory;

public abstract class AbstractDao<T> {
	
	protected abstract String getTableName();
	protected abstract T convertToEntity(ResultSet resultSet) throws SQLException;
	
	private final ConnectionFactory connectionFactory;

	public AbstractDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	public List<T> getAll() {
		List<T> results = new ArrayList<T>();
		String query = "SELECT * FROM "+getTableName();
		try(Connection connection = this.connectionFactory.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					T entity = this.convertToEntity(resultSet);
					results.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return results;
	}
	
	public Optional<T> findById(int id) {
		String query = "SELECT * FROM "+getTableName()+" WHERE id = ?";
		try(Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					T entity = this.convertToEntity(resultSet);
					return Optional.of(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return Optional.empty();
	}

}
