package mg.plinsy.sudpay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginWindow extends JFrame implements ActionListener {
	
	private Dimension paneDimension = new Dimension(250, 1000);
	private Dimension labelDimension = new Dimension(200, 25);
	private Dimension inputDimension = new Dimension(200, 25);

	private JTextField inputEmail;
	private JTextField inputPassword;
	private JButton submitBtn;
	
	public LoginWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(paneDimension);
		
		JLabel labelDivider = new JLabel();
		labelDivider.setText("");
		labelDivider.setPreferredSize(labelDimension);
		panel.add(labelDivider);
		
		JLabel labelTitle = new JLabel();
		labelTitle.setText("Login Sudpay");
		labelTitle.setPreferredSize(labelDimension);
		labelTitle.setFont(new Font("sans-serif", Font.BOLD, 20));
		panel.add(labelTitle);
		
		JLabel labelDivider2 = new JLabel();
		labelDivider2.setText("");
		labelDivider2.setPreferredSize(labelDimension);
		panel.add(labelDivider2);
		
		JLabel labelEmail = new JLabel();
		labelEmail.setText("Nom");
		labelEmail.setPreferredSize(labelDimension);
		panel.add(labelEmail);
		inputEmail = new JTextField("rihantiana@gmail.com");
		inputEmail.setPreferredSize(inputDimension);
		panel.add(inputEmail);
		
		JLabel labelPassword = new JLabel();
		labelPassword.setText("Prenom");
		labelPassword.setPreferredSize(labelDimension);
		panel.add(labelPassword);
		inputPassword = new JTextField("1234");
		inputPassword.setPreferredSize(inputDimension);
		panel.add(inputPassword);
		
		submitBtn = new JButton("Valider");
		submitBtn.setPreferredSize(labelDimension);
		JLabel labelDivider3 = new JLabel();
		labelDivider3.setText("");
		labelDivider3.setPreferredSize(labelDimension);
		panel.add(labelDivider3);
		panel.add(submitBtn);
		
		submitBtn.addActionListener(this);
		
		this.getContentPane().add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!("").equals(inputEmail.getText()) && !("").equals(inputPassword.getText())) {
			User user = new User(inputEmail.getText(), inputPassword.getText());
			boolean connected = user.connect();
			System.out.println(connected);
			if(connected) {
				if(user.isAdmin()) {
					loadWindowForAdmin();
				} else {
					loadWindowForEmployee();
				}
				this.setVisible(false);
			}
		}
	}
	
	
	public void loadWindowForAdmin() {
		Window win = new Window();
		win.setTitle("Sudpay");
		win.setSize(new Dimension(1000, 550));
		win.setMinimumSize(new Dimension(1000, 550));
		win.setVisible(true);
	}
	
	public void loadWindowForEmployee() {
		
	}
	
}
