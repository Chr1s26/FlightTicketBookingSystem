package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;
import Model.UserPolicy;

public class UserPolicyDaoImpl extends UserPolicyDao {

	@Override
	public String getTableName() {
		return "user_policies";
	}

	@Override
	public UserPolicy convertToObject(ResultSet resultset) {
		UserPolicy userPolicy = new UserPolicy();
		try {
			userPolicy.setId(resultset.getInt("id"));;
			userPolicy.setname(resultset.getString("name"));
			userPolicy.setDescription(resultset.getString("description"));;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPolicy;
	}

	@Override
	public String getInsertQuery() {
	
		return "Insert Into "+this.getTableName()+" (name,description) Values (?,?)";
	}

	@Override
	public String getUpdateQuery() {
		return "update "+this.getTableName()+" set name = ?, description = ? where id = ?";
	}

	@Override
	public void prepareParams(PreparedStatement preparedStatement, UserPolicy object) {
		try {
			preparedStatement.setString(1, object.getname());
			preparedStatement.setString(2, object.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void prepareParamsForUpdate(PreparedStatement preparedStatement, UserPolicy object) {
		try {
			preparedStatement.setString(1, object.getname());
			preparedStatement.setString(2, object.getDescription());
			preparedStatement.setInt(3, object.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
