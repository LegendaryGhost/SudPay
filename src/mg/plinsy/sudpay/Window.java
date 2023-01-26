package mg.plinsy.sudpay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Window extends JFrame implements ActionListener {
	
	private Dimension leftDimension = new Dimension(250, 1000);
	private Dimension labelDimension = new Dimension(200, 25);
	private Dimension inputDimension = new Dimension(200, 25);
	
	private JTextField inputNom;
	private JTextField inputPrenom;
	private JTextField inputTel;
	private JTextField inputAdresse;
	private JTextField inputFonction;
	private JTextField inputSalaire;
	private JTextField searchInput;
	private JButton submitBtn;
	private JButton searchBtn;
	private JTable tableau;
	private Vector<Vector<Object>> data;
	private ZModel personnelListTableModel;
	private Vector<String> title = new Vector<String>();
		
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());
		//On ajoute le bouton au content pane de la JFrame
		
		//Au nord
		JPanel topPanel = new JPanel();
		
		topPanel.setBackground(Color.WHITE);
		
		searchInput = new JTextField();
		searchInput.setPreferredSize(inputDimension);
		topPanel.add(searchInput);
		searchBtn = new JButton("Rechercher");
		searchBtn.addActionListener(this);
		topPanel.add(searchBtn);
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		
		
		//Au sud
		this.getContentPane().add(new JButton("SOUTH"),
		BorderLayout.SOUTH);
		
		
		// À l'ouest
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(leftDimension);
		
		JLabel labelNom = new JLabel();
		labelNom.setText("Nom");
		labelNom.setPreferredSize(labelDimension);
		leftPanel.add(labelNom);
		inputNom = new JTextField();
		inputNom.setPreferredSize(inputDimension);
		leftPanel.add(inputNom);
		
		JLabel labelPrenom = new JLabel();
		labelPrenom.setText("Prenom");
		labelPrenom.setPreferredSize(labelDimension);
		leftPanel.add(labelPrenom);
		inputPrenom = new JTextField();
		inputPrenom.setPreferredSize(inputDimension);
		leftPanel.add(inputPrenom);
		
		JLabel labelTel = new JLabel();
		labelTel.setText("Téléphone");
		labelTel.setPreferredSize(labelDimension);
		leftPanel.add(labelTel);
		inputTel = new JTextField();
		inputTel.setPreferredSize(inputDimension);
		leftPanel.add(inputTel);
		
		JLabel labelAdresse = new JLabel();
		labelAdresse.setText("Adresse");
		labelAdresse.setPreferredSize(labelDimension);
		leftPanel.add(labelAdresse);
		inputAdresse = new JTextField();
		inputAdresse.setPreferredSize(inputDimension);
		leftPanel.add(inputAdresse);
		
		JLabel labelFonction = new JLabel();
		labelFonction.setText("Fonction");
		labelFonction.setPreferredSize(labelDimension);
		leftPanel.add(labelFonction);
		inputFonction = new JTextField();
		inputFonction.setPreferredSize(inputDimension);
		leftPanel.add(inputFonction);
		
		JLabel labelSalaire = new JLabel();
		labelSalaire.setText("Salaire");
		labelSalaire.setPreferredSize(labelDimension);
		leftPanel.add(labelSalaire);
		inputSalaire = new JTextField();
		inputSalaire.setPreferredSize(inputDimension);
		leftPanel.add(inputSalaire);
		
		submitBtn = new JButton("Valider");
		submitBtn.setPreferredSize(labelDimension);
		JLabel labelDivider = new JLabel();
		labelDivider.setText("");
		labelDivider.setPreferredSize(labelDimension);
		leftPanel.add(labelDivider);
		leftPanel.add(submitBtn);
		
		submitBtn.addActionListener(this);
		
		this.getContentPane().add(leftPanel, BorderLayout.WEST);
		
		
		// Au centre
		//Les données du tableau
		data = Personnel.getAll();
		//Les titres des colonnes 
		// {"Nom", "Prénom", "Téléphone", "Adresse", "Fonction", "Salaire", "Reste"};
		title.add("Id");
		title.add("Nom");
		title.add("Prenom");
		title.add("Téléphone");
		title.add("Adresse");
		title.add("Fonction");
		title.add("Salaire");
		title.add("Action");
		title.add("Action2");
		// title.add("Reste");
		
		//Nous devons utiliser un modèle d'affichage spécifique pour pallier les bugs d'affichage !
		personnelListTableModel = new ZModel(data, title);
		tableau = new JTable(personnelListTableModel);
		tableau.setRowHeight(25);
		tableau.removeColumn(tableau.getColumnModel().getColumn(0));
		
		TableComponent tableComponent = new TableComponent();
		
		this.tableau.setDefaultRenderer(JButton.class, tableComponent);
		TableColumn actionCol = this.tableau.getColumn("Action");
		actionCol.setCellEditor(new ButtonEditor(new JCheckBox()));
		actionCol.setPreferredWidth(175);
		
		TableColumn action2Col = this.tableau.getColumn("Action2");
		action2Col.setCellEditor(new ButtonEditor(new JCheckBox()));
		action2Col.setPreferredWidth(150);

		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		
		//Au nord
		JPanel rightPanel = new JPanel();
		
		rightPanel.setPreferredSize(leftDimension);
		
		this.getContentPane().add(rightPanel, BorderLayout.EAST);

	}
	
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == this.submitBtn) {
			Vector<Object> res = new Vector<Object>();
			try {
				Personnel p = new Personnel(
					null,
					inputNom.getText(),
					inputPrenom.getText(),
					inputTel.getText(),
					inputAdresse.getText(),
					inputFonction.getText(),
					inputSalaire.getText()
				);
				
				res.add(inputNom.getText());
				res.add(inputPrenom.getText());
				res.add(inputTel.getText());
				res.add(inputAdresse.getText());
				res.add(inputFonction.getText());
				res.add(inputSalaire.getText());
				res.add(new JButton("Supprimer"));
				res.add(new JButton("Détails"));
				
				personnelListTableModel.addRow(res);
					
				if(p.insert()) {
					inputNom.setText("");
					inputPrenom.setText("");
					inputTel.setText("");
					inputAdresse.setText("");
					inputFonction.setText("");
					inputSalaire.setText("");
				}
			} catch(Exception e) {
				
				
				
			}
		} else if(arg0.getSource() == this.searchBtn) {
			Vector<Vector<Object>> res = Personnel.search(searchInput.getText());
			
			for(int i = data.size() - 1; i >= 0 ;i--) {
				personnelListTableModel.removeRow(0);
			}
			
			data = res;
			
			for(int i = 0; i< data.size(); i++) {
				personnelListTableModel.addRow(data.elementAt(i));
			}
		
		}
			
	}
	
}
