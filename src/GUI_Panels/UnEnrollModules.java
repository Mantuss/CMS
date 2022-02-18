// current class package
package GUI_Panels;

// importing libraries
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

// importing DatabaseManage File From DBMS Package
import DBMS.DatabaseManage;

/* Students are able to view their module Teacher with the help of this panel. They will also be able to unenroll their module */

public class UnEnrollModules implements ActionListener  {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private String module,username, fullname;
	private JFrame frame;
	private JLabel courseTitle;
	private JButton unenroll, back;
	private JPanel enrollPanel;
	private JTable teachers;
	public DatabaseManage manage = new DatabaseManage();
	
	// class constructor
	public UnEnrollModules(String module, String username, String fullname){
		
		this.module = module;
		this.username = username;
		this.fullname = fullname;
	}
	

	// funtion to remove modules from enrolled modules
	private void removeModules(String[] code, String course_code) throws SQLException {
		
		String result = "";
		
		// removing the unenrolled module for the string
		for(String c: code) {
			
			if(!c.equals(course_code)) {
				
				result+= "," + c;
			}
			
		}
		
		// updating the database
		manage.updateEnrolledModule("UPDATE user_info SET `enrolled_modules`='"+ result +"' WHERE username='" + this.username + "'");
		manage.updateResult("DELETE FROM report WHERE student_name='"+ this.fullname +"' AND module_name = '"+ this.module + "'");
		manage.resetAutoIncrementReport();

	}
		
	// function to begin the GUI
	public void beginGUI() {
		
		// main frame of this class
		frame  = new JFrame("Enrolled Module");
		frame.setSize(600,380);
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		frame.setIconImage(frameIcon.getImage());
		
		Border line = BorderFactory.createLineBorder(Color.GRAY);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		// panel where the module information is given
		enrollPanel = new JPanel();
		enrollPanel.setBounds(25,15,540,80);
		enrollPanel.setBackground(Color.WHITE);
		enrollPanel.setBorder(line);
		enrollPanel.setLayout(null);
		
		// Label for the course full name
		courseTitle = new JLabel();
		courseTitle.setBounds(20,23,330,30);
		courseTitle.setText(this.module);
		courseTitle.setFont(new Font("Consolas",Font.PLAIN,15));
		enrollPanel.add(courseTitle);
		
		// button to enroll from the module
		unenroll = new JButton();
		unenroll.setBounds(315,21,110,35);
		unenroll.setText("UnEnroll");
		unenroll.addActionListener(this);
		unenroll.setFocusable(false);
		unenroll.setFont(new Font("Consolas",Font.PLAIN,14));
		enrollPanel.add(unenroll);
		
		// back button which will redirect the user to the student panel
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		back = new JButton();
		back.setIcon(exitIcon);
		back.setBounds(430,21,100,35);
		back.addActionListener(this);
		back.setFocusable(false);
		back.setFont(new Font("Consolas",Font.PLAIN,14));
		enrollPanel.add(back);
		
		// declaring some local variables
		String[][] data = null;
		String code = null;
		
		// exception handling
		try {
			
			code = manage.getCodeModule("SELECT module_code From modules WHERE course_modules = '"+ this.module +"'");
			data = manage.getModuleTeachers(code);
			
		} 
		
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// title button (Disabled Button)
		JButton headButton = new JButton("Module Teachers");
		headButton.setBounds(175, 110, 210, 40);
		headButton.setFocusable(false);
		headButton.setEnabled(false);
		headButton.setFont(new Font("Consolas",Font.PLAIN, 14));
		frame.add(headButton);
		
		// table headings 
		String[] column = {"S.N", "Teacher's Name", "Email"};
		
		// creating table to show the module teachers
		teachers = new JTable(data, column);
		teachers.setRowHeight(30);
		JTableHeader header = teachers.getTableHeader();
		header.setFont(new Font("Consolas",Font.PLAIN, 15));
		teachers.setEnabled(false);
		teachers.setFont(new Font("Consolas",Font.PLAIN, 14));

		// managing the table
		teachers.getColumnModel().getColumn(0).setPreferredWidth(100);
		teachers.getColumnModel().getColumn(1).setPreferredWidth(350);
		teachers.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		teachers.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );teachers.setDefaultRenderer(String.class, centerRenderer);
		teachers.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );teachers.setDefaultRenderer(String.class, centerRenderer);
		teachers.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );teachers.setDefaultRenderer(String.class, centerRenderer);
		
		// adding the table to JScrollpane
		JScrollPane scrollPane = new JScrollPane(teachers);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(25, 170, 540, 150);
		
		// adding the table to the frame
		frame.add(scrollPane);
		
		// making the frame resizable
		frame.setResizable(false);
		
		// adding the enrollpanel to the frame
		frame.add(enrollPanel);
		
		// making the layout of the frame null
		frame.setLayout(null);
		
		// making the frame visible to the user
		frame.setVisible(true);
		
		// making the disabled button a bit cleaner
		
		UIManager.put("Button.disabledText", Color.BLACK);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String[] userInfo = null;

		// if the user pressed the unenroll button
		if(e.getSource() == unenroll) {

			// creating object of DatabaseMange class
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
				// if there is no enrolled modules
				if(userInfo[0].isBlank() || userInfo[0].isEmpty()) {
					
					course_code = userInfo[0].split(",");
					
				}
				
				
				// if there is other enrolled modules 
				else {
					
					course_code = userInfo[0].substring(1).split(",");
					
				}
				
				// declaring and initializing a variable
				String code = null;
				
				// exception handling
				try {
				
					code = manage.getModuleCode("SELECT module_code FROM modules WHERE course_modules='"+ this.module +"'");
					
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				finally{
					
					// exception handling
					try {
						
						// function to remove module from the enrolled modules
						removeModules(course_code, code);
						
					} 
					
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
				
			}
			
			
			// making the enrolled button disabled after clicking
			unenroll.setText("Enroll");
			unenroll.setEnabled(false);
		}
		
		// if the back button is pressed
		if(e.getSource() == back) {
			
			// closing the frame
			frame.dispose();
			
			// creating object of StudentPanel
			StudentPanel std = new StudentPanel(this.username);
			
			// exception handling
			try {
				
				// starting the GUI of the panel
				std.startGUI();
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	 }
}





