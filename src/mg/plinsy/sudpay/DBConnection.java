package mg.plinsy.sudpay;
import java.sql.*;

public class DBConnection {

	public Connection conn;
	
	public DBConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5434/sudpaydb";
			String user = "postgres";
			String passwd = "xnil";
			this.conn = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			Window.print(e.toString());
			e.printStackTrace();
		}
		
	}
	
	public Statement getStatement() {
		try {
			if(this.conn == null) {
				throw new Exception("Conn is null");
			}
			return this.conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
