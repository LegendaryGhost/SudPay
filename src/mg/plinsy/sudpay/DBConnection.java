package mg.plinsy.sudpay;
import java.sql.*;

public class DBConnection {

	public Connection conn;
	
	public DBConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
			String url = "jdbc:postgresql://localhost:5432/sudpaydb";
			String user = "postgres";
			String passwd = "123456789";
			this.conn = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Statement getStatement() {
		try {
			return this.conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
