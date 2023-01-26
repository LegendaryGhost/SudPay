package mg.plinsy.sudpay;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	
		protected JButton button;
		private boolean isPushed;
		private ButtonListener bListener = new ButtonListener();
		
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(bListener);
		}
		
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			bListener.setRow(row);
			bListener.setColumn(column);
			bListener.setTable(table);
			button.setText( (value == null) ? "" : value.toString() );

			return button;
		}
		
}
