// current class package
package GUI_Panels;

// importing DatabaeManage from DBMS package
import DBMS.DatabaseManage;

// importing librarires
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;


// This panel is used to select modules for from the course which the user has enrolled


public class EnrollModules implements ActionListener  {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private String module,username;
	private JFrame frame;
	private JLabel courseTitle;
	private JButton enroll, back;
	private JPanel enrollPanel;
	
	// creating object of DatabaseManage class
	DatabaseManage manage = new DatabaseManage();
	
	// class constructor
	public EnrollModules(String module, String username){
		
		this.module = module;
		this.username = username;
	}
	
	// function to insert modules to enrolled modules in user_info table
	private void addCourse(String name, String module) throws SQLException {
		
		
		String enrolled = manage.getEnrolledCourses("SELECT enrolled_modules FROM user_info WHERE username='"+ this.username +"'");
		String module_code = manage.getModuleCode("SELECT module_code FROM modules WHERE course_modules='"+ this.module +"'");
		enrolled+= "," + module_code;
		manage.updateEnrolledModule("UPDATE user_info SET `enrolled_modules`='"+ enrolled +"' WHERE username='" + this.username + "'");
		String[] data = manage.getUserModules("SELECT * FROM user_info WHERE username = '" + this.username + "'");
		manage.insertNewReport("INSERT INTO `report`(`student_name`, `module_name`, `marks`,`level`,`semester`) VALUES ('" + data[0] + "','" + module + "','0','"+data[1]+"','"+data[2]+"')");
		
		

	}
		
	
	// function to start the panel of EnrollModule class file
	public void beginGUI() {
		
		//main frame
		frame  = new JFrame(" Enroll Modules");
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		frame.setIconImage(frameIcon.getImage());
		frame.setSize(600,150);
		Border line = BorderFactory.createLineBorder(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		// panel where all the elements are added
		enrollPanel = new JPanel();
		enrollPanel.setBounds(25,15,540,80);
		enrollPanel.setBackground(Color.WHITE);
		enrollPanel.setBorder(line);
		enrollPanel.setLayout(null);
		
		// label for the module title 
		courseTitle = new JLabel();
		courseTitle.setBounds(20,23,330,30);
		courseTitle.setText(this.module);
		courseTitle.setFont(new Font("Consolas",Font.PLAIN,15));
		enrollPanel.add(courseTitle);
		
		// button to enroll into modules 
		enroll = new JButton();
		enroll.setBounds(315,21,110,35);
		enroll.setText("Enroll");
		enroll.addActionListener(this);
		enroll.setFocusable(false);
		enroll.setFont(new Font("Consolas",Font.PLAIN,14));
		enrollPanel.add(enroll);
		
		// button for going back from the enroll panel
		back = new JButton();
		back.setBounds(430,21,100,35);
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		back.setIcon(exitIcon);
		back.addActionListener(this);
		back.setFocusable(false);
		back.setFont(new Font("Consolas",Font.PLAIN,14));
		enrollPanel.add(back);

		// making the frame not resizable
		frame.setResizable(false);
		
		// adding enrollPanel to the frame
		frame.add(enrollPanel);
		
		// setting the frame layout to null
		frame.setLayout(null);
		
		// making the frame visible
		frame.setVisible(true);
		UIManager.put("Button.disabledText", Color.BLACK);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String[] userInfo = null;
		
		
		// if the enroll button is pressed
		if(e.getSource() == enroll) {

			DatabaseManage manage = new DatabaseManage();
			
			// exception handling
			try {
				
				userInfo = manage.getEnrolledCourse("SELECT enrolled_modules, level FROM user_info WHERE username = '"+ this.username +"'");
			} 
			
			catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			finally {
	

				String[] course_code = {};

				// checking if the enrolled_course cell is empty or not
				
				// if the cell is empty in the database
				if(userInfo[0].isBlank() || userInfo[0].isEmpty()) {
					
					course_code = userInfo[0].split(",");
					
				}
				
				// if the cell is not empty in the database
				else {
					
					course_code = userInfo[0].substring(1).split(",");
					
				}
				
				// if the user is below or level 5
				if(Integer.parseInt(userInfo[1]) <= 5) {
					
					if(course_code.length < 4 ) {

						try {
							
							// calling the method to add modules in the database
							addCourse(this.username, this.module);
						} 
						
						catch (SQLException e1) {
							
							e1.printStackTrace();
							
						}
						
						// setting the text of the Enroll button to Enrolled
						enroll.setText("Enrolled");
						
						// making the button disabled
						enroll.setEnabled(false);
							
					}
					
					else {
						
						// displaying error message to the user
						JLabel error = new JLabel("You have exceeded your course limit!");
						error.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(null,error,"", JOptionPane.ERROR_MESSAGE);
						
						
					}
				
				}
				
				// if the user is above level 5
				else {
					
					// if the userhas selected less than 4 modules
					if(course_code.length < 4 ) {

						// exception handling
						try {
							
							addCourse(this.username, this.module);
						} 
						
						catch (SQLException e1) {
							
							e1.printStackTrace();
							
						}
						
						// setting the text of the Enroll button to Enrolled
						enroll.setText("Enrolled");
						
						// making the button disabled
						enroll.setEnabled(false);
							
					}
					
					else {
						
						// displaying error message to the user
						JLabel error = new JLabel("You have exceeded your course limit!");
						error.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(null,error,"", JOptionPane.ERROR_MESSAGE);	
						
					}

				}
			
				
			}
			
		}
		
		
		// if the back button is pressed
		if(e.getSource() == back) {
			
			// closing the frame
			frame.dispose();
			
			// creating object of StudentPanel class
			StudentPanel std = new StudentPanel(this.username);
			try {
				// calling the function to start the GUI
				std.startGUI();
			}
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	 }
		
}





