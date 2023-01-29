package mg.plinsy.sudpay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;

public class Personnel {
	
	public int id;
	public String nom;
	public String prenom;
	public String tel;
	public String adresse;
	public String fonction;
	/**
	 * Le salaire ne doit pas contenir d'espace
	 */
	public float salaire;
	public JButton newAvanceBtn = new JButton("Avance");
	
	public Personnel(String id, String nom, String prenom, String tel, String adresse, String fonction, String salaire) {
		
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setTel(tel);
		setAdresse(adresse);
		setFonction(fonction);
		setSalaire(salaire);
		
	}
	
	public Personnel(ResultSet res) {
		try {
			setId(res.getString("id"));
			setNom(res.getString("nom"));
			setPrenom(res.getString("prenom"));
			setTel(res.getString("tel"));
			setAdresse(res.getString("adresse"));
			setFonction(res.getString("fonction"));
			setSalaire(res.getString("salaire"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Personnel(ResultSet res, Window win) {
		try {
			setId(res.getString("id"));
			setNom(res.getString("nom"));
			setPrenom(res.getString("prenom"));
			setTel(res.getString("tel"));
			setAdresse(res.getString("adresse"));
			setFonction(res.getString("fonction"));
			setSalaire(res.getString("salaire"));
			this.newAvanceBtn.addActionListener(win);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Personnel(String id, String nom, String prenom, String tel, String adresse, String fonction, String salaire, Window win) {
		
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setTel(tel);
		setAdresse(adresse);
		setFonction(fonction);
		setSalaire(salaire);
		this.newAvanceBtn.addActionListener(win);
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
			db.getStatement().executeUpdate(sql);
			this.createFirstSalaire();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void createFirstSalaire() {
		Salaire s = new Salaire(null, this.id + "", this.salaire + "", null);
		s.insert();
	}
	
	public static Vector<Vector<Object>> getAll(Window win) {
		Vector<Vector<Object>> res = new Vector<Vector<Object>>();
		String sql = "SELECT * FROM personnel LIMIT 10;";
		
		try {
			DBConnection db = new DBConnection();
			
			ResultSet dbRes = db.getStatement().executeQuery(sql);
			
			while(dbRes.next()) {
				Vector<Object> o = new Vector<Object>();
				Personnel p = new Personnel(dbRes, win);
				o.add(p.id);
				o.add(p.nom);
				o.add(p.prenom);
				o.add(p.tel);
				o.add(p.adresse);
				o.add(p.fonction);
				o.add(p.salaire);
				o.add(p.getReste());
				o.add(p.newAvanceBtn);
				res.add(o);
			}
			
		} catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return res;
	}
	
	public float getReste() {
		float res = 0;
		Salaire s = this.getSalaire();
		/**
		 * Récupérer les avances du salaire sinon s.avances sera vide
		 */
		Vector<Avance> avances = s.getAvances();
		/**
		 * Par ex la personne a un salaire de 5.000Ar
		 * Donc le reste commence à 5.000Ar
		 */
		res = this.salaire;
		/**
		 * Déduire le montantInitial des montants des avances 
		 * liées à ce salaire et à cette personnel
		 */
		for(int i = 0; i < avances.size(); i++) {
			res -= avances.elementAt(i).montant;
		}
		
		return res;
	}
	
	public static Vector<Vector<Object>> search(String q, Window win) {
		Vector<Vector<Object>> res = new Vector<Vector<Object>>();
		
		
		String sql = "SELECT * FROM personnel "
				+ "WHERE LOWER(nom) LIKE '%"
				+ q.toLowerCase()
				+ "%'"
				+ " LIMIT 10;";
		
		try {
			DBConnection db = new DBConnection();
			
			ResultSet dbRes = db.getStatement().executeQuery(sql);
			
			while(dbRes.next()) {
				Vector<Object> o = new Vector<Object>();
				Personnel p = new Personnel(dbRes, win);
				o.add(p.id);
				o.add(p.nom);
				o.add(p.prenom);
				o.add(p.tel);
				o.add(p.adresse);
				o.add(p.fonction);
				o.add(p.salaire);
				o.add(p.getReste());
				o.add(p.newAvanceBtn);
				res.add(o);
			}
			
		} catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
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
	
	public static Personnel get(int id) {
		Personnel p = null;
		String sql = "";
		
		try {
			
			sql = "SELECT * FROM personnel WHERE id=" + id + " LIMIT 1;";
			
			DBConnection db = new DBConnection();
			
			ResultSet res = db.getStatement().executeQuery(sql);
			
			while(res.next()) {
				p = new Personnel(res);
			}
			
		} catch(Exception e) {
			System.out.println(sql);
		}
		
		return p;
	}
	
	public void setId(String id) {
		this.id = id != null ? Integer.parseInt(id) : 0;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getFullName() {
		return this.nom + " " + this.prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
	public void setSalaire(String salaire) {
		this.salaire = Float.parseFloat(salaire);
	}
	
	public String toString() {
		return nom + " " + prenom + " " + fonction;
	}
	
	public Salaire getSalaire(int montant) {
		Salaire s = new Salaire();
		String sql = "";
		
		try {
			
			sql = "SELECT * FROM salaire WHERE id_posseder=" + id + " "
					+ " AND montant >= 0"
					+ " ORDER BY date DESC LIMIT 1;";
			
			DBConnection db = new DBConnection();
			
			ResultSet res = db.getStatement().executeQuery(sql);
			
			while(res.next()) {
				s = new Salaire(res);
			}
			
		} catch(Exception e) {
			Window.print(sql);
			e.printStackTrace();
		}
		
		return s;
	}
	
	public Salaire getSalaire() {
		Salaire s = new Salaire();
		String sql = "";
		
		try {
			
			sql = "SELECT * FROM salaire WHERE id_posseder=" + id + ""
					+ " ORDER BY date DESC LIMIT 1;";
			
			DBConnection db = new DBConnection();
			
			ResultSet res = db.getStatement().executeQuery(sql);
			
			while(res.next()) {
				s = new Salaire(res);
			}
			
		} catch(Exception e) {
			Window.print(sql);
			e.printStackTrace();
		}
		
		return s;
	}
}
