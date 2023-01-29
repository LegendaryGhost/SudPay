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
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void print(String str) {
		ZDialog dialog = new ZDialog(null, "Notice", true);
		ZLabel h1 = new ZLabel(str);
		dialog.add(h1);
		dialog.setPreferredSize(new Dimension(800, 400));
		dialog.setVisible(true);
	}
	
	public static Window current;
	
	public static Dimension leftDimension = new Dimension(250, 1000);
	public static Dimension labelDimension = new Dimension(200, 25);
	public static Dimension inputDimension = new Dimension(200, 25);
	
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
	private Vector<Vector<Object>> data;
	private ZModel personnelListTableModel;
	private Vector<String> title = new Vector<String>();
	
	public static int persoId = 0;
	public static JButton saveAvanceBtn = new JButton("Enregistrer");
	public static JTextField montantInput = new JTextField("");
		
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(rootPane);
		// On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());
		// On ajoute le bouton au content pane de la JFrame
		
		// Au nord
		JPanel topPanel = new JPanel();
		
		topPanel.setBackground(Color.WHITE);
		
		searchInput = new JTextField();
		searchInput.setPreferredSize(inputDimension);
		topPanel.add(searchInput);
		searchBtn = new JButton("Rechercher");
		searchBtn.addActionListener(this);
		topPanel.add(searchBtn);
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		

		
		// Au sud
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
		data = Personnel.getAll(this);
		//Les titres des colonnes 
		// {"Nom", "Prénom", "Téléphone", "Adresse", "Fonction", "Salaire", "Reste"};
		title.add("Id");
		title.add("Nom");
		title.add("Prenom");
		title.add("Téléphone");
		title.add("Adresse");
		title.add("Fonction");
		title.add("Salaire");
		title.add("Reste");
		title.add("Action");
		title.add("Action2");
		// title.add("Reste");
		
		//Nous devons utiliser un modèle d'affichage spécifique pour pallier les bugs d'affichage !
		personnelListTableModel = new ZModel(data, title);
		tableau = new JTable(personnelListTableModel);
		// tableau.removeColumn(tableau.getColumnModel().getColumn(0));
		this.tableau.setDefaultRenderer(JButton.class, new TableComponent());
		this.tableau.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		centerPanel.add(new JScrollPane(tableau));

		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		
		// À l'est
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(leftDimension);
		this.getContentPane().add(rightPanel, BorderLayout.EAST);

		this.setVisible(true);
		
		current = this;

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
					
				if(p.insert()) {
					inputNom.setText("");
					inputPrenom.setText("");
					inputTel.setText("");
					inputAdresse.setText("");
					inputFonction.setText("");
					inputSalaire.setText("");
					personnelListTableModel.addRow(res);
					data.add(res);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(arg0.getSource() == this.searchBtn) {
			Vector<Vector<Object>> res = Personnel.search(searchInput.getText(), this);
			
			for(int i = 0; i < data.size(); i++) {
				int rowId = 1;
				System.out.println("Removing row " + rowId);
				personnelListTableModel.removeRow(rowId);
			}
			
			data = res;
			
			for(int i = 1; i <= data.size(); i++) {
				personnelListTableModel.addRow(data.elementAt(i));
			}
		
			
		} else if(arg0.getSource() == saveAvanceBtn) {
			this.saveNewAvance();
		} else {
			System.out.println(arg0.getSource());
		}
		
		
	}
	
	public void showNewAvanceDialog(JTable table) {
		
		try {
			Window.persoId = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
			Personnel p = Personnel.get(persoId);
			ZDialog avanceDialog = new ZDialog(null, "Prendre une avance", true);
			
			JPanel pan1 = new JPanel();
			ZLabel pan1Title = new ZLabel("Prendre une avance");
			pan1.add(pan1Title);
			avanceDialog.add(pan1);
			
			JPanel pan2 = new JPanel();
			ZLabel pan2NomPersonnel = new ZLabel("Pour " + p);
			pan2.add(pan2NomPersonnel);
			ZLabel pan2MontantLabel = new ZLabel("Montant de l'avance: ");
			pan2.add(pan2MontantLabel);
			montantInput.setPreferredSize(Window.inputDimension);
			pan2.add(montantInput);
			saveAvanceBtn.setPreferredSize(Window.inputDimension);
			pan2.add(saveAvanceBtn);
			saveAvanceBtn.addActionListener(this);
			avanceDialog.add(pan2);
			
			avanceDialog.setVisible(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveNewAvance() {
		try {
			if(montantInput.getText().equals("")) {
				throw new Error("Veuillez entrer le montant de l'avance");
			} else {
				saveAvanceBtn.setOpaque(true);
				/*
				 * Choisi d'abord de quel salaire il s'agit
				 */
				Personnel p = Personnel.get(persoId);
				int montant = Integer.parseInt(montantInput.getText());
				
				Salaire s = p.getSalaire(montant);
				
				Avance a = new Avance(null, montant + "", s.id + "", persoId + "");
				if(a.insert()) {
					ZDialog.current.dispose();
					int rowId = tableau.getSelectedRow();
					personnelListTableModel.removeRow(rowId);
					Vector<Object> o = new Vector<Object>();
					o.add(p.id);
					o.add(p.nom);
					o.add(p.prenom);
					o.add(p.tel);
					o.add(p.adresse);
					o.add(p.fonction);
					o.add(p.salaire);
					o.add(p.getReste());
					o.add(p.newAvanceBtn);
					personnelListTableModel.insertRow(rowId, o);
				}
			}
			
		} catch(Exception e) {
			Window.print(e.toString());
			e.printStackTrace();
		}
	}
}
