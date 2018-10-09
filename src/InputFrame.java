import java.awt.Color;
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
		proccessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//one the user enters in all of the data we will open the output frame and display results here
				OutputFrame o = new OutputFrame();//make a new object of type outputFrame 
				o.newScreen();// open a new frame by calling it's initialize button
			}
		});
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
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the user hit the help button open a dialog box that helps with some of the common functions of the program
				JOptionPane.showMessageDialog(null, "Using of the program\n\n Q : How do I add an activity into the network?\n A : To enter "
						+ "a Node please enter the the nodes name in the activity name field\n enter the duration into the duration field."
						+ "plese note duration must be an integer, and add any dependencies of the\n node as a space separated list\n\nQ : How do I"
						+ " quit the program?\nA :  To quit the program simply hit the Quit button\n\nQ : How can I restart the Network?\n A :  "
						+ "To restart the network just hit the reset network."
						+ " it will clear the network so that you can start from scratch\n\n"
						+ "Q : Can I enter more than 1 activity at a time?\nA : No you can only enter 1 activity at a time" 
			);
			}
		});
		helpButton.setBounds(457, 400, 97, 25);
		frame.getContentPane().add(helpButton);
		
		JButton aboutButton = new JButton("About");
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Project for 360 written by Erick , Jai, and Michael.\n it's a network diagram GUI that gives the critical path");
			}
		});
		aboutButton.setBounds(562, 400, 97, 25);
		frame.getContentPane().add(aboutButton);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			/*once the user has entered the data in the provided fields. retrieve it and clear the fields*/
			public void actionPerformed(ActionEvent e) {
				activityLabel.setForeground(Color.BLACK);//reset the text if it was changed
				durationLabel.setForeground(Color.BLACK);//reset the label color if it was changed
				String activity = ActivityField.getText();
				String duration = DurationField.getText();
				String depend = DependenciesField.getText();
				String[] result = depend.trim().split(" ");//trims the string down and will store it in an array
				// not sure how to do dependencies
				if(activity.isEmpty()== true || duration.isEmpty()) {
					JOptionPane.showMessageDialog(null, "please enter missing info");
					if(activity.isEmpty() == true)
						activityLabel.setForeground(Color.red);
					if(duration.isEmpty() == true)
						durationLabel.setForeground(Color.red);
				}
				else if(isNumeric(duration) == false) {
					JOptionPane.showMessageDialog(null, "Please Enter an Integer");
					durationLabel.setForeground(Color.red);
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
