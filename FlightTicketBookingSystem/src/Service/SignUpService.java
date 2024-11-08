package Service;

import java.util.ArrayList;
import java.util.List;

import Dao.UserDaoImpl;
import Model.User;
import exception.SignUpException;
import util.PasswordUtil;
import util.TokenUtil;

public class SignUpService {
	
	private List<String> errorMessage;
	private UserDaoImpl userDao;
	private DTO.User signUpUser;
	
	public SignUpService() {
		this.userDao = new UserDaoImpl();
		errorMessage = new ArrayList<String>();
	}
	
	public void call(DTO.User signUp)throws SignUpException  {
		this.signUpUser = signUp;
		this.validatePassWord();
		this.validateDuplicateUsername();
		this.validateDuplicateEmail();
		if(!this.errorMessage.isEmpty()) {
			throw new SignUpException(String.join("\n",this.errorMessage));
		}
		this.createUser();
	}
	
	public void createUser() {
		User user = new User();
		user.setName(signUpUser.getUsername());
		user.setEmail(signUpUser.getEmail());
		user.setPasswordHash(PasswordUtil.hashPassword(signUpUser.getPassword()));
		user.setPolicy(signUpUser.getUserPolicy());
		this.userDao.create(user);
	}
	
	public void validateDuplicateEmail() {
		User user = this.userDao.getUserByUserEmail(this.signUpUser.getEmail());
		if(user != null) {
			errorMessage.add("Email already exists !!");
		}
	}
	
	public void validateDuplicateUsername()  {
		User user = this.userDao.getUserByUserName(this.signUpUser.getUsername());
		if(user != null) {
			errorMessage.add("Username already exists");
		}
	}
	
	public void validatePassWord() {
		if(!signUpUser.getPassword().equals(signUpUser.getConfirmPassword())) {
			errorMessage.add("Password is not same. Please check password again!");
		}
	}
	
}
