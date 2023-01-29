package mg.plinsy.sudpay;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Avance {

	public int id;
	public Date date;
	public float montant;
	public int id_concerner;
	public int id_prendre;
	
	public Avance(String id, String montant, String id_concerner, String id_prendre) {
		setId(id);
		setDate(null);
		setMontant(montant);
		setIdConcerner(id_concerner);
		setIdPrendre(id_prendre);
	}
	
	public Avance(String id, String montant, String id_concerner, String id_prendre, String date) {
		setId(id);
		setDate(date);
		setMontant(montant);
		setIdConcerner(id_concerner);
		setIdPrendre(id_prendre);
	}
	
	public Avance(ResultSet res) {
		try {
			setId(res.getString("id"));
			setIdConcerner(res.getString("id_concerner"));
			setIdPrendre(res.getString("id_prendre"));
			setDate(res.getString("date"));
			setMontant(res.getString("montant"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setIdPrendre(String id_prendre) {
		this.id_prendre = Integer.parseInt(id_prendre);
	}
	
	public void setIdConcerner(String id_concerner) {
		this.id_concerner = id_concerner == null ? 0 : Integer.parseInt(id_concerner);
	}
	
	public void setMontant(String montant) {
		this.montant = Float.parseFloat(montant);
	}
	
	public void setDate(String date) {
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date == null ? "01/26/2023" : date.replaceAll("-", "/"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setId(String id) {
		this.id = id == null ? 0 : Integer.parseInt(id);
	}
	
	public boolean insert() {
		
		DBConnection db = new DBConnection();
		String sql = "";
		
		try {
			sql = "INSERT INTO avance (montant, id_concerner, id_prendre) VALUES ("
				+ this.montant
				+ ", "
				+ this.id_concerner
				+ ", "
				+ this.id_prendre
				+ ");";
			db.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		return "id: " + id + ", date: " + date + ", montant: " + montant;
	}
}
