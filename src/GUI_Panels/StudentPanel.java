// current class file package
package GUI_Panels;

import DBMS.DatabaseManage;

// importing libraries
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*  This is one of the Impportant panel. This is also the Student View. You can view all the module from the course which you have enrolled.
 *  You can also view the teacher for the module. One of the most important feature is tha you can edit and delete yoru profile and Finally 
 *  you can view your result. The marks are being assigned by the teachers. You will get promoted if your marks in each module is greater that 40
 */


public class StudentPanel implements ActionListener{
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private JFrame frame, profile;
	private JPanel backPanel, topPanel, downPanel, enrolled;
	private JLabel studentName, email, student_id, phoneLine; 
	private JButton details, logout, available, neww, report, courseButton, rear1, back1 ,rear2, back2, name ,exit, profileBack, editProfile, saveChange, deleteAccount, unEnroll;
	private String fullname, oldname;
	private JTextField nameStudent, studentEmail, idStudent, phone;
	private String username;
	
	// creating arraylist object 
	List<JButton> buttonList = new ArrayList<JButton>();
	List<JButton> buttonList1 = new ArrayList<JButton>();
	
	// creating DatabaseManage object
	DatabaseManage manage = new DatabaseManage();
	
	// class constructor
	public StudentPanel(String username){
		
		this.username = username;
		
		// exception handling
		try {
			
			DatabaseManage manage = new DatabaseManage();
			
			// checking for the session
			// if there is not data in the session table session is added to the database table
			if(!manage.sessionExistence("SELECT * FROM session WHERE username = '"+ this.username + "' AND indicator = 'Student'")) {

				String[] result = manage.getSessionInfo("SELECT username, indicator FROM logs WHERE username = '"+ this.username + "'");
				manage.setSession("INSERT INTO session(`username`, `indicator`) VALUES ('"+ result[1] +"','"+ result[2]+"')");
			}
			
		}
		
		catch(SQLException error) {
			
			error.printStackTrace();
			
		}
		
		
	}
	
	// funtion to display userinfo and allow user to edit their info
	private void profileDetails() {
		
		// frame for view profile
		profile = new JFrame("Student Details");
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		profile.setIconImage(frameIcon.getImage());
		profile.setSize(500,260);
	
		// label for name
		studentName = new JLabel("Name    :");
		studentName.setBounds(20, 20, 150, 30);
		studentName.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(studentName);
		
		// textfield for name
		nameStudent = new JTextField();
		nameStudent.setBounds(100, 20, 200, 30);
		nameStudent.setFont(new Font("Consolas",Font.PLAIN,14));
		nameStudent.setEditable(false);
		profile.add(nameStudent);
		
		// label for email
		email = new JLabel("Email   :");
		email.setBounds(20, 70, 150, 30);
		email.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(email);
		
		// textfield for email
		studentEmail = new JTextField();
		studentEmail.setBounds(100, 70, 200, 30);
		studentEmail.setFont(new Font("Consolas",Font.PLAIN,14));
		studentEmail.setEditable(false);
		profile.add(studentEmail);
		
		// label for college id
		student_id = new JLabel("Identity:");
		student_id.setBounds(20, 120, 150, 30);
		student_id.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(student_id);
		
		// textfield for college id
		idStudent = new JTextField();
		idStudent.setBounds(100, 120, 200, 30);
		idStudent.setFont(new Font("Consolas",Font.PLAIN,14));
		idStudent.setEditable(false);
		profile.add(idStudent);
		
		// label for phone number
		phoneLine = new JLabel("Phone   :");
		phoneLine.setBounds(20, 170, 150, 30);
		phoneLine.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(phoneLine);
		
		// text field for phone number
		phone = new JTextField();
		phone.setBounds(100, 170, 200, 30);
		phone.setFont(new Font("Consolas",Font.PLAIN,14));
		phone.setEditable(false);
		profile.add(phone);

		// button used for editing profile
		editProfile = new JButton("Edit Profile");
		editProfile.setBounds(330, 20, 130, 30);
		editProfile.setFocusable(false);
		editProfile.addActionListener(this);
		editProfile.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(editProfile);
		
		// save change button to save the changes made
		saveChange = new JButton("Save Change");
		saveChange.setBounds(330, 70, 130, 30);
		saveChange.setFocusable(false);
		saveChange.setEnabled(false);
		saveChange.addActionListener(this);
		saveChange.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(saveChange);
		
		// delete button to delete the profile
		deleteAccount = new JButton("Delete A/C");
		deleteAccount.setBounds(330, 120, 130, 30);
		deleteAccount.setFocusable(false);
		deleteAccount.setEnabled(false);
		deleteAccount.addActionListener(this);
		deleteAccount.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(deleteAccount);
		
		// back button to go back to student view
		profileBack = new JButton("Back");
		profileBack.setBounds(330, 170, 130, 30);
		profileBack.setFocusable(false);
		profileBack.addActionListener(this);
		profileBack.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(profileBack);
		
		// getting the full name of the user from the database
		String[] info = null;
		
		// exception handling
		try {
			
			info = manage.getProfileData("SELECT * FROM user_info WHERE username='" + this.username +"'");
			
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			// displaying the data of user 
			nameStudent.setText(info[0]);
			studentEmail.setText(info[1]);
			idStudent.setText(info[2]);
			phone.setText(info[3]);
			
		}
		
		/* setting the layout of the frame to null, making the frame appear in the middle, setting the resizability to false so that it cannot be resized and make 
		 * the frame visible to the user 
		 */
		profile.setLocationRelativeTo(null);
		profile.setResizable(false);
		profile.setLayout(null);
		profile.setVisible(true);
		
		
	}
	
	
	// function to start the student panel
	public void startGUI() throws SQLException {
		
		buttonList1.clear();
		buttonList.clear();
		
		// main frame for this class
		frame = new JFrame("Student View");
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");;
		frame.setIconImage(frameIcon.getImage());
		frame.setSize(615,630);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		backPanel = new JPanel();
		backPanel.setBounds(0,0,700,500);
		backPanel.setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setBounds(20,70,565,300);
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(null);

		downPanel = new JPanel();
		downPanel.setBounds(20,390,565,150);
		downPanel.setBackground(Color.WHITE);
		downPanel.setLayout(null);
		
		rear1 = new JButton(" ");
		rear1.setFocusable(false);
		rear1.setEnabled(false);
		rear1.setBounds(195,-10,500,30);
		downPanel.add(rear1);
		
		rear2 = new JButton(" ");
		rear2.setFocusable(false);
		rear2.setEnabled(false);
		rear2.setBounds(195,-10,500,30);
		topPanel.add(rear2);
		
		back1 = new JButton("");
		back1.setFocusable(false);
		back1.setEnabled(false);
		back1.setBounds(-1,-10,22,30);
		downPanel.add(back1);
		
		back2 = new JButton("");
		back2.setFocusable(false);
		back2.setEnabled(false);
		back2.setBounds(-1,-10,22,30);
		topPanel.add(back2);
		
		// logout button for the panel
		logout = new JButton();
		logout.setBounds(495, 18, 90, 30);
		logout.setFocusable(false);
		logout.addActionListener(this);
		logout.setFont(new Font("Consolas",Font.PLAIN,14));
		logout.setText("Logout");
		backPanel.add(logout);

		// exit button of the class
		exit = new JButton();
		exit.setBounds(20, 550, 100, 30);
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		exit.setIcon(exitIcon);
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setFont(new Font("Consolas",Font.PLAIN,14));
		backPanel.add(exit);
		
		// unenroll button of the class
		unEnroll = new JButton();
		Icon unEnrollIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\unenroll.png");
		unEnroll.setBounds(125, 550, 50, 30);
		unEnroll.setIcon(unEnrollIcon);
		unEnroll.setFocusable(false);
		unEnroll.addActionListener(this);
		unEnroll.setFont(new Font("Consolas",Font.PLAIN,14));
		backPanel.add(unEnroll);
		
		// button to generate report of the user
		report = new JButton();
		report.setBounds(385, 550, 200, 30);
		report.setFocusable(false);
		Icon reportIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\report.png");
		report.setIcon(reportIcon);
		report.addActionListener(this);
		report.setFont(new Font("Consolas",Font.PLAIN,14));
		report.setText("Generate Report");
		backPanel.add(report);

		// button to show the details of the user
		details = new JButton();
		details.setBounds(362, 18, 130, 30);
		details.setFocusable(false);
		details.addActionListener(this);
		details.setFont(new Font("Consolas",Font.PLAIN,14));
		details.setText("View Profile");
		backPanel.add(details);
		
		// title Modules Availabe
		neww = new JButton("Modules Available");
		neww.setBounds(20,0,176,35);
		neww.setFont(new Font("Consolas",Font.PLAIN,14));
		neww.setEnabled(false);
		UIManager.put("Button.disabledText", Color.BLACK);
		topPanel.add(neww);
		
		// title enrolled Modules
		available = new JButton("Enrolled Modules");
		available.setBounds(20,0,176,35);
		available.setFont(new Font("Consolas",Font.PLAIN,14));
		available.setEnabled(false);
		UIManager.put("Button.disabledText", Color.BLACK);
		downPanel.add(available);
	
		// panel to show all the modules
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(15,40,550,280);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		JScrollPane topPane = new JScrollPane(panel);
        topPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        topPane.setBounds(15, 50, 540, 238);
        
        String[] userinfo;
        String query = null;
       
        // query to get all information of the user_info
		query = "SELECT level, semester, fullname, enrolled_courses FROM user_info WHERE username = '" + this.username + "'";
		userinfo = manage.getUserData(query);

		// displaying the name of the user
		name = new JButton("Logged: " + userinfo[1]);
		fullname = userinfo[1];
		name.setEnabled(false);
		name.setFont(new Font("Consolas",Font.PLAIN,14));
		UIManager.put("Button.disabledText", Color.BLACK);
		name.setBounds(20,18,300,30);
		backPanel.add(name);
		
		// query to get all the info from modules 
		query = "SELECT * FROM modules WHERE level = '" + userinfo[2] + "' AND course_names = '"+ userinfo[3] +"' AND semester = '"+ userinfo[4] +"' ";
		ArrayList<String[]> courses = manage.getCourseData(query);
		
		
		// getting enrolled modules from the user_info
		query = "SELECT enrolled_modules FROM user_info WHERE username = '"+ this.username +"'";
		
		String enroll = manage.getEnrolledCourses(query);
		String[] enrolledSplit = null;

		// if the enrolled user info is empty
		if(!enroll.isEmpty() || !enroll.isBlank()) {
			
			enrolledSplit = enroll.substring(1).split(",");
			
		}
		
		// if the enrolled user info is not empty
		else {
			enrolledSplit = enroll.split(",");
		}
		
		int x = 0;

		// displaying all the modules using JButton
		
		
		for(String[] course: courses) {
			
			courseButton = new JButton( "(Level: "+ course[2] +")         "+ course[1] + "        "+ course[0]);
			courseButton.setHorizontalAlignment(SwingConstants.LEFT);
			courseButton.setFont(new Font("Consolas",Font.PLAIN,13));
			courseButton.setFocusable(false);
			
			// adding all the JButtons in ArrayList as a reference
			
		    buttonList1.add(courseButton);
		    x+=1;
			
		    // if the module is already enrolled then the button will be disabled
			for(String enrolledCourse: enrolledSplit) {
				
				if(course[1].equals(enrolledCourse)) {
					
					
					courseButton.setEnabled(false);

				}

		    }

		}
		
		
		// getting all the button from the array list and displaying it in the panel
		
		
		 for(JButton butt : buttonList1) {
	 	    	
	 	    butt.addActionListener(this);
	 	    	
	 	    // if there are less than equals to 3 buttons then different size so that it fits perfectly
	 	    if(x <= 3) {
	 	    		
	 	    		butt.setMaximumSize(new Dimension(512, 70));
	 		        butt.setPreferredSize(new Dimension(395, 65));

	 	    }
	 	    	
	 	   // if there are more 3 buttons then different size so that it fits perfectly while scrolling
	 	    else {
	 	    		
	 	    		butt.setMaximumSize(new Dimension(500, 70));
	 		        butt.setPreferredSize(new Dimension(395, 65));
	 	    		
	 	    }
	 	    	
	 	    panel.add(butt);
	 	    panel.add(Box.createRigidArea(new Dimension(25, 10)));
	 	    	
	 	  }
		 
		// panel to show enrolled Modules
		enrolled = new JPanel();
		enrolled.setBounds(12,255,550,80);
		enrolled.setBackground(Color.WHITE);
		enrolled.setLayout(new BoxLayout(enrolled, BoxLayout.Y_AXIS));
		enrolled.add(Box.createRigidArea(new Dimension(0, 10)));
		JScrollPane pane = new JScrollPane(enrolled);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    pane.setBounds(15, 50, 540, 82);
		 
		x  = 0; 
		
		// getting all the modules from tabel modules
	    ArrayList<String[]> courseDataSet = manage.getCourseData("SELECT * FROM modules ORDER BY level ASC");
		 
	    // creating button in a loop
		for(String[] courseData: courseDataSet) {
			
			courseButton = new JButton( "(Enrolled)         "+ courseData[1] + "        "+ courseData[0]);
			courseButton.setHorizontalAlignment(SwingConstants.LEFT);
			courseButton.setFont(new Font("Consolas",Font.PLAIN,13));
			courseButton.setFocusable(false);
			
			if(enrolledSplit.length <= 1) {
				
				courseButton.setMaximumSize(new Dimension(512, 60));
				courseButton.setPreferredSize(new Dimension(395, 55));
				
			}
			
			else {
				
				courseButton.setMaximumSize(new Dimension(500, 65));
				courseButton.setPreferredSize(new Dimension(395, 60));
				
			}
			
			// checking the modules if its enrolled or not
			for(String enrolledCourse: enrolledSplit) {
				
				// if the module is enrolled then it is added to the arraylist
				if(courseData[1].equals(enrolledCourse)) {
					
	 		        buttonList.add(courseButton);
				}

		    }
			
			x+=1;

		}
		 
		// printing button from the arraylist
		for(JButton but : buttonList) {
	 	    	
	 	    	but.addActionListener(this);
	 	    	enrolled.add(but);
	 	    	enrolled.add(Box.createRigidArea(new Dimension(25, 10)));
	 	    	
		}
		
		// adding every component to its place and then finally adding it to the frame
		topPanel.add(topPane);
		backPanel.add(topPanel);
		downPanel.add(pane);
		backPanel.add(downPanel);
		frame.add(backPanel);
 	    frame.setVisible(true);	
		
}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if the clicked component is a JButton
		if(e.getSource() instanceof JButton) {

			// getting text of the button
			String course = (((JButton) e.getSource()).getText());
			
			String[] split = course.split("        ");
			
			List<String> list = Arrays.asList(split);
			
			// if the splitted sentence of the button has 3 texts then the buttons are used to show the modules
			if(split.length == 3) {
				
				// if the buttons contains enrolled text
				if(list.contains("(Enrolled)")) {
					
					frame.dispose();
					new UnEnrollModules(split[2],this.username,fullname).beginGUI();
				}
				
				// if the button does not contain enroll text
				else {
					
					frame.dispose();
					new EnrollModules(split[2],this.username).beginGUI();
					
					
				}
				
			}
			
		}
		
		// if the logout button is pressed
		if(e.getSource() == logout) {

			// closing the frame
			frame.dispose();
			
			try {
				
				// creating a DatabaseManage object
				DatabaseManage manage = new DatabaseManage();
				
				// deleting data from session after logout
				manage.deleteSession("DELETE FROM session WHERE user_id != 0 AND indicator = 'Student'");
				
				// creating a MainPanel object and calling the method to start the GUI
				MainPanel main = new MainPanel();
				main.startGUI();
				
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		// if the view details button is pressed
		if(e.getSource() == details) {
			
			// closing the frame and opening the profile frame
			frame.dispose();
			profileDetails();
			
		}
		
		// if the generate report button is pressed
		if(e.getSource() == report) {
			
			// closing the frame and opening the report frame
			frame.dispose();
			
			// opening the Report panel
			new GenerateReport(fullname, this.username);
			
		}
		
		
		// if editProfile button is pressed
		if(e.getSource() == editProfile) {
			
			oldname = nameStudent.getText();
			nameStudent.setEditable(true);
			studentEmail.setEditable(true);
			idStudent.setEditable(true);
			phone.setEditable(true);
			saveChange.setEnabled(true);
			deleteAccount.setEnabled(true);
			profileBack.setEnabled(false);
		
		}
		
		// if the saveChange button is pressed the the edited user info will be updated in the Database
		if(e.getSource() == saveChange) {

			try {
				manage.updateUserInfo("UPDATE user_info SET fullname='"+ nameStudent.getText() +"', email='"+ studentEmail.getText() +"', phone ='"+  phone.getText() + "', college_id ='"+ idStudent.getText() +"' WHERE username ='"+this.username+"'");
				manage.updateReport("UPDATE report SET student_name='"+ nameStudent.getText() +"' WHERE student_name = '"+ oldname +"'");
			} 
			
			catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			finally {
				
				// after updating the profile panel will be closed
				profile.dispose();
				
				// displaying Information message to the user
				JLabel er = new JLabel("Succesfully Updated!");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"Task Done", JOptionPane.INFORMATION_MESSAGE);
				
				// exception handling
				try {
					// calling the studentpanel constructor and calling the method to start the GUI
					new StudentPanel(this.username).startGUI();
				}
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}

			
		}
		
		// if the delete button is pressed
		if(e.getSource() == deleteAccount) {
			
			// deleting every information related to the user
			String query1 = "DELETE FROM user_info WHERE username='"+this.username+"'";
			String query2 = "DELETE FROM logs WHERE username='"+this.username+"'";
			String query3 = "DELETE FROM report WHERE student_name = '"+nameStudent.getText()+"'";
			String query4 = "DELETE FROM session WHERE username !=''";
			
			try {
				
				manage.deleteAccount(query1, query2, query3, query4);
			} 
			
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				// closing the profile panel
				profile.dispose();
				
				// displaying information message to the user
				JLabel er = new JLabel("Account has been deleted!");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"Task Done", JOptionPane.INFORMATION_MESSAGE);
				
				// classing the MainPanel constructor and calling method to start the frame
				new MainPanel().startGUI();
				
			}

			
		}

		// if back button of the profile frame is pressed it will redirect you to the student view
		if(e.getSource() == profileBack) {
			
			// closing the profile panel
			profile.dispose();
			
			// exception handling
			try {
				// calling the constructor and calling the method to start the GUI
				new StudentPanel(this.username).startGUI();
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		
		// if unEnroll button is pressed the user will be unenrolled from the course and he/she have to pick another course
		if(e.getSource() == unEnroll) {
			
			// exception handling
			try {
				
				manage.unEnrollcourse("UPDATE user_info SET enrolled_modules ='"+"', enrolled_courses ='"+"', semester ='1', completed_sem ='0' WHERE username = '"+ this.username +"'");
				manage.removeResult("DELETE FROM report WHERE student_name = '"+fullname+"'");
				
			} 
			
			catch (Exception error) {
				
				error.printStackTrace();
			}
			
			finally {
				
				// closing the main frame
				frame.dispose();
				// calling the CourseEnroll() constructor
				new CourseEnroll(this.username);
				
			}
			
		}

		// if the exit button is pressed the frame will be closed
		if(e.getSource() == exit) {
			
			// disposing/ closing the main frame
			frame.dispose();
			
		}
		
	}
	
}
