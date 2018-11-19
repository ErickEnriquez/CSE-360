import java.awt.EventQueue;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateNewFile {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void generateFileScreen (String list, String criticalPath, int endTime) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewFile window = new CreateNewFile(list,criticalPath,endTime);
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
	public CreateNewFile(String list, String criticalPath, int endTime) {
		initialize(list,criticalPath,endTime);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String list, String criticalPath, int endTime) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("GenerateFile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//if the user hit the generate file button
				String fileName = textField.getText();//get the file name user entered
				textField.setText(null);//set the text to be null
				try {
				File outputFile  = new File(fileName);
				if(outputFile.exists() == false) {
					outputFile.createNewFile();//create a new file
					}
				PrintWriter write = new PrintWriter(outputFile);//create a obj to write to file
				write.println(fileName);//placeholder text for now
				
				
				DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//creata a formatter object
				LocalDateTime now = LocalDateTime.now();//get the local time using .now method from localdatetime class
				write.println(dtf.format(now));//write the current time to the file
				write.println("Total Time: " + endTime + "\n" + criticalPath + "\n" + list);
				write.close();//close the writer
				System.out.println("Successfully generated report");
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(149, 150, 133, 47);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(80, 88, 250, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterFileName = new JLabel("Enter File Name");
		lblEnterFileName.setBounds(149, 35, 133, 40);
		frame.getContentPane().add(lblEnterFileName);
	}
}
