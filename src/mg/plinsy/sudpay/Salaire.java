package mg.plinsy.sudpay;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Salaire {
	
	public int id;
	public int id_posseder;
	public float montant;
	public Date date = null;
	
	public Salaire(String id, String id_posseder, String montant, String date) {
		
		this.id = Integer.parseInt(id);
		this.id_posseder = Integer.parseInt(id_posseder);
		this.montant = Float.parseFloat(montant);
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
