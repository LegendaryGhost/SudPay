package mg.plinsy.sudpay;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableComponent extends DefaultTableCellRenderer{
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
		//On écrit dans le bouton ce que contient la cellule
		return (value instanceof JButton) ? (JButton) value : this;
	}

}
