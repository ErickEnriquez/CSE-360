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
import javax.swing.JRadioButton;

public class InputFrame {

	private JFrame frame;
	private JTextField ActivityField;
	private JTextField DurationField;
	private JTextField DependenciesField;
	public boolean toggle = false;
	public ArrayList<PertNode> NodesList = new ArrayList<PertNode>();
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
				//ArrayList<PertNode> CriticalPath = new ArrayList<PertNode>();
				
				ArrayList<ArrayList<PertNode>> godList = new ArrayList<ArrayList<PertNode>>();
				ArrayList<PertNode> normalPath = new ArrayList<PertNode>();
				
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
				activityList.insertionSort(activityList.masterList, "i");
				ArrayList<ArrayList<PertNode>> CriticalPaths = new ArrayList<ArrayList<PertNode>>();
				if(CriticalPaths.size() == 0)
				{
					CriticalPaths.add(new ArrayList<PertNode>());
				}
				//activityList.masterList.get(activityList.masterList.size() - 1).setCriticalPath(CriticalPath);
				activityList.masterList.get(activityList.masterList.size() - 1).markCriticalPath(activityList.masterList.get(activityList.masterList.size() - 1));
				CriticalPaths.get(0).add(activityList.masterList.get(activityList.masterList.size() - 1)); // add the final element
				activityList.masterList.get(0).determineCriticalPaths(activityList.masterList.get(activityList.masterList.size() - 1), CriticalPaths, CriticalPaths.get(0));
				
				activityList.masterList.get(activityList.masterList.size() - 1).determinePaths(activityList.masterList.get(activityList.masterList.size() - 1), godList, normalPath);
				for(int i = 0; i < godList.size(); i++)
				{
					activityList.insertionSort(godList.get(i), "i");
				}
				String list = "";
				int[] totalTime = new int[godList.size()];
				for(int i = 0; i < godList.size(); i++)
				{
					totalTime[i] = 0;
				}
				for(int i = 0; i < godList.size(); i++)
				{
					for(int j = 0; j < godList.get(i).size(); j++)
					{
						totalTime[i] += godList.get(i).get(j).Duration;
					}
				}
				for(int i =  0; i < godList.size(); i++)
				{
					for(int j = 0; j < godList.get(i).size(); j++)
					{
						list += godList.get(i).get(j).Node + " ";
					}
					list += "Total Time: " + totalTime[i] + "\n";
				}
				int endTime = activityList.masterList.get(activityList.masterList.size() - 1).endTime;
				
				for(int i = 0; i < CriticalPaths.size(); i++)
				{
					activityList.insertionSort(CriticalPaths.get(i), "i");
				}
				//String criticalPathOutput = activityList.masterList.get(0).printCriticalPath(CriticalPath);
				String criticalPathOutput = "";
				for(int i = 0; i < CriticalPaths.size(); i++)
				{
					criticalPathOutput += activityList.masterList.get(0).printCriticalPath(CriticalPaths.get(i));
				}
				activityList.insertionSort(NodesList, "i");
				
				for (int i = 0; i < NodesList.size(); i++)
				{
					list += NodesList.get(i).Node + "\tTime: " + NodesList.get(i).endTime + "\n";
				}
				if(toggle)
				{
					OutputFrame out = new OutputFrame("", criticalPathOutput, endTime);
					out.newScreen("", criticalPathOutput, endTime);
				}
				else
				{
					OutputFrame out = new OutputFrame(list, "", endTime);
					out.newScreen(list, "", endTime);
				}
				for(int i = 0; i < CriticalPaths.size(); i++) 
				{
					for (int j = 0; j < CriticalPaths.get(i).size(); j++)
					{
						CriticalPaths.get(i).get(j).isCritical = false;
					}
				}
				//String criticalPathOutput = activityList.masterList.get(0).printCriticalPath(CriticalPath);
			}
		});
		
		proccessButton.setBounds(368, 47, 186, 46);
		frame.getContentPane().add(proccessButton);
		
		JButton restartButton = new JButton("Restart");
		restartButton.setBounds(368, 121, 186, 46);
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
		quitButton.setBounds(368, 192, 186, 46);
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
				if(intersects(newNode))
				{
					NodesList.add(newNode);
					activityList.updateList(newNode);
				}
				else
				{
					activityList.updateList(newNode);
				}
				//clear the textFields
				ActivityField.setText(null);
				DurationField.setText(null);
				DependenciesField.setText(null);
			}

			private boolean intersects(PertNode newNode) {
				for(int i = 0; i < NodesList.size(); i++)
				{
					if(Objects.equals(newNode.Node, NodesList.get(i).Node))
					{
						return false;
					}
				}
				return true;
			}
		});
		enterButton.setBounds(40, 354, 175, 57);
		frame.getContentPane().add(enterButton);
		
		JRadioButton critPathOnlyButton = new JRadioButton("Display Critical Path Only");
		critPathOnlyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(toggle)
				{
					toggle = false;
				}
				else
					toggle = true;
			}
		});
		critPathOnlyButton.setBounds(368, 329, 213, 25);
		frame.getContentPane().add(critPathOnlyButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Update");
		rdbtnNewRadioButton.setBounds(368, 285, 186, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
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
