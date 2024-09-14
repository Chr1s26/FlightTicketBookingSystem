package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionFactory implements ConnectionFactory {
	
		private static final String  URL = "jdbc:postgresql://localhost:5432/vehiclemanagementsystem";
		private static final String USERNAME = "postgres";
		private static final String PASSWORD = "root";
		
		static {
			try {
				
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		@Override
		public Connection createConnection() throws SQLException {
			// Establish connection to PostgreSQL
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}
}
