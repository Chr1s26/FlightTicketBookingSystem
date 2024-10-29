package Service;

import Dao.UserDaoImpl;
import Model.User;
import exception.IncorrectPasswordException;
import exception.IncorrectUserNameException;
import exception.InvalidTokenException;

public class AuthenticationService {
	
	private static User currentUser;
	private static final UserDaoImpl userDao =  new UserDaoImpl();;
	
	
	public static void login(String username, String password) throws IncorrectUserNameException, IncorrectPasswordException {
		currentUser = userDao.validateUser(username, password);
	}
	
	public static void authenticate() throws InvalidTokenException{
		userDao.validateLoginToken(currentUser);
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		AuthenticationService.currentUser = currentUser;
	}

}
