package mg.plinsy.sudpay;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

	private String email;
	private String password;
	private boolean isAdmin;
	private DBConnection dbConnect;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.isAdmin = false;
		dbConnect = new DBConnection();
	}

	public boolean connect() {
		String sql = "";
		
		try {
			sql = "SELECT * FROM users WHERE email = '" + email + "' LIMIT 1;";
			ResultSet res = dbConnect.getStatement().executeQuery(sql);
			res.next();
			isAdmin = res.getBoolean("is_admin");
			return password.equals(res.getString("password"));
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
}
