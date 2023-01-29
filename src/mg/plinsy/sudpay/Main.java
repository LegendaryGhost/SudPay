package mg.plinsy.sudpay;

import java.awt.Dimension;

public class Main {

	public static void main(String[] args) {
		LoginWindow logWin = new LoginWindow();
		logWin.setTitle("Sudpay");
		Dimension loginDimension = new Dimension(250, 375);
		logWin.setSize(loginDimension);
		logWin.setMinimumSize(loginDimension);
		logWin.setVisible(true);
	}

}
