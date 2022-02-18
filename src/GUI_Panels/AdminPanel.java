// current class package
package GUI_Panels;

// importing libraries
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


// importing DatabaseManage from DBMS Package
import DBMS.DatabaseManage;


public class AdminPanel extends InstructorPanel implements ActionListener  {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private String courseCode = null;
	private String[] data = null;
	private JFrame editPanel, deletePanel, addPanel, manageTeacher;
	private String username;
	private JButton manageCourse, manageAccounts, editCourse, deleteCourse, addCourse, courseSearch, applyChange, back, deleteButton, deleteBack, refresh, courseAdd, addBack, findCourse, assign, generateReport, exit;
	private JPanel panelBack, searchPanel, accountPanel, bottomPanel;
	private JLabel courseFull, courseShort, courseFullName, courseShortForm, addedDate, name, nameLabel,statusLabel;
	private JButton accountTitle, searchAccount, changeLogs, generateButton , assignModule, deassignModule, assignBack;
	private JTextField crsFull, crsShort, crsSearch, deleteSearch, crsShortForm ,addDate, crsFullName, searchCourse, teacher, userName, status, nameField , resultStudent;
	private JTable accounts;
	private JScrollPane scrollPane;
	private JComboBox<String> dropdown;
	private Border line = BorderFactory.createLineBorder(Color.GRAY);
	private Border wline = BorderFactory.createLineBorder(Color.WHITE);
	private DatabaseManage manage = new DatabaseManage();
	private ArrayList<JButton> buttonList1 = new ArrayList<>();

	// class constructor
	public AdminPanel(String username) {
		
		super(username);
		this.username = username;
	
	}
	
	// method to manage teacher module
	private void manageTeacherModules() {
		
		studentView.dispose();
		manageTeacher = new JFrame("Assign Modules");
	
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		manageTeacher.setIconImage(frameIcon.getImage());
		
		manageTeacher.setSize(450,150);
		manageTeacher.setResizable(false);
		manageTeacher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		manageTeacher.setLocationRelativeTo(null);
		
		teacher = new JTextField();
		teacher.setBounds(20, 20, 140, 30);
		teacher.setFont(new Font("Consolas",Font.PLAIN,13));
		manageTeacher.add(teacher);
		
		String[] modules = null;
		
		try {
			
			modules = manage.getModules("SELECT course_modules FROM modules WHERE course_modules != ''");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		dropdown = new JComboBox<String>(modules);
		dropdown.setBounds(165, 20, 250, 30);
		dropdown.setFont(new Font("Consolas",Font.PLAIN,13));
		manageTeacher.add(dropdown);
		
		assignModule = new JButton("Assign Module");
		assignModule.setBounds(20, 60, 140, 30);
		assignModule.addActionListener(this);
		assignModule.setFocusable(false);
		assignModule.setFont(new Font("Consolas",Font.PLAIN,13));
		manageTeacher.add(assignModule);
		
		deassignModule = new JButton("Deassign Module");
		deassignModule.setBounds(165, 60, 140, 30);
		deassignModule.addActionListener(this);
		deassignModule.setFocusable(false);
		deassignModule.setFont(new Font("Consolas",Font.PLAIN,13));
		manageTeacher.add(deassignModule);
		
		assignBack = new JButton();
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		assignBack.setIcon(exitIcon);
		assignBack.setBounds(310, 60, 105, 30);
		assignBack.addActionListener(this);
		assignBack.setFocusable(false);
		assignBack.setFont(new Font("Consolas",Font.PLAIN,13));
		manageTeacher.add(assignBack);
		
		manageTeacher.setLayout(null);
		manageTeacher.setVisible(true);
		
		
		
	}
	
	// method to edit the course
	private void editPanel() {
		
		editPanel = new JFrame();
		editPanel.setSize(300,265);
		editPanel.setResizable(false);
		
		crsSearch = new JTextField();
		crsSearch.setBounds(10, 10, 180, 30);
		crsSearch.setFont(new Font("Consolas",Font.PLAIN,13));
		editPanel.add(crsSearch);
		
		courseSearch = new JButton("Search");
		courseSearch.setBounds(195, 10, 80, 30);
		courseSearch.addActionListener(this);
		courseSearch.setFont(new Font("Consolas",Font.PLAIN,13));
		editPanel.add(courseSearch);
	
		courseShort = new JLabel("Course Code:");
		courseShort.setBounds(10, 50, 100, 30);
		courseShort.setFont(new Font("Consolas",Font.PLAIN,13));
		editPanel.add(courseShort);
		
		crsShort = new JTextField();
		crsShort.setBounds(10, 75, 265, 30);
		crsShort.setEditable(false);
		crsShort.setFont(new Font("Consolas",Font.PLAIN,13));
		editPanel.add(crsShort);
		
		courseFull = new JLabel("Course Name:");
		courseFull.setBounds(10, 110, 100, 30);
		courseFull.setFont(new Font("Consolas",Font.PLAIN,13));
		editPanel.add(courseFull);
		
		crsFull = new JTextField();
		crsFull.setBounds(10, 135, 265, 30);
		crsFull.setEditable(false);
		crsFull.setFont(new Font("Consolas",Font.PLAIN,13));
		editPanel.add(crsFull);
		
		back = new JButton("Back");
		back.setBounds(185, 180, 90, 30);
		back.setFont(new Font("Consolas",Font.PLAIN,13));
		back.setFocusable(false);
		back.addActionListener(this);
		editPanel.add(back);
		
		applyChange = new JButton("Save Changes");
		applyChange.setBounds(10, 180, 170, 30);
		applyChange.setFont(new Font("Consolas",Font.PLAIN,13));
		applyChange.setFocusable(false);
		applyChange.setEnabled(false);
		applyChange.addActionListener(this);
		editPanel.add(applyChange);

		editPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editPanel.setLocationRelativeTo(null);
		editPanel.setLayout(null);
		editPanel.setVisible(true);
		
	
	}
	
	// method to delete course
	private void deleteCourse() {

		deletePanel = new JFrame();
		deletePanel.setSize(300,120);
		deleteSearch = new JTextField();
		deleteSearch.setBounds(10, 10, 180, 30);
		deleteSearch.setFont(new Font("Consolas",Font.PLAIN,13));
		deletePanel.add(deleteSearch);
	
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(195, 10, 80, 30);
		deleteButton.addActionListener(this);
		deleteButton.setFont(new Font("Consolas",Font.PLAIN,13));
		deletePanel.add(deleteButton);
		
		deleteBack = new JButton("Back");
		deleteBack.setBounds(10, 45 , 265, 30);
		deleteBack.setFont(new Font("Consolas",Font.PLAIN,13));
		deleteBack.setFocusable(false);
		deleteBack.addActionListener(this);
		deletePanel.add(deleteBack);
		
		deletePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deletePanel.setLocationRelativeTo(null);
		deletePanel.setResizable(false);
		deletePanel.setLayout(null);
		deletePanel.setVisible(true);

	}
	
	// method to add courses
	private void addPanel() {
		
		addPanel = new JFrame();
		addPanel.setSize(600,270);
		addPanel.setResizable(false);	
		addPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addPanel.setLocationRelativeTo(null);
		addPanel.setLayout(null);
		
		courseShortForm = new JLabel("Course Short Form (*BIT):");
		courseShortForm.setBounds(50, 20, 200, 30);
		courseShortForm.setFont(new Font("Consolas",Font.PLAIN,15));
		addPanel.add(courseShortForm);
		
		crsShortForm = new JTextField();
		crsShortForm.setBounds(50, 55, 200, 30);
		crsShortForm.setFont(new Font("Consolas",Font.PLAIN,15));
		addPanel.add(crsShortForm);
		
		courseFullName = new JLabel("Course Full Name:");
		courseFullName.setBounds(50, 100, 200, 30);
		courseFullName.setFont(new Font("Consolas",Font.PLAIN,15));
		addPanel.add(courseFullName);
		
		crsFullName = new JTextField();
		crsFullName.setBounds(50, 130, 470, 30);
		crsFullName.setFont(new Font("Consolas",Font.PLAIN,15));
		addPanel.add(crsFullName);

		addedDate = new JLabel("Added Date(yy/mm/dd):");
		addedDate.setBounds(320, 20, 200, 30);
		addedDate.setFont(new Font("Consolas",Font.PLAIN,15));
		addPanel.add(addedDate);
		
		addDate = new JTextField();
		addDate.setBounds(320, 55, 200, 30);
		addDate.setFont(new Font("Consolas",Font.PLAIN,15));
		addPanel.add(addDate);
		
		courseAdd = new JButton("Add Course");
		courseAdd.setBounds(50, 180, 150, 30);
		courseAdd.addActionListener(this);
		courseAdd.setFont(new Font("Consolas",Font.PLAIN,14));
		addPanel.add(courseAdd);
		
		addBack = new JButton("Back");
		addBack.setBounds(205, 180, 90, 30);
		addBack.addActionListener(this);
		addBack.setFont(new Font("Consolas",Font.PLAIN,14));
		addPanel.add(addBack);
		addPanel.setVisible(true);
		
	}
	
	// method to start the Main panel(Overriden from instructor panel)
	public void startGUI()  {

		leftside = new JPanel();
		leftside.setBounds(0,-5,210,450);
		leftside.setLayout(null);
		leftside.setBorder(line);
		
		rightside = new JPanel();
		rightside.setBounds(210,0,575,372);
		rightside.setLayout(null);
		
		dashboard = new JButton();
		dashboard.setText("Dashboard");
		dashboard.setBounds(10, 15, 190, 50);
		dashboard.addActionListener(this);
		dashboard.setFont(new Font("Consolas",Font.PLAIN,15));
		dashboard.setFocusable(false);
		dashboard.setBackground(Color.WHITE);

		manageCourse = new JButton();
		manageCourse.setText("Courses");
		manageCourse.setBounds(10, 70, 190, 50);
		manageCourse.addActionListener(this);
		manageCourse.setFont(new Font("Consolas",Font.PLAIN,15));
		manageCourse.setFocusable(false);
		manageCourse.setBackground(Color.WHITE);
		
		manageAccounts = new JButton();
		manageAccounts.setText("Accounts");
		manageAccounts.setBounds(10, 125, 190, 50);
		manageAccounts.addActionListener(this);
		manageAccounts.setFont(new Font("Consolas",Font.PLAIN,15));
		manageAccounts.setFocusable(false);
		manageAccounts.setBackground(Color.WHITE);
		
		generateReport = new JButton();
		generateReport.setText("Statistics");
		generateReport.setBounds(10, 180, 190, 50);
		generateReport.addActionListener(this);
		generateReport.setFont(new Font("Consolas",Font.PLAIN,15));
		generateReport.setFocusable(false);
		generateReport.setBackground(Color.WHITE);
		
		exit = new JButton();
		exit.setText("Exit");
		exit.setBounds(10, 275, 190, 50);
		exit.addActionListener(this);
		exit.setFont(new Font("Consolas",Font.PLAIN,15));
		exit.setFocusable(false);
		exit.setBackground(Color.WHITE);

		leftside.add(dashboard);
		leftside.add(manageCourse);
		leftside.add(manageAccounts);
		leftside.add(generateReport);
		leftside.add(exit);
		
		studentView = new JFrame("Admin Panel");
		ImageIcon adminIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		studentView.setIconImage(adminIcon.getImage());
		studentView.getContentPane().setBackground(Color.LIGHT_GRAY);
		studentView.setSize(800,370);
		studentView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentView.setLocationRelativeTo(null);
		studentView.setLayout(null);
		studentView.setResizable(false);
		studentView.add(leftside);
		studentView.add(rightside);
		
		dashboard.doClick();
		UIManager.put("Button.disabledText", Color.BLACK);
		studentView.setVisible(true);

	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e){
		
		
			if(e.getSource() == dashboard) {
			
					rightside.removeAll();
					rightside.revalidate();
					rightside.repaint();
					dashboard.setEnabled(false);
					dashboard.setBorderPainted(false);
					manageCourse.setEnabled(true);
					manageCourse.setBorderPainted(true);
					generateReport.setBorderPainted(true);
					generateReport.setEnabled(true);
					
					String user = null;
					
				
					try {
						
						user = manage.getUser("SELECT * FROM user_info WHERE username='"+this.username+"' AND indicator='Admin'");
			
					} 
					
					catch (SQLException e1) {
						
						
					}
					
					finally {
						
						
						dash = new JPanel();
					    dash.setBounds(0,0,575,372);
					    dash.setLayout(null);
					   
					    body = new JPanel();
					    body.setBounds(15,10,550,50);
					    body.setBackground(Color.WHITE);
					    body.setBorder(line);
					    body.setLayout(null);
					    dash.add(body);
			
					    loggedIn = new JLabel();
					    loggedIn.setText("Logged: " + user +"");
			    		loggedIn.setFont(new Font("Consolas",Font.PLAIN,15));
			    		loggedIn.setBounds(17,-25,300,100);
			    		body.add(loggedIn);
		
			    		logout = new JButton();
			    		logout.setText("Logout");
			    		logout.setBounds(450,10,85,30);
			    		logout.addActionListener(this);
			    		logout.setFont(new Font("Consolas",Font.PLAIN,14));
			    		logout.setFocusable(false);
			    		body.add(logout);
			    		
			    		
			    		JButton managementStats = new JButton("Management Statistics");
			    		managementStats.setBounds(170, 100, 210, 40);
			    		managementStats.setFocusable(false);
			    		managementStats.setEnabled(false);
			    		managementStats.setFont(new Font("Consolas",Font.PLAIN, 14));
			    		rightside.add(managementStats);
			    		
			    		
			    		int countStudent = 0, countTeacher = 0, countCourse = 0;
			    		
			    		try {
			    			
			    			countStudent = manage.getCount("SELECT COUNT(DISTINCT username) as count FROM user_info WHERE indicator = 'Student'");
			    			countTeacher = manage.getCount("SELECT COUNT(DISTINCT username) as count FROM user_info WHERE indicator = 'Teacher'");
			    			countCourse = manage.getCount("SELECT COUNT(DISTINCT course_name) as count FROM courses WHERE course_name != '' ");
			    			
			    		}
			    		catch (Exception er) {
							
			    			er.printStackTrace();
						}

			    		JPanel studentCount = new JPanel();
			    		studentCount.setBounds(30, 160, 140, 140);
			    		studentCount.setBackground(Color.WHITE);
			    		studentCount.setLayout(null);
			    		studentCount.setBorder(line);
			    		rightside.add(studentCount);
			    		
			    		JLabel student = new JLabel(Integer.toString(countStudent));
			    		student.setFont(new Font("Consolas",Font.PLAIN,50));
			    		student.setBounds(55,15,300,100);
			    		studentCount.add(student);
			    		
			    		JLabel studentLabel = new JLabel("Students");
			    		studentLabel.setFont(new Font("Consolas",Font.PLAIN,20));
			    		studentLabel.setBounds(27,60,300,100);
			    		studentCount.add(studentLabel);
			    		
			    		JPanel teacherCount = new JPanel();
			    		teacherCount.setBounds(205, 160, 140, 140);
			    		teacherCount.setBackground(Color.WHITE);
			    		teacherCount.setBorder(line);
			    		teacherCount.setLayout(null);
			    		rightside.add(teacherCount);
			    		
			    		JLabel teacher = new JLabel(Integer.toString(countTeacher));
			    		teacher.setFont(new Font("Consolas",Font.PLAIN,50));
			    		teacher.setBounds(55,15,300,100);
			    		teacherCount.add(teacher);
			    		
			    		JLabel teacherLabel = new JLabel("Teachers");
			    		teacherLabel.setFont(new Font("Consolas",Font.PLAIN,20));
			    		teacherLabel.setBounds(27,60,300,100);
			    		teacherCount.add(teacherLabel);
			    		
			    		JPanel courseCount = new JPanel();
			    		courseCount.setBounds(390, 160, 140, 140);
			    		courseCount.setBackground(Color.WHITE);
			    		courseCount.setLayout(null);
			    		courseCount.setBorder(line);
			    		rightside.add(courseCount);
			    		
			    		JLabel course = new JLabel(Integer.toString(countCourse));
			    		course.setFont(new Font("Consolas",Font.PLAIN,50));
			    		course.setBounds(55,15,300,100);
			    		courseCount.add(course);
			    		
			    		JLabel courseLabel = new JLabel("Courses");
			    		courseLabel.setFont(new Font("Consolas",Font.PLAIN,20));
			    		courseLabel.setBounds(30,60,300,100);
			    		courseCount.add(courseLabel);
			    		
			    		
			    		
			    		rightside.add(dash);
			    		UIManager.put("Button.disabledText", Color.BLACK);
						studentView.add(rightside);
						SwingUtilities.updateComponentTreeUI(studentView);
						
						
					}
				
		 }

		
		if(e.getSource() == manageCourse) {
			
			buttonList1.clear();
			rightside.removeAll();
			rightside.revalidate();
			rightside.repaint();
			dashboard.setEnabled(true);
			dashboard.setBorderPainted(true);
			manageAccounts.setEnabled(true);
			manageAccounts.setBorderPainted(true);
			manageCourse.setEnabled(false);
			manageCourse.setBorderPainted(false);
			generateReport.setBorderPainted(true);
			generateReport.setEnabled(true);

			panelBack = new JPanel();
			panelBack.setBounds(15,10, 545, 310);
			panelBack.setBorder(line);
			panelBack.setLayout(null);
			
			searchCourse = new JTextField();
			searchCourse.setBounds(15,10,195,30);
			searchCourse.setFont(new Font("Consolas",Font.PLAIN,13));
			panelBack.add(searchCourse);
			
			editCourse = new JButton("Edit Course");
		    editCourse.setBounds(305, 10, 115, 30);
		    editCourse.setFont(new Font("Consolas",Font.PLAIN,13));
		    editCourse.addActionListener(this);
		    editCourse.setFocusable(false);
		    panelBack.add(editCourse);

		    addCourse = new JButton("Add Course");
		    addCourse.setBounds(425, 10, 110, 30);
		    addCourse.setFont(new Font("Consolas",Font.PLAIN,13));
		    addCourse.addActionListener(this);
		    addCourse.setFocusable(false);
		    panelBack.add(addCourse);

		    deleteCourse = new JButton("Delete Course");
		    deleteCourse.setBounds(405, 266, 130, 30);
		    deleteCourse.setFont(new Font("Consolas",Font.PLAIN,13));
		    deleteCourse.addActionListener(this);
		    deleteCourse.setFocusable(false);
		    panelBack.add(deleteCourse);

			findCourse = new JButton();
			findCourse.setBounds(215, 10, 85, 30);
			findCourse.setText("Search");
			findCourse.addActionListener(this);
			findCourse.setFocusable(false);
			findCourse.setFont(new Font("Consolas",Font.PLAIN,13));
			
			
			searchPanel = new JPanel();
			searchPanel.setBounds(15, 50, 520, 285);
			searchPanel.setBackground(Color.WHITE);
			searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
			searchPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			JScrollPane scrollPane = new JScrollPane(searchPanel);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(15, 50, 520, 205);
	        
			
	        JButton courses;
	        
	        ArrayList<String[]> courseDetails = null;
			try {
				
				courseDetails = manage.getCourseDetails("SELECT * FROM courses WHERE course_full !='"+"' ORDER BY course_full ASC");
				
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
	        
	        
			finally {
				
				for(String[] course: courseDetails) {
		        	
		        	courses = new JButton(course[0] + "  (" + course[1] + ")" );
	 	    		courses.setHorizontalAlignment(SwingConstants.LEFT);
	 	    		courses.setFont(new Font("Consolas",Font.PLAIN,13));
	 	    		
	 	    		if(courseDetails.size() <= 3) {
	 	    			
			 	    	courses.setMaximumSize(new Dimension(495, 60));
				 	    courses.setPreferredSize(new Dimension(395, 50));
			 	    	
			 	    }
			 	    		
			 	    else {
			 	    			
			 	    	courses.setMaximumSize(new Dimension(485, 62));
				 	    courses.setPreferredSize(new Dimension(395, 54));
			 	    			
			 	    }
			 	    		
			 	    	courses.setFocusable(false);
				 		buttonList1.add(courses);
					}
		
		        
		      

			 	    	 
				for(JButton butt : buttonList1) {
					 	    	
					 	butt.addActionListener(this);
					 	 searchPanel.add(butt);
					 	 searchPanel.add(Box.createRigidArea(new Dimension(15, 10)));
					 	    	
					}
				
				
				UIManager.put("Button.disabledText", Color.BLACK);
				panelBack.add(scrollPane);
				panelBack.add(findCourse);
				rightside.add(panelBack);
				
				
			}
			
		
	     }


		if(e.getSource() == manageAccounts) {
			
			
			buttonList1.clear();
			rightside.removeAll();
			rightside.revalidate();
			rightside.repaint();

			dashboard.setEnabled(true);
			dashboard.setBorderPainted(true);
			manageAccounts.setEnabled(false);
			manageAccounts.setBorderPainted(false);
			manageCourse.setEnabled(true);
			manageCourse.setBorderPainted(true);
			generateReport.setBorderPainted(true);
			generateReport.setEnabled(true);

			accountPanel = new JPanel();
			accountPanel.setBounds(15,10, 545, 175);
			accountPanel.setBackground(Color.WHITE);
			accountPanel.setBorder(line);
			accountPanel.setLayout(null);
			
			bottomPanel = new JPanel();
			bottomPanel.setBounds(15, 195, 545, 120);
			bottomPanel.setBackground(Color.WHITE);
			bottomPanel.setBorder(line);
			bottomPanel.setLayout(null);
			
			accountTitle = new JButton("Available Accounts");
			accountTitle.setBounds(15,10, 350, 30);
			accountTitle.setEnabled(false);
			accountTitle.setFont(new Font("Consolas",Font.PLAIN, 14));
			accountPanel.add(accountTitle);
			
			assign = new JButton("Manage Instruct");
			assign.setBounds(370,10, 160, 30);
			assign.setFocusable(false);
			assign.addActionListener(this);
			assign.setFont(new Font("Consolas",Font.PLAIN, 14));
			accountPanel.add(assign);
			

			UIManager.put("Button.disabledText", Color.BLACK);
			
			String column[]={"S.N","Username","Position","Status"};  
			
			ArrayList<String[]> data = null;
			try {
				
				data = manage.getLogs("SELECT user_id, username, status, indicator FROM logs WHERE indicator ='Student' OR indicator='Teacher'");
				
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			String[][] userlogs = (String[][]) data.toArray(new String[data.size()][3]);
			
			
			accounts = new JTable(userlogs,column);
			accounts.setRowHeight(27);
			accounts.setBorder(wline);
			JTableHeader header = accounts.getTableHeader();
			header.setFont(new Font("Consolas",Font.PLAIN, 14));
			accounts.getTableHeader().setPreferredSize(
				     new Dimension(0, 30)
				);
			
			accounts.setFont(new Font("Consolas",Font.PLAIN, 12));
			accounts.setEnabled(false);

			accounts.getColumnModel().getColumn(0).setPreferredWidth(100);
			accounts.getColumnModel().getColumn(1).setPreferredWidth(200);
			accounts.getColumnModel().getColumn(2).setPreferredWidth(100);
			accounts.getColumnModel().getColumn(3).setPreferredWidth(100);

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			accounts.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );accounts.setDefaultRenderer(String.class, centerRenderer);
			accounts.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );accounts.setDefaultRenderer(String.class, centerRenderer);
			accounts.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );accounts.setDefaultRenderer(String.class, centerRenderer);
			accounts.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );accounts.setDefaultRenderer(String.class, centerRenderer);

			scrollPane = new JScrollPane(accounts);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(15, 50, 515, 114);
			
			name = new JLabel("Username:");
			name.setBounds(20, 15, 150, 40);
			name.setFont(new Font("Consolas",Font.PLAIN, 14));
			bottomPanel.add(name);
			
			userName = new JTextField();
			userName.setBounds(100, 19, 320, 30);
			userName.setFont(new Font("Consolas",Font.PLAIN, 14));
			bottomPanel.add(userName);
			
			searchAccount = new JButton("Search");
			searchAccount.setBounds(430, 18, 100, 30);
			searchAccount.addActionListener(this);
			searchAccount.setFocusable(false);
			searchAccount.setFont(new Font("Consolas",Font.PLAIN, 13));
			bottomPanel.add(searchAccount);
			
			nameLabel = new JLabel("Username:");
			nameLabel.setBounds(20, 72, 150, 30);
			nameLabel.setFont(new Font("Consolas",Font.PLAIN, 14));
			bottomPanel.add(nameLabel);
			
			nameField = new JTextField();
			nameField.setBounds(100, 72, 150, 30);
			nameField.setEditable(false);
			nameField.setFont(new Font("Consolas",Font.PLAIN, 13));
			bottomPanel.add(nameField);
			
			statusLabel = new JLabel("Status:");
			statusLabel.setBounds(270, 72, 150, 30);
			statusLabel.setFont(new Font("Consolas",Font.PLAIN, 14));
			bottomPanel.add(statusLabel);

			status = new JTextField();
			status.setBounds(330, 72, 88, 30);
			status.setEditable(false);
			status.setFont(new Font("Consolas",Font.PLAIN, 14));
			bottomPanel.add(status);
			
			changeLogs = new JButton("Save");
			changeLogs.setBounds(430, 72, 100, 30);
			changeLogs.addActionListener(this);
			changeLogs.setEnabled(false);
			changeLogs.setFocusable(false);
			changeLogs.setFont(new Font("Consolas",Font.PLAIN, 13));
			bottomPanel.add(changeLogs);
		
			accountPanel.add(scrollPane);
			rightside.add(accountPanel);
			rightside.add(bottomPanel);

		}
		
		if(e.getSource() == generateReport) {
			
			buttonList1.clear();
			rightside.removeAll();
			rightside.revalidate();
			rightside.repaint();
			dashboard.setEnabled(true);
			dashboard.setBorderPainted(true);
			manageAccounts.setEnabled(true);
			manageAccounts.setBorderPainted(true);
			manageCourse.setEnabled(true);
			manageCourse.setBorderPainted(true);
			generateReport.setBorderPainted(false);
			generateReport.setEnabled(false);
	
			resultStudent = new JTextField();
			resultStudent.setBounds(50, 130, 300, 30);
			resultStudent.setFont(new Font("Consolas",Font.PLAIN, 14));
			rightside.add(resultStudent);
	
			generateButton = new JButton("Generate Report");
			generateButton.setBounds(360, 130, 160, 28);
			generateButton.addActionListener(this);
			generateButton.setFocusable(false);
			generateButton.setFont(new Font("Consolas",Font.PLAIN, 14));
			rightside.add(generateButton);
	
			
		}
		
		if(e.getSource() == generateButton) {
			
			
			studentView.dispose();
			

			try {
				
				data = manage.getUserReport("SELECT * FROM user_info WHERE fullname = '"+resultStudent.getText()+"'");
				
			}
			
			catch (Exception error) {
				
			}
			
			new GenerateReportAdmin(data[0], data[1], this.username);
			
			
		}
		
		if(e.getSource() == deassignModule) {
			
			
			
			String[] assigned = null;
			
			String code = null;
			
			String updated = "";
			
			try {
				code = manage.getModuleCode("SELECT module_code FROM modules WHERE course_modules = '"+ dropdown.getSelectedItem() + "'");
				assigned = manage.getAssignedCourse("SELECT assigned FROM user_info WHERE username='"+ teacher.getText()+"' AND indicator = 'Teacher'");
			} 
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				 List assignedModules = Arrays.asList(assigned);
				 
				 if(!assignedModules.isEmpty()) {
					 
					for(String modules: assigned) {
						
						if(!modules.equals(code)) {
							
							updated+= "," + modules;
							
						}
						
					}
					
					
					try {
						
						manage.updateAssigned("UPDATE `user_info` SET `assigned`='"+ updated +"' WHERE username='"+ teacher.getText() +"' AND indicator = 'Teacher'");
					} 
					
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					finally {
						
						JLabel er = new JLabel("Task Completed Succesfully!");
						er.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
						manageTeacher.dispose();
						new AdminPanel(this.username).startGUI();
						
						
					}
					 
				 }
				 
				 else {
					 
					 JLabel er = new JLabel("Oops! Something Went Wrong");
					 er.setFont(new Font("Consolas",Font.PLAIN, 13));
					 JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
					 manageTeacher.dispose();
					 new AdminPanel(this.username).startGUI();
					 
					 
				 }
				 
				
			   }
			
			}
			

		if(e.getSource() == assignBack) {
			
			
			manageTeacher.dispose();
			new AdminPanel(this.username).startGUI();
			
		}
		
		if(e.getSource() == assignModule) {
			
			String[] assigned = null;
			
			String code = null;
	
			String updated = "";
			
			try {
				
				code = manage.getModuleCode("SELECT module_code FROM modules WHERE course_modules = '"+ dropdown.getSelectedItem() + "'");
				assigned = manage.getAssignedCourse("SELECT assigned FROM user_info WHERE username='"+ teacher.getText() +"' AND indicator = 'Teacher'");
			} 
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				List assignedModules = Arrays.asList(assigned);
				 
				 if(!assignedModules.contains(code) && assignedModules.size() < 4) {
					 
					for(String modules: assigned) {
						
						updated+= "," + modules;
						
					}
					
					updated+="," + code;
					
					try {
						
						manage.updateAssigned("UPDATE `user_info` SET `assigned`='"+ updated +"' WHERE username='"+ teacher.getText() +"' AND indicator = 'Teacher'");
					} 
					
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					finally {
						
						JLabel er = new JLabel("Task Completed Succesfully!");
						er.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
						manageTeacher.dispose();
						new AdminPanel(this.username).startGUI();
						
						
					}
					 
				 }
				 
				 else {
					 
					 JLabel er = new JLabel("Oops! Something Went Wrong");
					 er.setFont(new Font("Consolas",Font.PLAIN, 13));
					 JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
					 manageTeacher.dispose();
					 new AdminPanel(this.username).startGUI();
					 
					 
				 }
				 
				
			}
			
		}
		
		
		if(e.getSource() == changeLogs) {
			
			
			studentView.dispose();
			try {
				
				manage.updateLogs("UPDATE `logs` SET `username`='"+nameField.getText()+"',`status`='"+status.getText()+"' WHERE username ='"+nameField.getText()+"'");
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally{
				
				new AdminPanel(this.username).startGUI();
			}
			
		}
		
		if(e.getSource() == searchAccount) {
			
			String[] searched = null;
			
			try {
				
				searched = manage.searchAccount("SELECT username,status FROM logs WHERE username='"+userName.getText()+"'");
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			} 
			
			finally {
				
				nameField.setEditable(true);
				status.setEditable(true);
				nameField.setText(searched[0]);
				
				status.setText(searched[1]);
				changeLogs.setEnabled(true);

			}
			
		}
		
		if(e.getSource() == assign) {
			
			studentView.dispose();
			manageTeacherModules();
			
		}

		if(e.getSource() == logout) {
			studentView.dispose();
			MainPanel main = new MainPanel();
			main.startGUI();
			
		}
		
		if(e.getSource() == view) {
			
			studentView.dispose();
		
		}
		
		
		if(e.getSource() == addCourse) {
			
			studentView.dispose();
			addPanel();
			
			
		}
		
		if(e.getSource() == editCourse) {
			
			editPanel();
			studentView.dispose();

		}
		
		if(e.getSource() == deleteCourse) {
			
			studentView.dispose();
			deleteCourse();
			
			
		}
		
		if(e.getSource() == findCourse) {
			
			String searched = searchCourse.getText();
			buttonList1.clear();
			
			
			if(searchCourse.getText().isBlank() || searchCourse.getText().isEmpty()) {
				
				
				manageCourse.setEnabled(true);
				manageCourse.doClick();
				
			}
			
			else {
				
				
				rightside.removeAll();
				rightside.revalidate();
				rightside.repaint();
				dashboard.setEnabled(true);
				dashboard.setBorderPainted(true);
				manageAccounts.setEnabled(true);
				manageAccounts.setBorderPainted(true);
				manageCourse.setEnabled(false);
				manageCourse.setBorderPainted(false);

				panelBack = new JPanel();
				panelBack.setBounds(15,10, 545, 310);
				panelBack.setBorder(line);
				panelBack.setLayout(null);
				
				searchCourse = new JTextField();
				searchCourse.setBounds(15,10,195,30);
				searchCourse.setFont(new Font("Consolas",Font.PLAIN,13));
				panelBack.add(searchCourse);
				
				editCourse = new JButton("Edit Course");
			    editCourse.setBounds(305, 10, 115, 30);
			    editCourse.setFont(new Font("Consolas",Font.PLAIN,13));
			    editCourse.addActionListener(this);
			    editCourse.setFocusable(false);
			    panelBack.add(editCourse);

			    addCourse = new JButton("Add Course");
			    addCourse.setBounds(425, 10, 110, 30);
			    addCourse.setFont(new Font("Consolas",Font.PLAIN,13));
			    addCourse.addActionListener(this);
			    addCourse.setFocusable(false);
			    panelBack.add(addCourse);

			    deleteCourse = new JButton("Delete Course");
			    deleteCourse.setBounds(312, 266, 130, 30);
			    deleteCourse.setFont(new Font("Consolas",Font.PLAIN,13));
			    deleteCourse.addActionListener(this);
			    deleteCourse.setFocusable(false);
			    panelBack.add(deleteCourse);
			    
			    refresh = new JButton("Refresh");
			    refresh.setBounds(448, 266, 85, 30);
			    refresh.setFont(new Font("Consolas",Font.PLAIN,13));
			    refresh.addActionListener(this);
			    refresh.setFocusable(false);
			    panelBack.add(refresh);

				findCourse = new JButton();
				findCourse.setBounds(215, 10, 85, 30);
				findCourse.setText("Search");
				findCourse.addActionListener(this);
				findCourse.setFocusable(false);
				findCourse.setFont(new Font("Consolas",Font.PLAIN,13));
		
				searchPanel = new JPanel();
				searchPanel.setBounds(15, 50, 520, 285);
				searchPanel.setBackground(Color.WHITE);
				searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
				searchPanel.add(Box.createRigidArea(new Dimension(0, 10)));
				JScrollPane scrollPane = new JScrollPane(searchPanel);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(15, 50, 520, 205);
	
		        JButton courses;
		        String[] courseDetails = null;
	
				try {
					
					courseDetails = manage.getSearchedCourse("SELECT * FROM courses WHERE course_name='" + searched + "'");
					
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		        
				finally {

			        	courses = new JButton(courseDetails[0] + "  (" + courseDetails[1] + ")" );
		 	    		courses.setHorizontalAlignment(SwingConstants.LEFT);
		 	    		courses.setFont(new Font("Consolas",Font.PLAIN,13));
		 	    		courses.setMaximumSize(new Dimension(500, 60));
					 	courses.setPreferredSize(new Dimension(395, 50));
					 	courses.setFocusable(false);
					 	buttonList1.add(courses);
						
					}
			 
					for(JButton butt : buttonList1) {
						 	    	
						 butt.addActionListener(this);
						 searchPanel.add(butt);
						 searchPanel.add(Box.createRigidArea(new Dimension(12, 10)));
						 	    	
					}

					UIManager.put("Button.disabledText", Color.BLACK);
					panelBack.add(scrollPane);
					panelBack.add(findCourse);
					rightside.add(panelBack);

				}   
	
		}


		if(e.getSource() == courseSearch ) {
			
			courseCode = crsSearch.getText();
			crsSearch.setText("");
			
			String[] course = null;
			
			try {
				
				course = manage.findCourse("SELECT course_name,course_full FROM courses WHERE course_name = '"+ courseCode +"'");
				
			} 
			
			catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
			crsShort.setText(course[0]);
			crsShort.setEditable(true);
			crsFull.setText(course[1]);
			crsFull.setEditable(true);
			applyChange.setEnabled(true);

		}
	
		if(e.getSource() == deleteButton) {
			
			String course = deleteSearch.getText();
			
			try {
				
				manage.deleteCourse("DELETE FROM courses WHERE course_name = '"+ course +"'");
				
			} 
			
			catch (SQLException e1) {
	
				e1.printStackTrace();
			}
			
			
			finally {
				
				JLabel er = new JLabel("Task Completed Succesfully!");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
				startGUI();

			}  
				

			
		}
		
		
		if(e.getSource() == refresh) {
			
			manageCourse.setEnabled(true);
			buttonList1.clear();
			manageCourse.doClick();
			
		}
		
		if(e.getSource() == applyChange) {
			
			try {
				
				manage.updateCourse("UPDATE courses SET course_name='"+ crsShort.getText() +"', course_full = '"+ crsFull.getText() +"' WHERE course_name='"+ courseCode +"'");
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				JLabel er = new JLabel("Task Completed Succesfully!");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
				editPanel.dispose();
				startGUI();
				
			}

			

		}
	
		if(e.getSource() == courseAdd) {
			
			boolean checkCourse = false ;
			
			try {
				
			checkCourse	 = manage.getExistingCourse("SELECT course_name, course_full FROM courses WHERE  course_name = '"+ crsShortForm.getText() +"' AND course_full = '"+ crsShortForm.getText()+"' OR course_name = '"+ crsShortForm.getText()+ "' OR course_full = '"+ crsShortForm.getText()+"'");
				
			}
			
			catch(Exception error){
				
				error.printStackTrace();
				
			}
			
			finally {
				
				
				
				if(checkCourse) {
					
					JLabel er = new JLabel("Course Already Exists");
					er.setFont(new Font("Consolas",Font.PLAIN, 13));
					JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
					crsShortForm.setText("");
					crsFullName.setText("");
					addDate.setText("");
					
					
				}
				
				else {
					
					try {
						
						manage.addCourse("INSERT INTO `courses`(`course_name`, `course_full`, `addedDate`) VALUES ('"+ crsShortForm.getText() +"','"+ crsFullName.getText() +"','"+addDate.getText()+"')");
					} 
					
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					finally {
						
						JLabel er = new JLabel("Task Complete Succesfully");
						er.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
						addPanel.dispose();
						startGUI();
						
						
					}
						
				}

			}
				
		} 

		if(e.getSource() == deleteBack) {
		
			deletePanel.dispose();
			AdminPanel admin = new AdminPanel(this.username);
			admin.startGUI();

		}
				
		if(e.getSource() == back) {
			
			editPanel.dispose();
			startGUI();
			
		}
		
		if(e.getSource() == addBack) {
			
			addPanel.dispose();
			startGUI();
			
		}
		
		
		if(e.getSource() instanceof JButton) {
			
			String diff = ((JButton)e.getSource()).getText();
			
			if(diff.split(" ").length >=3) {
				
				studentView.dispose();
				new ModuleEditPanel(diff.split("  ")[0], diff.split("  ")[1].replace(")", "").replace("(", ""), this.username);

			}
			
		}
		
		
		if(e.getSource() == exit) {
			
			studentView.dispose();
			
		}
		
    }

}
		
 
	

	

