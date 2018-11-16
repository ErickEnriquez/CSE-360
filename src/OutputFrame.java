import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;

public class OutputFrame {

	private JFrame frame;
	private JTextPane textField;

	/**
	 * Launch the application.
	 */
	public void newScreen(String list, String criticalPath, int endTime) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutputFrame window = new OutputFrame(list, criticalPath, endTime);
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
	public OutputFrame(String list, String criticalPath, int endTime) {
		initialize(list, criticalPath, endTime);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String list, String criticalPath, int endTime) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextPane();
		textField.setBounds(12, 72, 408, 168);
		frame.getContentPane().add(textField);
		//textField.setColumns(10);
		textField.setText("Total Time: " + endTime + "\n" + criticalPath + "\n" + list);
		
		JLabel criticalPathLabel = new JLabel("All Paths");
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(29, 66, 361, 174);
		frame.getContentPane().add(textPane);
		
		
	}
}
