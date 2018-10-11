import java.awt.Color;
import java.awt.EventQueue;
import java.util.*;

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
	PertList activityList = new PertList();

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
		durationLabel.setBounds(43, 245, 56, 16);
		frame.getContentPane().add(durationLabel);
		
		DurationField = new JTextField();
		DurationField.setBounds(40, 274, 195, 46);
		frame.getContentPane().add(DurationField);
		DurationField.setColumns(10);
		
		JLabel dependenciesLabel = new JLabel("Dependecies");
		dependenciesLabel.setBounds(43, 145, 116, 16);
		frame.getContentPane().add(dependenciesLabel);
		
		DependenciesField = new JTextField();
		DependenciesField.setBounds(40, 174, 195, 46);
		frame.getContentPane().add(DependenciesField);
		DependenciesField.setColumns(10);
		
		JButton proccessButton = new JButton("Proccess");
		proccessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//one the user enters in all of the data we will open the output frame and display results here
				String Dependencies = "";
				//testing with console output
				ArrayList<PertNode> CriticalPath = new ArrayList<PertNode>();
				for (int i = 0; i < activityList.masterList.size(); i++)
				{
					boolean flag = activityList.masterList.get(i).setDependency(activityList);
					if(flag == false)
					{
						JOptionPane.showMessageDialog(null, "Error: one of the dependencies you entered did not have a corresponding node. Please input the corresponding Node");
						return;
					}
				}
				for (int i = 0; i < activityList.masterList.size(); i++)
				{
					activityList.masterList.get(i).setEndTime();
				}
				activityList.insertionSort(activityList.masterList);
				activityList.masterList.get(activityList.masterList.size() - 1).setCriticalPath(CriticalPath);
				CriticalPath.add(activityList.masterList.get(activityList.masterList.size() - 1));
				int endTime = activityList.masterList.get(activityList.masterList.size() - 1).endTime;
				activityList.insertionSort(CriticalPath);
				String criticalPathOutput = activityList.masterList.get(0).printCriticalPath(CriticalPath);
					OutputFrame out = new OutputFrame(criticalPathOutput, endTime);
					out.newScreen(criticalPathOutput, endTime);
			}
		});
		
		proccessButton.setBounds(368, 47, 186, 65);
		frame.getContentPane().add(proccessButton);
		
		JButton restartButton = new JButton("Restart");
		restartButton.setBounds(368, 155, 186, 65);
		frame.getContentPane().add(restartButton);
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activityList.masterList.clear();
			}
		});
		
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
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Using the program\n\nQ: how do I enter input\n A:you enter the name in the activity name"
						+ "you enter the duration as an integer in the\n duration field, and you enter the dependecies as a spaced list\n\nQ:How do I restart?"
						+ "\nA: to restart simply press the enter button and the network will be cleared out\n\n Q: what does the proccess button do?\nA:The proccess button opens a new tab where the paths are listed"
						+ "in descending order");
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
				int intDuration = 0;
				durationLabel.setForeground(Color.BLACK);
				activityLabel.setForeground(Color.BLACK);
				String[] result;
				String depend;
				String activity = ActivityField.getText();
				String duration = DurationField.getText();
				if(duration.isEmpty() ==  true || activity.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "please enter required info");
					if(duration.isEmpty() == true){
						durationLabel.setForeground(Color.RED);
					}
				}
					if(activity.isEmpty() == true) {
						activityLabel.setForeground(Color.RED);
					}
				if(isNumeric(duration) == false) {
					JOptionPane.showMessageDialog(null, "Please Enter an Integer");
					activityLabel.setForeground(Color.RED);
				}
				else 
				{
					intDuration = Integer.parseInt(duration);
				}
				//we need to resolve the edge case where dependency field is empty
				//to avoid a null pointer access exception
				if(DependenciesField.getText().isEmpty())
				{
					result = new String[0];
				}
				else 
				{
					depend = DependenciesField.getText();
					result = depend.trim().split(" ");//trims the string down and will store it in an array
				// not sure how to do dependencies
				}
				PertNode newNode = new PertNode(activity, intDuration, result);
				activityList.updateList(newNode);
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
