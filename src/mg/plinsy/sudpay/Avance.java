package mg.plinsy.sudpay;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Avance {

	public int id;
	public Date date;
	public float montant;
	public int id_concerner;
	public int id_prendre;
	
	public Avance(String id, String montant, String id_concerner, String id_prendre) {
		
		this.id = Integer.parseInt(id);
		this.date = null;
		this.montant = Float.parseFloat(montant);
		this.id_concerner = Integer.parseInt(id_concerner);
		this.id_prendre = Integer.parseInt(id_prendre);
		
	}
	
	public boolean insert() {
		
		DBConnection db = new DBConnection();
		String sql = "";
		
		try {
			sql = "INSERT INTO personnel (montant, id_concerner, id_prendre) VALUES ("
				+ this.montant
				+ ", "
				+ this.id_concerner
				+ ", "
				+ this.id_prendre
				+ ");";
			ResultSet res = db.getStatement().executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
