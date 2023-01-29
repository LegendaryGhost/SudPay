package mg.plinsy.sudpay;

import java.util.Date;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Salaire {
	
	public int id;
	public int id_posseder;
	public float montant;
	public Date date = null;
	public Vector<Avance> avances = new Vector<Avance>();
	
	public Salaire() {
		setId(null);
		setIdPosseder(null);
		setMontant(null);
		setDate(null);
	}
	
	public Salaire(String id, String id_posseder, String montant, String date) {
		
		try {
			setId(id);
			setIdPosseder(id_posseder);
			setMontant(montant);
			setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "id: " + id + ", id_posseder: " + id_posseder + ", montant: " + montant;
	}
	
	public Salaire(ResultSet res) {
		try {
			setId(res.getString("id"));
			setIdPosseder(res.getString("id_posseder"));
			setMontant(res.getString("montant"));
			setDate(res.getString("date"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Avance> getAvances() {
		Vector<Avance> r = new Vector<Avance>();
		String sql = "";
		
		try {
			
			DBConnection db = new DBConnection();
			
			sql = "SELECT * FROM avance WHERE id_concerner=" + id + ";";
			
			ResultSet res = db.getStatement().executeQuery(sql);
			
			while(res.next()) {
				r.add(new Avance(res));
			}
			
			avances = r;
			
		} catch(Exception e) {
			Window.print(sql);
			e.printStackTrace();
		}
		
		return r;
	}
	
	public boolean insert() {
		String sql = "";
		
		try {
			DBConnection db = new DBConnection();
			
			sql = "INSERT INTO salaire (id_posseder, montant, date) VALUES ("
				+ ""
				+ id_posseder
				+ ", '"
				+ montant
				+ "', "
				+ "DEFAULT"
				+ ");";
			db.getStatement().executeUpdate(sql);
			
		} catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void setDate(String date) {
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date == null ? "01/26/2023" : date.replaceAll("-", "/"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setMontant(String montant) {
		this.montant = montant == null ? 0 : Float.parseFloat(montant);
	}
	
	public void setIdPosseder(String id_posseder) {
		this.id_posseder = id_posseder == null ? 0 : Integer.parseInt(id_posseder);
	}
	
	public void setId(String id) {
		this.id = id == null ? 0 : Integer.parseInt(id);
	}
	

}
