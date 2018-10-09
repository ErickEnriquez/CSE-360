import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OutputFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutputFrame window = new OutputFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OutputFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 72, 408, 168);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("output will go here");
		
		JLabel criticalPathLabel = new JLabel("critical path");
		criticalPathLabel.setForeground(Color.RED);
		criticalPathLabel.setBounds(166, 0, 109, 59);
		frame.getContentPane().add(criticalPathLabel);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "The output in this tab is the ciritical path of the network along with the total time it takes");
			}
		});
		helpButton.setBounds(293, 34, 97, 25);
		frame.getContentPane().add(helpButton);
	}
}
