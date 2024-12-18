package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;
import exception.IncorrectPasswordException;
import exception.IncorrectUserNameException;
import exception.InvalidTokenException;
import util.DateConverter;
import util.PasswordUtil;
import util.TokenUtil;

public class UserDaoImpl extends UserDao {
	
	private UserPolicyDaoImpl userPolicyDao;
	
	public UserDaoImpl() {
		this.userPolicyDao = new UserPolicyDaoImpl();
	}
	
	@Override
	public String getTableName() {
		return "users";
	}

	@Override
	public User convertToObject(ResultSet resultset) {
		User user = new User();
		try {
			user.setId(resultset.getInt("id"));
			user.setName(resultset.getString("username"));
			user.setEmail(resultset.getString("email"));
			user.setPasswordHash(resultset.getString("password_hash"));
			user.setPolicy(this.userPolicyDao.getById(resultset.getInt("policy_id")));
			user.setCreatedAt(DateConverter.toLocalDateTime(resultset.getTimestamp("created_at")));
			user.setUpdatedAt(DateConverter.toLocalDateTime(resultset.getTimestamp("update_at")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public String getInsertQuery() {
		return "insert into "+this.getTableName()+" (username,email,password_hash,policy_id) Values (?,?,?,?)";
	}

	@Override
	public String getUpdateQuery() {
		return "update "+this.getTableName()+ " set username = ?, email = ?, password_hash = ? policy_id = ? where id = ?";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, User user) {
		try {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3,user.getPasswordHash());
			preparedStatement.setInt(4, user.getPolicy().getId() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, User user) {
		try {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3,user.getPasswordHash());
			preparedStatement.setInt(4, user.getPolicy().getId() );
			preparedStatement.setInt(5, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserByUserName(String username) {
		User object = null;
		try {
			String query = "SELECT * FROM "+this.getTableName()+" WHERE username = ?";
			Connection connection = connectionFactory.createConnection() ;
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, username);
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
	
	@Override
	public User getUserByUserEmail(String email) {
		User object = null;
		try {
			String query = "SELECT * FROM "+this.getTableName()+" WHERE email = ?";
			Connection connection = connectionFactory.createConnection() ;
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, email);
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

	@Override
	public User validateUser(String username, String password) throws IncorrectUserNameException, IncorrectPasswordException {
		User user = this.getUserByUserName(username);
		if(user != null) {
			String hashedPassword = PasswordUtil.hashPassword(password);
			String passwordHash = user.getPasswordHash();
			Boolean flag = passwordHash != null && passwordHash.equals(hashedPassword);
			if(flag) {
				String logintoken = TokenUtil.generateToken(user.getName());
				user.setLoginToken(logintoken);
				this.updateLoginToken(user);
				return user;
			}else {
				throw new IncorrectPasswordException("Incorrect Password for: "+password);
			}
			
		}else {
			throw new IncorrectUserNameException("Incorrect Username for: "+username);
		}
	}

	@Override
	public void updateLoginToken(User user) {
		try {
			String query = "update "+this.getTableName()+" set login_token = ? where id = ?";
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getLoginToken());
			preparedStatement.setInt(2, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}finally {
			this.connectionFactory.closeConnection();
		}
	}	

	@Override
	public void validateLoginToken(User user) throws InvalidTokenException {
		User object = null;
		try {
			String query = "SELECT * FROM "+this.getTableName()+" WHERE username = ? AND login_token = ?";
			Connection connection = connectionFactory.createConnection() ;
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getLoginToken());
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				object = this.convertToObject(resultSet);
				if(object == null) {
					throw new InvalidTokenException("Invalid Token for: "+user.getLoginToken());
				}
			}
		} catch (SQLException e) {
			System.out.print("SQL Exception for : "+e.getMessage());
		}
		finally {
			this.connectionFactory.closeConnection();
		}
	}
}
