package mg.plinsy.sudpay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Window extends JFrame implements ActionListener {
	
	private Dimension leftDimension = new Dimension(250, 1000);
	private Dimension labelDimension = new Dimension(200, 25);
	private Dimension inputDimension = new Dimension(200, 25);
	
	private JPanel centerPanel;
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
	private Vector<Vector<String>> data;
	private DefaultTableModel personnelListTableModel;
	private Vector<String> title = new Vector<String>();
		
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());
		//On ajoute le bouton au content pane de la JFrame
		
		//Au nord
		JPanel topPanel = new JPanel();
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
		centerPanel = new JPanel();
//		centerPanel.setPreferredSize();
		centerPanel.setBackground(Color.BLACK);
		//Les données du tableau
		data = Personnel.getAll();
		//Les titres des colonnes 
		// {"Nom", "Prénom", "Téléphone", "Adresse", "Fonction", "Salaire", "Reste"};
		title.add("Nom");
		title.add("Prenom");
		title.add("Téléphone");
		title.add("Adresse");
		title.add("Fonction");
		title.add("Salaire");
//		title.add("Reste");
		personnelListTableModel = new DefaultTableModel(data, title);
		tableau = new JTable(personnelListTableModel);
		centerPanel.add(new JScrollPane(tableau));

		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
		// À l'est
		this.getContentPane().add(new JButton("EAST"),
		BorderLayout.EAST);
		this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == this.submitBtn) {
			Vector<String> res = new Vector<String>();
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
			Vector<Vector<String>> res = Personnel.search(searchInput.getText());
			
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
