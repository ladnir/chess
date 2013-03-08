import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Draw extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JButton b;
	private MyPanel panel;
	

	
	public Draw(){
		super("Chess");
		setSize(500, 500);
		setVisible(true);
		panel = new MyPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
	}

	
	
}
