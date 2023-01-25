package mg.plinsy.sudpay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Personnel {
	
	public int id;
	public String nom;
	public String prenom;
	public String tel;
	public String adresse;
	public String fonction;
	public float salaire;
	
	public Personnel(String id, String nom, String prenom, String tel, String adresse, String fonction, String salaire) {
		
		this.id = Integer.parseInt(id);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		this.fonction= fonction;
		this.salaire = Float.parseFloat(salaire);
		
	}
	
	public boolean insert() {
		
		DBConnection db = new DBConnection();
		String sql = "";
		
		try {
			sql = "INSERT INTO personnel (nom, prenom, tel, adresse, fonction, salaire) VALUES ("
				+ "'"
				+ this.nom
				+ "', '"
				+ this.prenom
				+ "', '"
				+ this.tel
				+ "', '"
				+ this.adresse
				+ "', '"
				+ this.fonction
				+ "', "
				+ this.salaire
				+ ");";
			ResultSet res = db.getStatement().executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static Vector<Vector<String>> getAll() {
		Vector<Vector<String>> res = new Vector<Vector<String>>();
		String sql = "SELECT * FROM personnel LIMIT 10;";
		
		try {
			DBConnection db = new DBConnection();
			
			ResultSet dbRes = db.getStatement().executeQuery(sql);
			
			while(dbRes.next()) {
				Vector<String> o = new Vector<String>();
				o.add(dbRes.getString("nom"));
				o.add(dbRes.getString("prenom"));
				o.add(dbRes.getString("tel"));
				o.add(dbRes.getString("adresse"));
				o.add(dbRes.getString("fonction"));
				o.add(dbRes.getString("salaire"));
				res.add(o);
			}
			
		} catch(Exception e) {
			System.out.println(sql);
		}
		
		return res;
	}
	
	public static Vector<Vector<String>> search(String q) {
		Vector<Vector<String>> res = new Vector<Vector<String>>();
		
		
		String sql = "SELECT * FROM personnel "
				+ "WHERE LOWER(nom) LIKE '%"
				+ q.toLowerCase()
				+ "%'"
				+ " LIMIT 10;";
		
		try {
			DBConnection db = new DBConnection();
			
			ResultSet dbRes = db.getStatement().executeQuery(sql);
			
			while(dbRes.next()) {
				Vector<String> o = new Vector<String>();
				o.add(dbRes.getString("nom"));
				o.add(dbRes.getString("prenom"));
				o.add(dbRes.getString("tel"));
				o.add(dbRes.getString("adresse"));
				o.add(dbRes.getString("fonction"));
				o.add(dbRes.getString("salaire"));
				res.add(o);
			}
			
		} catch(Exception e) {
			System.out.println(sql);
		}
		
		return res;
	}
	
	public Salaire Salaire() {
		String sql = "";
		Salaire s = null;
		
		try {
			
			sql = "SELECT * FROM salaire WHERE id_posseder=" + this.id + " LIMIT 1;";
			
			DBConnection db = new DBConnection();
			
			ResultSet res = db.getStatement().executeQuery(sql);
			
			while(res.next()) {
				s = new Salaire(
					res.getString("id"),
					res.getString("id_posseder"),
					res.getString("montant"),
					res.getString("date")
				);
			}
			
		} catch(Exception e) {
			System.out.println(sql);
		}
		
		return s;
	}

}
