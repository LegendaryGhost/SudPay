package mg.plinsy.sudpay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

//Notre listener pour le bouton
class ButtonListener implements ActionListener{
	
	private int column, row;
	private JTable table;
	private int nbre = 0;
	
	public void setColumn(int col){this.column = col;}
	
	public void setRow(int row){this.row = row;}
	
	public void setTable(JTable table){this.table = table;}
	
	public void actionPerformed(ActionEvent event) {
		System.out.println(table.getSelectedRow());
	}
}
