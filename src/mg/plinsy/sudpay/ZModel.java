package mg.plinsy.sudpay;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ZModel extends DefaultTableModel{
	
	private Vector<Vector<Object>> data;
	private Vector<String> title;
	
	//Constructeur
	public ZModel(Vector<Vector<Object>> data, Vector<String> title){
		this.data = data;
		this.title = title;
	}
	
	//Retourne le titre de la colonne à l'indice spécifié
	public String getColumnName(int col) {
		return this.title.get(col);
	}
	
	//Retourne le nombre de colonnes
	public int getColumnCount() {
		return this.title.size();
	}
	
	//Retourne le nombre de lignes
	public int getRowCount() {
		return this.data == null ? 0 : this.data.size();
	}
	
	//Retourne la valeur à l'emplacement spécifié
	public Object getValueAt(int row, int col) {
		return this.data.get(row).get(col);
	}
	
	//Définit la valeur à l'emplacement spécifié
	public void setValueAt(Object value, int row, int col) {
		//On interdit la modification sur certaines colonnes !
		if(!this.getColumnName(col).equals("Age"))
			this.data.get(row).set(col, value);
	}
	
	//Retourne la classe de la donnée de la colonne
	public Class getColumnClass(int col){
		//On retourne le type de la cellule à la colonne demandée
		//On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
		//On choisit donc la première ligne
		return this.data.get(0).get(col).getClass();
	}
	
	public boolean isCellEditable(int row, int col){
		return true;
	}
	
}
