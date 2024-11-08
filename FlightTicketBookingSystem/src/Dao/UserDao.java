package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;
import exception.IncorrectPasswordException;
import exception.IncorrectUserNameException;
import exception.InvalidTokenException;

public abstract class UserDao extends AbstractDao<User> {

	public abstract User getUserByUserName(String username);
	public abstract User validateUser(String username,String password) throws IncorrectUserNameException , IncorrectPasswordException;
	public abstract void updateLoginToken(User user);
	public abstract void validateLoginToken(User user)throws InvalidTokenException;
	public abstract User getUserByUserEmail(String email);

}
