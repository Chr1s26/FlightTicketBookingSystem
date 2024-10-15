package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSqlConnectionFactory implements ConnectionFactory{
    private static final String URL = "jdbc:postgresql://localhost:5432/flight_ticket_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "682682";
    private Connection connection;
    

    public Connection createConnection() throws SQLException {
     this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return this.connection;
    }
    
    public void closeConnection() {
    	try {
    		this.connection.close();
    	}catch (SQLException e) {
			System.out.print(e.getMessage());
		}
    }
}