package assign;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String args[])
	{
		JFrame frm = new GUISolution("map1.csv");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}
}
