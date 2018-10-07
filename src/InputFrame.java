import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InputFrame {

	private JFrame frame;
	private JTextField ActivityField;
	private JTextField DurationField;
	private JTextField DependenciesField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputFrame window = new InputFrame();
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
	public InputFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 689, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel activityLabel = new JLabel("Activity Name");
		activityLabel.setBounds(43, 47, 92, 16);
		frame.getContentPane().add(activityLabel);
		
		ActivityField = new JTextField();
		ActivityField.setBounds(40, 66, 195, 46);
		frame.getContentPane().add(ActivityField);
		ActivityField.setColumns(10);
		
		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setBounds(43, 141, 56, 16);
		frame.getContentPane().add(durationLabel);
		
		DurationField = new JTextField();
		DurationField.setBounds(43, 164, 195, 46);
		frame.getContentPane().add(DurationField);
		DurationField.setColumns(10);
		
		JLabel dependenciesLabel = new JLabel("Dependecies");
		dependenciesLabel.setBounds(43, 253, 116, 16);
		frame.getContentPane().add(dependenciesLabel);
		
		DependenciesField = new JTextField();
		DependenciesField.setBounds(43, 279, 195, 46);
		frame.getContentPane().add(DependenciesField);
		DependenciesField.setColumns(10);
		
		JButton proccessButton = new JButton("Proccess");
		proccessButton.setBounds(368, 47, 186, 65);
		frame.getContentPane().add(proccessButton);
		
		JButton restartButton = new JButton("Restart");
		restartButton.setBounds(368, 155, 186, 65);
		frame.getContentPane().add(restartButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			/*If the user clicks the quit button end the program*/
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitButton.setBounds(368, 270, 186, 65);
		frame.getContentPane().add(quitButton);
		
		JButton helpButton = new JButton("Help");
		helpButton.setBounds(457, 400, 97, 25);
		frame.getContentPane().add(helpButton);
		
		JButton aboutButton = new JButton("About");
		aboutButton.setBounds(562, 400, 97, 25);
		frame.getContentPane().add(aboutButton);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			/*once the user has entered the data in the provided fields. retrieve it and clear the fields*/
			public void actionPerformed(ActionEvent e) {
				String activity = ActivityField.getText();
				String duration = DurationField.getText();
				// not sure how to do dependencies
				if(isNumeric(duration) == false) {
					JOptionPane.showMessageDialog(null, "Please Enter an Integer");
				}
				//clear the textFields
				ActivityField.setText(null);
				DurationField.setText(null);
				DependenciesField.setText(null);
			}
		});
		enterButton.setBounds(40, 354, 175, 57);
		frame.getContentPane().add(enterButton);
	}
	/**Trys to convert string to numerical value. returns false is string is not a number , returns true otherwise*/
	private boolean isNumeric(String str) {
		try {
			int i  = Integer.parseInt(str);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
